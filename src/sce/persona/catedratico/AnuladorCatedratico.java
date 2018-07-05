/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.catedratico;

import javax.persistence.EntityManagerFactory;
import sce.excepciones.NonexistentEntityException;
import sce.persona.catedratico.orm.CatedraticoEntity;
import sce.persona.catedratico.orm.CatedraticoJpaController;

/**
 *
 * @author juan_
 */
public class AnuladorCatedratico {
     private final EntityManagerFactory emf;
    private final Long idCatedratico;
    private String razon_anulacion; 

    public AnuladorCatedratico(EntityManagerFactory emf, Long idCatedratico) {
      this.emf = emf;
      this.idCatedratico = idCatedratico;
    }

    public void setRazon_anulacion(String razon_anulacion) {
        this.razon_anulacion = razon_anulacion;
    }

    public void anularAsignacion() throws NonexistentEntityException{
        if(!ConsultorCatedratico.existeCatedratico(idCatedratico, emf)){
            throw new NonexistentEntityException("No existe una catedratico con el id siguiente: " + idCatedratico);    
        }
        CatedraticoEntity asignacionAnulada = new CatedraticoJpaController(emf).findCatedraticoEntity(idCatedratico);
        asignacionAnulada.setAnulado(true);
        asignacionAnulada.setRazon_anulacion(razon_anulacion);      
    }
    
}
