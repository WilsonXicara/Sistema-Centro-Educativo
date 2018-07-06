/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.catedratico;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.AnuladorAsignacionCarrera;
import sce.asignacion.carrera.ConsultorAsignacionCarrera;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.asignacion.catedratico.AnuladorAsignacionCatedratico;
import sce.asignacion.catedratico.ConsultorAsignacionCatedratico;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoEntity;
import sce.asignacion.catedratico.orm.AsignacionCatedraticoJpaController;
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
        List<AsignacionCatedraticoEntity> listaAsignacionCatedratico;
        if(!ConsultorCatedratico.existeCatedratico(idCatedratico, emf)){
            throw new NonexistentEntityException("No existe una catedratico con el id siguiente: " + idCatedratico);    
        }
        CatedraticoEntity asignacionAnulada = new CatedraticoJpaController(emf).findCatedraticoEntity(idCatedratico);
        asignacionAnulada.setAnulado(true);
        asignacionAnulada.setRazon_anulacion(razon_anulacion);   
         try {
             new CatedraticoJpaController(emf).edit(asignacionAnulada);
         } catch (Exception ex) {
             throw new NonexistentEntityException(ex.getMessage());
         }
         
         //Se hace una anulación de la asignación de catedrático que esté relacionada con dicho catedrático
         listaAsignacionCatedratico = new AsignacionCatedraticoJpaController(emf).buscarPorCatedratico(idCatedratico);
         if (!ConsultorAsignacionCatedratico.existeAsignacionCatedratico(listaAsignacionCatedratico.get(0).getId(), emf)){
             throw new NonexistentEntityException("No existe una asignacion catedratico por anular");
         }
         AnuladorAsignacionCatedratico anuladorAsignacionC = new AnuladorAsignacionCatedratico(emf,listaAsignacionCatedratico.get(0).getId());
         anuladorAsignacionC.setRazon_anulacion("El catedrático con id "+idCatedratico+" ha sido anulado");
         anuladorAsignacionC.anularAsignacion();
       
    }
    
}
