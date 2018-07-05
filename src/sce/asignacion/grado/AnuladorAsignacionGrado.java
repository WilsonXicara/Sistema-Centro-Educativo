/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.grado;

import javax.persistence.EntityManagerFactory;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author juan_
 */
public class AnuladorAsignacionGrado {
    private final EntityManagerFactory emf;
    private final Long idAsignacionGrado;
    private String razon_anulacion; 

    public AnuladorAsignacionGrado(EntityManagerFactory emf, Long idAsignacionGrado) {
      this.emf = emf;
      this.idAsignacionGrado = idAsignacionGrado;
    }

    public void setRazon_anulacion(String razon_anulacion) {
        this.razon_anulacion = razon_anulacion;
    }

    public void anularAsignacion() throws NonexistentEntityException{
        if(!ConsultorAsignacionGrado.existeAsignacionGrado(idAsignacionGrado, emf)){
            throw new NonexistentEntityException("No existe una carrera con el id siguiente: " + idAsignacionGrado);    
        }
        AsignacionGradoEntity asignacionAnulada = new AsignacionGradoJpaController(emf).findAsignacion_Grado(idAsignacionGrado);
        asignacionAnulada.setAnulado(true);
        asignacionAnulada.setRazon_anulacion(razon_anulacion);      
    }
    
}
