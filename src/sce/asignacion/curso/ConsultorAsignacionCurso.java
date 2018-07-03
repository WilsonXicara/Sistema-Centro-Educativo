/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso;

import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.asignacion.curso.orm.AsignacionCursoJpaController;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;

/**
 *
 * @author Usuario
 */
public class ConsultorAsignacionCurso {
    public static boolean existeAsignacionCurso(Long idAsignacionCurso, EntityManagerFactory emf) {
        if (idAsignacionCurso == null) {
            return false;
        } if (emf == null) {
            return false;
        } if (!emf.isOpen()) {
            return false;
        }
        AsignacionCursoEntity asigCurso = new AsignacionCursoJpaController(emf)
                .findAsignacion_Curso(idAsignacionCurso);
        return !(asigCurso == null);
    }
    public static boolean esAsignacionCursoAnulada(Long idAsignacionCurso, EntityManagerFactory emf) {
        if (!existeAsignacionCurso(idAsignacionCurso, emf)) {
            return true;
        }
        AsignacionCursoEntity asigCurso = new AsignacionCursoJpaController(emf)
                .findAsignacion_Curso(idAsignacionCurso);
        // Una Asignación Curso está anulada si la Asignación Carrera o la Asignación Grado (si lo hay) está anulada
        Long idAsignacionSuperior = asigCurso.getAsignacion_carrera_id();
        // @Nota para módulo correspondiente
        AsignacionCarreraEntity asigCarrera = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntity(idAsignacionSuperior);
        if (asigCarrera == null) {
            return true;
        } if (asigCarrera.getAnulado()) {
            return true;
        }
        idAsignacionSuperior = asigCurso.getAsignacion_grado_id();
        if (idAsignacionSuperior != null) {
            // @Nota para módulo correspondiente
            AsignacionGradoEntity asigGrado = new AsignacionGradoJpaController(emf).findAsignacion_Grado(idAsignacionSuperior);
            if (asigGrado == null) {
                return true;
            } if (asigGrado.getAnulado()) {
                return true;
            }
        }
        return false;
    }
}