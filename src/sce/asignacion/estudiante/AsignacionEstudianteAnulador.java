/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.estudiante.orm.AsignacionEstudianteEntity;
import sce.asignacion.estudiante.orm.AsignacionEstudianteJpaController;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.excepciones.NonexistentEntityException;
import sce.principal.EMF;

/**
 *
 * @author Usuario
 */
public class AsignacionEstudianteAnulador {
    private final EntityManagerFactory emf;
    private final Long idAsignacionCarrera;
    private Long idAsignacionGrado;
    private final ArrayList<Long> listaIDEsutdiantes;
    private final ArrayList<String> listaRazonAnulacion;

    public AsignacionEstudianteAnulador(EntityManagerFactory emf, Long idAsignacionCarrera) {
        this.emf = emf;
        this.idAsignacionCarrera = idAsignacionCarrera;
        this.listaIDEsutdiantes = new ArrayList<>();
        this.listaRazonAnulacion = new ArrayList<>();
    }
    public void setIdAsignacionGrado(Long idAsignacionGrado) { this.idAsignacionGrado = idAsignacionGrado; }
    public void addEstudianteParaAnular(Long idAsignacionEstudiante, String id) {
        //
    }
    public static void anularAsignacion(EntityManagerFactory emf, Long idAsignacionEstudiante, String razonAnulacion)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        // Se comprueba que la Asignación de Estudiante exista y no esté anulada
        if (idAsignacionEstudiante == null) {
            throw new ExcepcionParametrosIncompletos("El ID de la Asignación de Estudiante no puede ser nulo");
        }
        AsignacionEstudianteJpaController controller = new AsignacionEstudianteJpaController(emf);
        AsignacionEstudianteEntity asignacion = controller.findAsignacion_Estudiante(idAsignacionEstudiante);
        if (asignacion == null) {
            throw new NonexistentEntityException("No existe una Asignación de Estudiante con id="+idAsignacionEstudiante);
        }
        //@Nota para módulo correspondiente
        // Se procede a anular todas las Asignaciones de Estudiante a Curso de todos los cursos que el estudiante
        // tenga asignado. Esto es una llamada a MÓDULO_ASIGNACION_ESTUDIANTE_CURSO.anularAsignaciones(idAsignacionEstudiante)
        
        // Si todas las Asignaciones del Estudiane a Cursos se anularon con éxito, se procede a anular la
        // Asignación de Estudiante actual
        asignacion.setAnulado(true);
        asignacion.setRazon_anulacion(razonAnulacion);
        try {
            controller.edit(asignacion);
        } catch (Exception ex) {
            throw new NonexistentEntityException(ex.getMessage());
        }
    }
}