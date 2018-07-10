/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso;

import java.util.Objects;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.asignacion.curso.orm.AsignacionCursoJpaController;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;

/**
 * LISTO!!! Clase consultora para verificar si existe una Asignación de Curso, y si está anulada.
 * @author Usuario
 */
public class ConsultorAsignacionCurso {
    private static boolean validarConexion(EntityManagerFactory emf) {
        if (emf == null) {
            return false;
        }
        return emf.isOpen();
    }
    /**
     * Verifica si una Asignación de Curso existe, que idAsignacionCurso != null, que emf != null y
     * que emf esté abierta.
     * @param idAsignacionCurso el ID de la Asignación de Curso que se desea consultar.
     * @param emf
     * @return true si pasa las verificaciones; false si no pasa alguna.
     */
    public static boolean existeAsignacionCurso(EntityManagerFactory emf, Long idAsignacionCurso) {
        if (idAsignacionCurso == null) {
            return false;
        } if (!validarConexion(emf)) {
            return false;
        }
        AsignacionCursoEntity asigCurso = new AsignacionCursoJpaController(emf)
                .findAsignacion_Curso(idAsignacionCurso);
        return asigCurso != null;
    }
    /**
     * Verifica si una Asignación de Curso está anulada. Para ello, hace una consulta a los módulos de Asignación
     * de Grado (si tiene relacionado alguno) y Asignación de Carrera para verificar que éstos no estén anulados.
     * @param idAsignacionCurso el ID de la Asignación de Curso que se desea consultar.
     * @param emf
     * @return true si !existeAsignacionCurso(...) y la Asignación de curso no está anulada; false si no se cumple alguno.
     */
    public static boolean esAsignacionCursoAnulada(EntityManagerFactory emf, Long idAsignacionCurso) {
        if (!existeAsignacionCurso(emf, idAsignacionCurso)) {
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
    public static boolean validarAsignacionCurso(EntityManagerFactory emf, Long idAsignacionCurso, Long idAsignacionCarrera, Long idAsignacionGrado) {
        if (idAsignacionCurso== null || idAsignacionCarrera==null) {
            return false;
        }
        AsignacionCursoEntity asignacionCurso = new AsignacionCursoJpaController(emf).findAsignacion_Curso(idAsignacionCurso);
        return Objects.equals(asignacionCurso.getAsignacion_carrera_id(), idAsignacionCarrera) && Objects.equals(asignacionCurso.getAsignacion_grado_id(), idAsignacionGrado);
    }
}