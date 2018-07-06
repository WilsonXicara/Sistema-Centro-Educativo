/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
import sce.asignacion.curso.orm.AsignacionCursoJpaController;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.excepciones.ExcepcionEntityAnulado;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.excepciones.NonexistentEntityException;
import sce.principal.elemento_asignatura.curso.orm.CursoEntity;
import sce.principal.elemento_asignatura.curso.orm.CursoJpaController;

/**
 *
 * @author Usuario
 */
public class AsignacionCursoBuscador {
    /**
     * 
     * @param emf
     * @param idAsignacionCarrera
     * @return
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException
     * @throws ExcepcionEntityAnulado 
     */
    public static ConsultaAsignacionCurso obtenerCursoAsignadosACarrera(EntityManagerFactory emf, Long idAsignacionCarrera)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        evaluarParametros(emf, idAsignacionCarrera, null);
        ConsultaAsignacionCurso consultaCurso = new ConsultaAsignacionCurso(emf, idAsignacionCarrera);
        return consultaCurso;
    }
    public static ConsultaAsignacionCurso obtenerCursosRelacionadosAGrado(EntityManagerFactory emf, Long idAsignacionCarrera, Long idAsignacionGrado)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        evaluarParametros(emf, idAsignacionCarrera, idAsignacionGrado);
        ConsultaAsignacionCurso consultaCurso = new ConsultaAsignacionCurso(emf, idAsignacionCarrera, idAsignacionGrado);
        return consultaCurso;
    }
    private static void evaluarParametros(EntityManagerFactory emf, Long idAsignacionCarrera, Long idAsignacionGrado)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        // Validación de que los datos proporcionados sean correctos
        // @Nota para módulo correspondiente
        // Verificar que la Asignación de Carrera exista y no esté anulada
        if (idAsignacionCarrera == null) {
            throw new ExcepcionParametrosIncompletos("El ID de la Asignación de Carrera no puede ser nula");
        }
        AsignacionCarreraEntity asignacionCarrera = new AsignacionCarreraJpaController(emf)
                .findAsignacionCarreraEntity(idAsignacionCarrera);
        if (asignacionCarrera == null) {
            throw new NonexistentEntityException("No existe una Asignación de Carrera con id="+idAsignacionCarrera);
        } if (asignacionCarrera.getAnulado()) {
            throw new ExcepcionEntityAnulado("La Asignación de Carrera con id="+idAsignacionCarrera+" ya ha sido anulada");
        }
        // @Nota para módulo correspondiente
        // Si se proporciona una Asignación de Grado, verificar que exista y no esté anulada
        if (idAsignacionGrado != null) {
            AsignacionGradoEntity asignacionGrado = new AsignacionGradoJpaController(emf)
                    .findAsignacion_Grado(idAsignacionGrado);
            if (asignacionGrado == null) {
                throw new NonexistentEntityException("No existe una Asignación de Grado con id="+idAsignacionGrado);
            } if (asignacionGrado.getAnulado()) {
                throw new ExcepcionEntityAnulado("La Asignación de Grado con id="+idAsignacionGrado+" ya ha sido anulada");
            }
        }
    }
    /**
     * Contenedor de información básica acerca de las Asignaciones de Cursos a una Asignación Carrera y/o una
     * Asignación de Grado (si se proporciona) especificados. Cada Asignación de Curso se representa en un
     * {@code DetalleAsignacionCurso}, y en conjunto representan la información necesaria para utilizar los valores
     * de esta instancia en una Asignación de Estudiantes. Una instancia de esta clase no evalúa que los parámetros
     * sean correctos (los ID´s las asignaciones o que dichas asignaciones existan y no estén anuladas).
     */
    public static class ConsultaAsignacionCurso {
        private final Long idAsignacionCarrera, idAsignacionGrado;
        private final ArrayList<DetalleAsignacionCurso> listaAsignacionCursos;
        
        public ConsultaAsignacionCurso(EntityManagerFactory emf, Long idAsignacionCarrera) {
            this.idAsignacionCarrera = idAsignacionCarrera;
            this.idAsignacionGrado = null;
            this.listaAsignacionCursos = new ArrayList<>();
            buscarAsignacionCursos(emf);
        }
        public ConsultaAsignacionCurso(EntityManagerFactory emf, Long idAsignacionCarrera, Long idAsignacionGrado) {
            this.idAsignacionCarrera = idAsignacionCarrera;
            this.idAsignacionGrado = idAsignacionGrado;
            this.listaAsignacionCursos = new ArrayList<>();
            buscarAsignacionCursos(emf);
        }
        public Long getIdAsignacionCarrera() { return idAsignacionCarrera; }
        public Long getIdAsignacionGrado() { return idAsignacionGrado; }
        /**
         * Obtiene un Detalle de una Asignación de Curso almacenado en la instancia actual.
         * @param index el índice del detalle que se desea obtener.
         * @return el {@link DetalleAsignacionCurso} en el índice especificado; null si index está fuera de [0,listaDetalles.size()]
         */
        public DetalleAsignacionCurso getDetalleAsignacionCurso(int index) {
            if (index<0 || index>=listaAsignacionCursos.size()) {
                return null;
            }
            return listaAsignacionCursos.get(index);
        }
        /**
         * Obtiene el listado de Asignaciones de Curso relacionados a la Asignación de Carrerra y/o la Asignación
         * de Grado especificados en el contructor.
         * @param emf 
         */
        private void buscarAsignacionCursos(EntityManagerFactory emf) {
            // Obtención del listado de todos los cursos existentes
            List<CursoEntity> cursosExistentes = new CursoJpaController(emf).findCursoEntityEntities();
            ArrayList<Long> listaIDCursosExistentes = new ArrayList<>();
            for (CursoEntity cursoExistente : cursosExistentes) {
                listaIDCursosExistentes.add(cursoExistente.getId());
            }
            
            // Obtención de las Asignaciones de Curso relacionados a las Asignaciones de Carrera y Grado especificados
            AsignacionCursoJpaController controller = new AsignacionCursoJpaController(emf);
            List<AsignacionCursoEntity> cursosAsignados;
            cursosAsignados = (idAsignacionGrado == null)
                    ? controller.buscarPorAsignacionCarrera(idAsignacionCarrera)
                    : controller.buscarPorAsignacionGrado(idAsignacionCarrera, idAsignacionGrado);
            for (AsignacionCursoEntity cursoAsignado : cursosAsignados) {
                DetalleAsignacionCurso nuevo = new DetalleAsignacionCurso(
                        cursoAsignado.getId(),  // idAsignacionCurso
                        cursoAsignado.getCurso_id(),    // idCurso
                        listaAsignacionCursos.get(
                                listaIDCursosExistentes.indexOf(cursoAsignado.getCurso_id())
                        ).getNombreCurso()      // nombreCurso
                );
                listaAsignacionCursos.add(nuevo);
            }
        }
    }
    /**
     * Clase que contiene información básica de una Asignación de Curso. En conjunto con {@link ConsultaAsignacionCurso}
     * es útil para mostrar información
     */
    public static class DetalleAsignacionCurso {
        private final Long idAsignacionCurso, idCurso;
        private final String nombreCurso;
        
        public DetalleAsignacionCurso(Long idAsignacionCurso, Long idCurso, String nombreCurso) {
            this.idAsignacionCurso = idAsignacionCurso;
            this.idCurso = idCurso;
            this.nombreCurso = nombreCurso;
        }

        public Long getIdAsignacionCurso() { return idAsignacionCurso; }
        public Long getIdCurso() { return idCurso; }
        public String getNombreCurso() { return nombreCurso; }
    }
}