/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico;

import javax.persistence.EntityManagerFactory;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoJpaController;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author juan_
 */
public class AnuladorAsignacionCatedratico { 
    private final EntityManagerFactory emf;
    private final Long idAsignacionCatedratico;
    private String razon_anulacion; 

    public AnuladorAsignacionCatedratico(EntityManagerFactory emf, Long idAsignacionCatedratico) {
      this.emf = emf;
      this.idAsignacionCatedratico = idAsignacionCatedratico;
    }

    public void setRazon_anulacion(String razon_anulacion) {
        this.razon_anulacion = razon_anulacion;
    }

    public void anularAsignacion() throws NonexistentEntityException{
        if(!ConsultorAsignacionCatedratico.existeAsignacionCatedratico(idAsignacionCatedratico, emf)){
            throw new NonexistentEntityException("No existe una asignacion catedratico con el id siguiente: " + idAsignacionCatedratico);    
        }
        AsignacionCatedraticoEntity asignacionAnulada = new AsignacionCatedraticoJpaController(emf).findAsignacionCatedraticoEntity(idAsignacionCatedratico);
        asignacionAnulada.setAnulado(true);
        asignacionAnulada.setRazon_anulacion(razon_anulacion); 
        try {
            new AsignacionCatedraticoJpaController(emf).edit(asignacionAnulada);
        } catch (Exception ex) {
            throw new NonexistentEntityException(ex.getMessage());
        }
    }
}
