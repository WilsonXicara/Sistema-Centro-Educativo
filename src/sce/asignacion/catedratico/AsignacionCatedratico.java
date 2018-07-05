/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.carrera.ConsultorAsignacionCarrera;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoJpaController;
import sce.excepciones.NonexistentEntityException;
import sce.persona.catedratico.ConsultorCatedratico;
import sce.persona.catedratico.orm.CatedraticoEntity;
import sce.persona.catedratico.orm.CatedraticoJpaController;

/**
 *
 * @author juan_
 */
public class AsignacionCatedratico implements AsignacionCommand{
        private final EntityManagerFactory emf;
        private Long idAsignacionCarrera;
        private Long idCatedratico;
       
    public AsignacionCatedratico(EntityManagerFactory emf) {
        this.emf = emf;    
    }

    public void setIdAsignacionCarrera(Long idAsignacionCarrera) {
        this.idAsignacionCarrera = idAsignacionCarrera;
    }

    public void setIdCatedratico(Long idCatedratico) {
        this.idCatedratico = idCatedratico;
    }

    
    @Override
    public void crearAsignacion() throws NonexistentEntityException{
        if (!ConsultorCatedratico.existeCatedratico(idCatedratico, emf)){
            throw new NonexistentEntityException("El catedrático con id " +idCatedratico+ "no existe."); 
        }
        if (!ConsultorCatedratico.isCatedraticoAnulado(idCatedratico, emf)){
            throw new NonexistentEntityException("El catedrático con id " +idCatedratico+ "está anulado."); 
        }
        if (!ConsultorAsignacionCarrera.existeAsignacionCarrera(idAsignacionCarrera, emf)){
            throw new NonexistentEntityException("La asignación carrera con id " +idAsignacionCarrera+ "está anulada.");
        }
        if (ConsultorAsignacionCarrera.isAsignacionCarreraAnulada(idAsignacionCarrera, emf)){
            throw new NonexistentEntityException("La asignación carrera con id " +idAsignacionCarrera+ "está anulada.");
        }
        AsignacionCatedraticoEntity asignacionCat = new AsignacionCatedraticoEntity();
        asignacionCat.setCatedratico_id(idCatedratico);
        asignacionCat.setAsignacion_carrera_id(idAsignacionCarrera);
        new AsignacionCatedraticoJpaController(emf).create(asignacionCat);
        CatedraticoEntity catedratico = new CatedraticoJpaController(emf).findCatedraticoEntity(idCatedratico);
        catedratico.setAsignacion_id(idAsignacionCarrera);
            try {
                new CatedraticoJpaController(emf).edit(catedratico);
            } catch (Exception ex) {
                Logger.getLogger(AsignacionCatedratico.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
