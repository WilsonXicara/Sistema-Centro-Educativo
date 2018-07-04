/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoJpaController;
import sce.asignacion.consultor.ConsultorGeneral;
import sce.excepciones.NonexistentEntityException;
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
        EntityManager em = null;
        em = emf.createEntityManager();
        em.getTransaction().begin();
        if (!ConsultorGeneral.asignacionCarreraExistente(idAsignacionCarrera, emf)){
            em.getTransaction().rollback();
            throw new NonexistentEntityException("La asignaciÃ³n carrera con id " +idAsignacionCarrera+ "no existe.");
        } else {
            if (ConsultorGeneral.asignacionCarreraAnulada(idAsignacionCarrera, emf)){
                throw new NonexistentEntityException("La asignaciÃ³n carrera con id " + idAsignacionCarrera + "estÃ¡ anulada");
            } else{
                if (ConsultorGeneral.catedraticoExistente(idCatedratico, emf)){
                    CatedraticoEntity catedratico = new CatedraticoJpaController(emf).findCatedraticoEntity(idCatedratico);
                    AsignacionCatedraticoEntity asignacionCat = new AsignacionCatedraticoEntity();
                    asignacionCat.setAsignacion_carrera_id(idAsignacionCarrera);
                    asignacionCat.setCatedratico_id(idCatedratico);
                    new AsignacionCatedraticoJpaController(emf).create(asignacionCat);
                    catedratico.setAsignacion_id(asignacionCat.getId());
                    try {
                        new CatedraticoJpaController(emf).edit(catedratico);
                    } catch (Exception ex) {
                        Logger.getLogger(AsignacionCatedratico.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                } else {
                    em.getTransaction().rollback();
                    throw new NonexistentEntityException("El catedrÃ¡tico con id " +idCatedratico+ "no existe.");
                }
            }
        }
        em.getTransaction().commit();
    }
    
}
