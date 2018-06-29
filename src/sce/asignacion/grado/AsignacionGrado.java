/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.grado;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import sce.principal.command.AsignacionCommand;
import sce.principal.entity.AsignacionCarreraEntity;
import sce.principal.entity.AsignacionGradoEntity;
import sce.principal.entity.GradoEntity;
import sce.principal.ormjpa.AsignacionGradoJpaController;
import sce.principal.ormjpa.GradoJpaController;

/**
 *
 * @author juan_
 */
public class AsignacionGrado implements AsignacionCommand{
    
    private final EntityManagerFactory emf;
    private AsignacionCarreraEntity carrera;
    private GradoEntity grado;

    public AsignacionGrado(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void setCarrera(AsignacionCarreraEntity carrera) {
        this.carrera = carrera;
    }
   
    public void setGrado(GradoEntity grado) {
        this.grado = grado;
    }
    
    
    @Override
    public void crearAsignacion() {
        AsignacionGradoJpaController asignador = new AsignacionGradoJpaController(emf);
        AsignacionGradoEntity nuevaAsignacion = new AsignacionGradoEntity();
        nuevaAsignacion.setAsignacion_carrera_id(carrera.getId());
        nuevaAsignacion.setGrado_id(grado.getId());
        asignador.create(nuevaAsignacion);
        grado.setAsignacion_id(nuevaAsignacion.getId());
        try {
            new GradoJpaController(emf).edit(grado);
        } catch (Exception ex) {
            Logger.getLogger(AsignacionGrado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
