/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso;

import java.util.ArrayList;
import java.util.List;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
import sce.asignacion.curso.orm.AsignacionCursoJpaController;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.excepciones.NonexistentEntityException;
import sce.principal.GestorConexion;

/**
 * Anulación de una Asignación de Curso. Al anular dicha asignación, se debe anular todas las Asignaciones de
 * Estudiantes a Curso que estén relacionadas.
 * @author Usuario
 */
public class AsignacionCursoAnulador {
    private final Long idAsignacionCarrera;
    private Long idAsignacionGrado;
    private final ArrayList<Long> listaIDAsignacionCurso;
    private String razonAnulacion;

    public AsignacionCursoAnulador() {
        idAsignacionCarrera = 0l;
        listaIDAsignacionCurso = new ArrayList<>();
    }
    public AsignacionCursoAnulador(Long idAsignacionCarrera) {
        this.idAsignacionCarrera = idAsignacionCarrera;
        this.listaIDAsignacionCurso = new ArrayList<>();
    }
    public void setIdAsignacionGrado(Long idAsignacionGrado) { this.idAsignacionGrado = idAsignacionGrado; }
    public void setRazonAnulacion(String razonAnulacion) { this.razonAnulacion = razonAnulacion; }
    public void addAsignacionCursoParaAnular(Long idAsignacionEstudiante, String razonAnulacion) { listaIDAsignacionCurso.add(idAsignacionEstudiante); }
    /**
     * En teoría, se llama a este método cuando no hay anulación en cadena
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public void anularAsignacionesCurso()
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        //@Nota para módulo correspondiente
        // CONSULTAR QUE LA ASIGNACIÓN DE CARRERA Y LA ASIGNACIÓN DE GRADO (si se especifica) EXISTAN
        
        if (razonAnulacion.length() == 0) {
            throw new ExcepcionParametrosIncompletos("Especifique la razón de anulación de las Asignaciones de Curso");
        }
        int hashCodeEntityManager = GestorConexion.solicitarEntityManager(GestorConexion.NUEVO_ENTITY_MANAGER).hashCode();
        int miHashCode = this.hashCode();
        GestorConexion.transaccionBEGIN(hashCodeEntityManager, miHashCode);
        int cantidad = listaIDAsignacionCurso.size(), index;
        Long idAsignacionCurso;
        try {
            for(index=0; index<cantidad; index++) {
                idAsignacionCurso = listaIDAsignacionCurso.get(index);
                // Anulación de la Asignación del Curso
                AsignacionCursoAnulador
                        .anularAsignacionCurso(hashCodeEntityManager, idAsignacionCurso, razonAnulacion);
                //@Nota para módulo correspondiente
                // Se procede a anular todas las Asignaciones de Estudiante a Curso de todos los cursos que el estudiante
                // tenga asignado. Esto es una llamada a MÓDULO_ASIGNACION_ESTUDIANTE_CURSO.anularAsignaciones(idAsignacionEstudiante)
            }
        } catch (ExcepcionParametrosIncompletos | NonexistentEntityException ex) {
            GestorConexion.transaccionROLLBACK(hashCodeEntityManager, miHashCode);
            throw ex;
        }
        GestorConexion.transaccionCOMMIT(hashCodeEntityManager, miHashCode);
        GestorConexion.eliminarEntityManager(hashCodeEntityManager, miHashCode);
    }
    /**
     * Anula UNA Asignaciòn de Curso.
     * @param hashCodeEntityManager
     * @param idAsignacionCurso
     * @param razonAnulacion
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public static void anularAsignacionCurso(int hashCodeEntityManager, Long idAsignacionCurso, String razonAnulacion)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        // Se comprueba que la Asignación de Curso exista
        if (idAsignacionCurso == null) {
            throw new ExcepcionParametrosIncompletos("El ID de la Asignación de Curso no puede ser nulo");
        }
        AsignacionCursoJpaController controller = new AsignacionCursoJpaController(GestorConexion.crearEntityManagerFactory());
        AsignacionCursoEntity asignacion = controller.findAsignacion_Curso(idAsignacionCurso);
        if (asignacion == null) {
            throw new NonexistentEntityException("No existe una Asignación de Curso con id="+idAsignacionCurso);
        }
        // Inicio de la Transacción
        int miEntityManagerHashCode = GestorConexion.solicitarEntityManager(hashCodeEntityManager).hashCode();
        int miHashCode = new AsignacionCursoAnulador().hashCode();
        GestorConexion.transaccionBEGIN(miEntityManagerHashCode, miHashCode);
        //@Nota para módulo correspondiente
        // Se procede a anular todas las Asignaciones de Estudiante a Curso de todos los cursos que el estudiante
        // tenga asignado. Esto es una llamada a MÓDULO_ASIGNACION_ESTUDIANTE_CURSO.anularAsignaciones(idAsignacionEstudiante)
        
        //
        
        // Si todas las Asignaciones del Estudiane a Cursos se anularon con éxito, se confirma la Anulación
        GestorConexion.eliminarEntityManager(miEntityManagerHashCode, miHashCode);
    }
    /**
     * Anula las Asignaciones de Estudiante relacionadas a la Asignación de Grado especificada.
     * @param hashCodeEntityManager
     * @param idAsignacionGrado
     * @param idAsignacionCarrea
     * @param razonAnulacion
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public static void anularAsignacionCursosPorAnulacionGrado(int hashCodeEntityManager, Long idAsignacionGrado, Long idAsignacionCarrea, String razonAnulacion)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        if (idAsignacionGrado == null) {
            return;
        }
        // Búsqueda de todas las Asignaciones de Estudiante relacionadas a la Asignación de Grado a anular
        AsignacionCursoJpaController controller = new AsignacionCursoJpaController(GestorConexion.crearEntityManagerFactory());
        List<AsignacionCursoEntity> listaCursos = controller.buscarPorAsignacionGrado(idAsignacionCarrea, idAsignacionGrado);
        
        int miHashCodeEntityManager = GestorConexion.solicitarEntityManager(hashCodeEntityManager).hashCode();
        int miHashCode = new AsignacionCursoAnulador().hashCode();
        GestorConexion.transaccionBEGIN(miHashCodeEntityManager, miHashCode);
        try {
            for (AsignacionCursoEntity curso : listaCursos) {
                anularAsignacionCurso(hashCodeEntityManager, curso.getId(), razonAnulacion);
            }
            GestorConexion.transaccionCOMMIT(miHashCodeEntityManager, miHashCode);
        } catch (ExcepcionParametrosIncompletos | NonexistentEntityException ex) {
            GestorConexion.transaccionROLLBACK(miHashCodeEntityManager, miHashCode);
            throw ex;
        }
    }
    /**
     * Anula las Asignaciones de Estudiante relacionadas a la Asignación de Carrera especificada.
     * @param hashCodeEntityManager
     * @param idAsignacionCarrera 
     * @param razonAnulacion
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public static void anularAsignacionCursosPorAnulacionCarrera(int hashCodeEntityManager, Long idAsignacionCarrera, String razonAnulacion)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        if (idAsignacionCarrera == null) {
            throw new ExcepcionParametrosIncompletos("La Asignación de Carrera con id=null no puede existir");
        } if (razonAnulacion.length() == 0) {
            throw new ExcepcionParametrosIncompletos("Especifique la razón de anulación de las Asignaciones de Estudiante");
        }
        // Búsqueda de todas las Asignaciones de Curso relacionadas a la Asignación de Carrera a anular
        AsignacionCursoJpaController controller = new AsignacionCursoJpaController(GestorConexion.crearEntityManagerFactory());
        List<AsignacionCursoEntity> listaCursos = controller.buscarPorAsignacionCarrera(idAsignacionCarrera);
        
        int miHashCodeEntityManager = GestorConexion.solicitarEntityManager(hashCodeEntityManager).hashCode();
        int miHashCode = new AsignacionCursoAnulador().hashCode();
        GestorConexion.transaccionBEGIN(miHashCodeEntityManager, miHashCode);
        try {
            for (AsignacionCursoEntity curso : listaCursos) {
                anularAsignacionCurso(hashCodeEntityManager, curso.getId(), razonAnulacion);
            }
            GestorConexion.transaccionCOMMIT(miHashCodeEntityManager, miHashCode);
        } catch (ExcepcionParametrosIncompletos | NonexistentEntityException ex) {
            GestorConexion.transaccionROLLBACK(miHashCodeEntityManager, miHashCode);
            throw ex;
        }
    }
}
