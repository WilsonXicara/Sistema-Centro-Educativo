/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.catedratico;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.curso.nota.estudiante.NotaActividadEntity;
import sce.asignacion.curso.nota.orm.ActividadEntity;

/**
 * Clase para obtener las Notas de todos los Estudiantes (asignados a la Asignación de Curso especificada) para una
 * sola Actividad de la Distribución de Notas de la Asignación de Curso.
 * @author Usuario
 */
public class CatedraticoConsultaNotasActividad {
    private final EntityManagerFactory emf;
    private ActividadEntity actividad;
    private ArrayList<NotaActividadEntity> notasEstudiantes;
    private ArrayList<Long> listaIDNotaActividad;   // Para hacer más rápida la búsqueda
    private ArrayList<Boolean> listaNotaActividadModificada;
    
    public CatedraticoConsultaNotasActividad(Long idAsignacionEstudianteCurso, Long idActividad, EntityManagerFactory emf) {
        this.emf = emf;
    }
}
