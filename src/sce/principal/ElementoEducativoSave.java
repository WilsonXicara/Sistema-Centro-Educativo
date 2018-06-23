/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal;

import javax.persistence.EntityManagerFactory;
import sce.principal.entity.AsignacionCursoEntity;
import sce.principal.entity.CatedraticoEntity;
import sce.principal.entity.CicloEscolarEntity;
import sce.principal.entity.CursoEntity;
import sce.principal.entity.EstudianteEntity;
import sce.principal.entity.GradoEntity;
import sce.principal.ormjpa.AsignacionCursoJpaController;
import sce.principal.ormjpa.CatedraticoJpaController;
import sce.principal.ormjpa.CicloEscolarJpaController;
import sce.principal.ormjpa.CursoJpaController;
import sce.principal.ormjpa.EstudianteJpaController;
import sce.principal.ormjpa.GradoJpaController;

/**
 *
 * @author Usuario
 */
public class ElementoEducativoSave implements ElementoEducativoCommand {
    private EntityManagerFactory emf;
    
    public ElementoEducativoSave(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void guardarPersona(Persona persona) {
        if (persona instanceof EstudianteEntity) {
            EstudianteJpaController controller = new EstudianteJpaController(emf);
            controller.create((EstudianteEntity)persona);
        } else if (persona instanceof CatedraticoEntity) {
            CatedraticoJpaController controller = new CatedraticoJpaController(emf);
            controller.create((CatedraticoEntity)persona);
        }
    }

    @Override
    public void guardarElementoAsignatura(ElementoAsignatura elemento) {
        if (elemento instanceof CursoEntity) {
            CursoJpaController controller = new CursoJpaController(emf);
            controller.create((CursoEntity)elemento);
        } else if (elemento instanceof GradoEntity) {
            GradoJpaController controller = new GradoJpaController(emf);
            controller.create((GradoEntity)elemento);
        } else if (elemento instanceof CicloEscolarEntity) {
            CicloEscolarJpaController controller = new CicloEscolarJpaController(emf);
            controller.create((CicloEscolarEntity)elemento);
        }
    }

    @Override
    public void guardarAsignacion(Asignacion asignacion) {
        if (asignacion instanceof AsignacionCursoEntity) {
            AsignacionCursoJpaController controller = new AsignacionCursoJpaController(emf);
            controller.create((AsignacionCursoEntity)asignacion);
        }
    }
    
}
