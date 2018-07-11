/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.estudiante;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
//import sce.asignacion.curso.ConsultorAsignacionCurso;
import sce.asignacion.curso.nota.orm.ActividadEntity;
import sce.asignacion.curso.nota.orm.ActividadJpaController;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
import sce.asignacion.curso.orm.AsignacionCursoJpaController;
import sce.asignacion.estudiante.orm.AsignacionCursosEstudianteEntity;
import sce.asignacion.estudiante.orm.AsignacionCursosEstudianteJpaController;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.excepciones.NonexistentEntityException;

/**
 * LISTO!!! Clase que será utilizada por un Estudiante para consultar la Distribución de Notas de un Curso al que
 * está asignado. Dicha ConsultaNotaCurso contiene todas las actividades y la nota obtenida en cada una, así como
 * un acumulado de la nota de todas las actividades. Este registro es sólo de consulta, por lo que no contiene ID's.
 * @author Usuario
 */
public class ConsultorEstudianteNotasCurso {
    /**
     * Devuelve las Notas de un Curso al que está asignado un Estudiante. Estas notas están en contraste a las
     * actividades que el catedrático ha definido, además que sólo es para consulta.
     * @param idAsignacionEstudianteCurso el ID de la Asignación de Estudiante a Curso del que se mostrarán las notas.
     * @param emf
     * @return
     * @throws NonexistentEntityException se lanza si no existe la Asignación de Estudiante a Curso o la Asignación
     * de Curso.
     * @throws ExcepcionParametrosIncompletos se lanza si idAsignacionEstudianteCurso == null
     */
    public static ConsultaNotaCurso obtenerNotasActividadesCurso(Long idAsignacionEstudianteCurso, EntityManagerFactory emf)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        // Validación de que exista la Asignación de Estudiante a Curso
        // @Nota para módulo correspondiente
        // Implementar en sce.asignacion.estudiante.ConsultorAsignacionEstudiante
        if (idAsignacionEstudianteCurso == null) {
            throw new ExcepcionParametrosIncompletos("El ID de la Asignación de Estudiante a Curso no puede ser nulo");
        }
        // Obtención del Entity que contiene la Asignación del Estudiante al Curso que se desea ver
        AsignacionCursosEstudianteEntity asignacionEstudianteCurso = new AsignacionCursosEstudianteJpaController(emf)
                .findAsignacionCursosEstudiante(idAsignacionEstudianteCurso);
        if (asignacionEstudianteCurso == null) {
            throw new NonexistentEntityException("No existe una Asignación de Estudiante a Curso con id="+idAsignacionEstudianteCurso);
        }
        // No se verifica si la Asignación de Estudiante a Curso está anulada, ya que sólo se mostrarán las Notas
        
        // Obtención del Entity con la Asignación de Curso al que está relacionado las Notas del estudiante
        Long idAsignacionCurso = asignacionEstudianteCurso.getId();
        /*if (!ConsultorAsignacionCurso.existeAsignacionCurso(emf, idAsignacionCurso)) {
            throw new NonexistentEntityException("No existe una Asignación de Curso con id="+idAsignacionCurso+" para la Asignación del Estudiante a Curso con id="+idAsignacionEstudianteCurso);
        }*/
        // No se verifica si la Asignación de Curso está anulada, ya que sólo se mostrán las Notas
        AsignacionCursoEntity asignacionCurso = new AsignacionCursoJpaController(emf)
                .findAsignacion_Curso(idAsignacionCurso);
        
        // Obtención del listado de Actividades que el catedrático ha creado para la Asignación de Curso
        ConsultaNotaCurso notaCurso = new ConsultaNotaCurso(asignacionCurso.getEsperado());
        List<ActividadEntity> actividades = new ActividadJpaController(emf)
                .buscarPorAsignacionCurso(idAsignacionCurso);
        ArrayList<Long> listaIdActividades = new ArrayList<>();
        for (ActividadEntity actividad : actividades) {
            ConsultaNotaCurso.ConsultaNotaActividadEstudiante notaActividad = new ConsultaNotaCurso.ConsultaNotaActividadEstudiante(actividad);
            notaCurso.addNotaActividad(notaActividad);  // Por ahora no tiene la Nota obtenida. Eso se agregará después
            listaIdActividades.add(actividad.getId());
        }
        // Obtención del listado de Notas obtenidas en las actividades por el Estudiante
        // En teoría, hay la misma cantidad que actividades definidas por el Catedrático
        List<NotaActividadEntity> notaActividades = new NotaActividadJpaController(emf)
                .buscarPorAsignacionEstudianteCurso(asignacionEstudianteCurso.getId());
        int index;
        for (NotaActividadEntity notaActividad : notaActividades) {
            index = listaIdActividades.indexOf(notaActividad.getActividad_id());
            notaCurso.setNotaObtenida(index, notaActividad.getObtenido());
        }
        return notaCurso;
    }
}