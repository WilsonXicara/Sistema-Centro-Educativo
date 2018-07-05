    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.carrera;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.asignacion.grado.AnuladorAsignacionGrado;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.excepciones.NonexistentEntityException;



/**
 *
 * @author juan_
 */
public class AnuladorAsignacionCarrera {
    private final EntityManagerFactory emf;
    private final Long idAsignacionCarrera;
    private String razon_anulacion; 

    public AnuladorAsignacionCarrera(EntityManagerFactory emf, Long idAsignacionCarrera) {
      this.emf = emf;
      this.idAsignacionCarrera = idAsignacionCarrera;
    }

    public void setRazon_anulacion(String razon_anulacion) {
        this.razon_anulacion = razon_anulacion;
    }

    public void anularAsignacion() throws NonexistentEntityException{
        List<AsignacionGradoEntity> lista;
        if(!ConsultorAsignacionCarrera.existeAsignacionCarrera(idAsignacionCarrera, emf)){
            throw new NonexistentEntityException("No existe una carrera con el id siguiente: " + idAsignacionCarrera);    
        }
        //Se hace la anulación de la asignación carrera
        AsignacionCarreraEntity asignacionAnulada = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntity(idAsignacionCarrera);
        asignacionAnulada.setAnulado(true);
        asignacionAnulada.setRazon_anulacion(razon_anulacion);      
        
        //Se busca con que otras asignaciones está relacionada esta asignacion y se anulan también
        lista = new AsignacionGradoJpaController(emf).buscarPorCarrera(idAsignacionCarrera);
        AnuladorAsignacionGrado anuladorAsigGrado = new AnuladorAsignacionGrado(emf,lista.get(0).getId());
        anuladorAsigGrado.setRazon_anulacion("La asignación de carrera con id");
        anuladorAsigGrado.anularAsignacion();
    }
    
    
}
