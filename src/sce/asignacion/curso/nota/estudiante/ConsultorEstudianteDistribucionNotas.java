/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.estudiante;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.curso.nota.orm.ActividadEntity;
import sce.asignacion.curso.nota.orm.ActividadJpaController;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
import sce.asignacion.curso.orm.AsignacionCursoJpaController;
import sce.asignacion.estudiante.orm.AsignacionCursosEstudianteEntity;
import sce.asignacion.estudiante.orm.AsignacionCursosEstudianteJpaController;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class ConsultorEstudianteDistribucionNotas {
    public static ConsultaNotaCurso obtenerNotasActividades(Long idAsignacionEstudianteCurso, EntityManagerFactory emf)
            throws NonexistentEntityException {
        // Validación de que exista la Asignación de Estudiante a Curso
        // @Nota para módulo correspondiente
        // Implementar en sce.asignacion.estudiante.ConsultorAsignacionEstudiante
        
        // Paso 1: Obtención del Entity que contiene la Asignación del Estudiante al Curso que se desea ver
        AsignacionCursosEstudianteEntity asignacionEstudianteCurso = new AsignacionCursosEstudianteJpaController(emf)
                .findAsignacionCursosEstudiante(idAsignacionEstudianteCurso);
        if (asignacionEstudianteCurso == null) {
            throw new NonexistentEntityException("No existe una Asignación de Estudiante a Curso con id="+idAsignacionEstudianteCurso);
        }
        // Paso 2: Obtención del Entity con la Asignación de Curso al que está relacionado las Notas del estudiante
        Long idAsignacionCurso = asignacionEstudianteCurso.getId();
        AsignacionCursoEntity asignacionCurso = new AsignacionCursoJpaController(emf).findAsignacion_Curso(idAsignacionCurso);
        if (asignacionCurso == null) {
            throw new NonexistentEntityException("No existe una Asignación de Curso con id="+idAsignacionCurso);
        }
        // Paso 3: Obtención del listado de Actividades que el catedrático ha creado para la Asignación de Curso
        ConsultaNotaCurso notaCurso = new ConsultaNotaCurso(asignacionCurso.getEsperado());
        List<ActividadEntity> actividades = new ActividadJpaController(emf)
                .buscarPorAsignacionCurso(idAsignacionCurso);
        ArrayList<Long> listaIdActividades = new ArrayList<>();
        for (ActividadEntity actividad : actividades) {
            ConsultaNotaCurso.ConsultaNotaActividad notaActividad = new ConsultaNotaCurso.ConsultaNotaActividad();
            notaActividad.setGrupoActividad(actividad.getGrupo_actividad());
            notaActividad.setNombreActividad(actividad.getActividad());
            notaActividad.setNotaEsperada(actividad.getEsperado());
            notaActividad.setNotaObtenida(0f);
            notaCurso.addNotaActividad(notaActividad);  // Por ahora no tiene la Nota obtenida. Eso se agregará después
            listaIdActividades.add(actividad.getId());
        }
        // Obtención del listado de Notas obtenidas en las actividades por el Estudiante
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