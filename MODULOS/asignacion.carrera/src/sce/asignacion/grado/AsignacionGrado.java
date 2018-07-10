/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.grado;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.carrera.ConsultorAsignacionCarrera;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.excepciones.NonexistentEntityException;
import sce.principal.elemento_asignatura.grado.ConsultorRegistroGrado;
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
        if (!ConsultorAsignacionCarrera.existeAsignacionCarrera(idAsignacionCarrera, emf)){
            throw new NonexistentEntityException("No existen una asignacion carrera con el id siguiente: " + idAsignacionCarrera);  
        }
        if (ConsultorAsignacionCarrera.isAsignacionCarreraAnulada(idAsignacionCarrera, emf)){
            throw new NonexistentEntityException("La asignacion carrera con id " +idAsignacionCarrera+ "está anulada.");
        } else {
            AsignacionGradoEntity asignacionGrado = new AsignacionGradoEntity();
            asignacionGrado.setAsignacion_carrera_id(idAsignacionCarrera);
            for (Long elementos : idGrado){
                if (ConsultorRegistroGrado.existeGrado(elementos, emf)){
                    GradoEntity gradoExistente = new GradoJpaController(emf).findGradoEntity(elementos);
                    asignacionGrado.setGrado_id(elementos);
                    gradoExistente.setAsignacion_id(asignacionGrado.getId());
                    new AsignacionGradoJpaController(emf).create(asignacionGrado);
                    try {
                        new GradoJpaController(emf).edit(gradoExistente);
                    } catch (Exception ex) {
                        Logger.getLogger(AsignacionGrado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    throw new NonexistentEntityException("No existen grado con el id siguiente: " + elementos);
                }
            }
        }  
    }
    
}
