/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.grado;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.consultor.ConsultorGeneral;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.excepciones.NonexistentEntityException;
import sce.principal.elemento_asignatura.grado.orm.GradoEntity;
import sce.principal.elemento_asignatura.grado.orm.GradoJpaController;

/**
 *
 * @author juan_
 */
public class AsignacionGrado implements AsignacionCommand{
    
    private final EntityManagerFactory emf;
    private Long idAsignacionCarrera;
    private final ArrayList<Long> idGrado; 
   

    public AsignacionGrado(EntityManagerFactory emf) {
        this.emf = emf;
        this.idGrado = new ArrayList<>();
    }

    public void setIdAsignacionCarrera(Long idAsignacionCarrera) {
        this.idAsignacionCarrera = idAsignacionCarrera;
    }
    

    @Override
    public void crearAsignacion() throws NonexistentEntityException {
        EntityManager em = null;
        em = emf.createEntityManager();
        em.getTransaction().begin();
        if (ConsultorGeneral.asignacionCarreraExistente(idAsignacionCarrera, emf)){
            AsignacionGradoEntity asignacionGrado = new AsignacionGradoEntity();
            asignacionGrado.setAsignacion_carrera_id(idAsignacionCarrera);
            for(Long elementos : idGrado){
                if (ConsultorGeneral.gradoExistente(elementos, emf)){
                    GradoEntity gradoExistente = new GradoJpaController(emf).findGradoEntity(elementos);
                    asignacionGrado.setGrado_id(elementos); 
                    gradoExistente.setAsignacion_id(asignacionGrado.getId());
                    new AsignacionGradoJpaController(emf).create(asignacionGrado, em);
                    try {
                        new GradoJpaController(emf).edit(gradoExistente);
                    } catch (Exception ex) {
                        Logger.getLogger(AsignacionGrado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    em.getTransaction().rollback();
                    throw new NonexistentEntityException("No existen un grado con el id siguiente: " + elementos);
                } 
            }
            
        } else{
            em.getTransaction().rollback();
            throw new NonexistentEntityException("No existen una asignaciÃ³n carrera con el id siguiente: " + idAsignacionCarrera);
        }
        
        em.getTransaction().commit();
    }
    
}
