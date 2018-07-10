/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante;

import java.util.ArrayList;
import java.util.List;
import sce.asignacion.estudiante.orm.AsignacionEstudianteEntity;
import sce.asignacion.estudiante.orm.AsignacionEstudianteJpaController;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.excepciones.NonexistentEntityException;
import sce.principal.GestorConexion;

/**
 * Anulación de Asignación de Estudiante. Al anular dicha asignación, se debe anular las Asignaciones del Estudiante
 * a Cursos que estén relacionadas.
 * @author Usuario
 */
public class AsignacionEstudianteAnulador {
    private final Long idAsignacionCarrera;
    private Long idAsignacionGrado;
    private final ArrayList<Long> listaIDAsignacionEsutdiantes;
    private final ArrayList<String> listaRazonAnulacion;

    public AsignacionEstudianteAnulador() {
        idAsignacionCarrera = 0l;
        listaIDAsignacionEsutdiantes = new ArrayList<>();
        listaRazonAnulacion = new ArrayList<>();
    }
    public AsignacionEstudianteAnulador(Long idAsignacionCarrera) {
        this.idAsignacionCarrera = idAsignacionCarrera;
        this.listaIDAsignacionEsutdiantes = new ArrayList<>();
        this.listaRazonAnulacion = new ArrayList<>();
    }
    public void setIdAsignacionGrado(Long idAsignacionGrado) { this.idAsignacionGrado = idAsignacionGrado; }
    public void addAsignacionEstudianteParaAnular(Long idAsignacionEstudiante, String razonAnulacion) {
        listaIDAsignacionEsutdiantes.add(idAsignacionEstudiante);
        listaRazonAnulacion.add(razonAnulacion);
    }
    /**
     * En teoría, se llama a este método cuando no hay anulación en cadena
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public void anularAsignacionesEstudiante()
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        //@Nota para módulo correspondiente
        // CONSULTAR QUE LA ASIGNACIÓN DE CARRERA Y LA ASIGNACIÓN DE GRADO (si se especifica) EXISTAN
        int hashCodeEntityManager = GestorConexion.solicitarEntityManager(GestorConexion.NUEVO_ENTITY_MANAGER).hashCode();
        int miHashCode = this.hashCode();
        GestorConexion.transaccionBEGIN(hashCodeEntityManager, miHashCode);
        int cantidad = listaIDAsignacionEsutdiantes.size(), index;
        Long idAsignacionEstudiante;
        String razonAnulacion;
        try {
            for(index=0; index<cantidad; index++) {
                idAsignacionEstudiante = listaIDAsignacionEsutdiantes.get(index);
                razonAnulacion = listaRazonAnulacion.get(index);
                // Anulación de la Asignación de Estudiante
                AsignacionEstudianteAnulador
                        .anularAsignacionEstudianteCurso(hashCodeEntityManager, idAsignacionEstudiante, razonAnulacion);
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
     * Anula UNA Asignaciòn de Estudiante.
     * @param hashCodeEntityManager
     * @param idAsignacionEstudiante
     * @param razonAnulacion
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public static void anularAsignacionEstudianteCurso(int hashCodeEntityManager, Long idAsignacionEstudiante, String razonAnulacion)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        // Se comprueba que la Asignación de Estudiante exista y no esté anulada
        if (idAsignacionEstudiante == null) {
            throw new ExcepcionParametrosIncompletos("El ID de la Asignación de Estudiante no puede ser nulo");
        } if (razonAnulacion.length() == 0) {
            throw new ExcepcionParametrosIncompletos("Especifique la razón de anulación de la Asignación de Estudiante con id="+idAsignacionEstudiante);
        }
        AsignacionEstudianteJpaController controller = new AsignacionEstudianteJpaController(GestorConexion.crearEntityManagerFactory());
        AsignacionEstudianteEntity asignacion = controller.findAsignacion_Estudiante(idAsignacionEstudiante);
        if (asignacion == null) {
            throw new NonexistentEntityException("No existe una Asignación de Estudiante con id="+idAsignacionEstudiante);
        } if (asignacion.getAnulado()) {
            System.err.println("La Asigación de Estudiante con id="+idAsignacionEstudiante+" ya ha sido anulada");
        }
        // Inicio de la Transacción
        int miEntityManagerHashCode = GestorConexion.solicitarEntityManager(hashCodeEntityManager).hashCode();
        int miHashCode = new AsignacionEstudianteAnulador().hashCode();
        GestorConexion.transaccionBEGIN(miEntityManagerHashCode, miHashCode);
        //@Nota para módulo correspondiente
        // Se procede a anular todas las Asignaciones de Estudiante a Curso de todos los cursos que el estudiante
        // tenga asignado. Esto es una llamada a MÓDULO_ASIGNACION_ESTUDIANTE_CURSO.anularAsignaciones(idAsignacionEstudiante)
        
        //
        
        // Si todas las Asignaciones del Estudiane a Cursos se anularon con éxito, se procede a anular la
        // Asignación de Estudiante actual
        asignacion.setAnulado(true);
        asignacion.setRazon_anulacion(razonAnulacion);
        try {
            controller.edit(asignacion);
            GestorConexion.transaccionCOMMIT(miEntityManagerHashCode, miHashCode);
        } catch (Exception ex) {
            GestorConexion.transaccionROLLBACK(miEntityManagerHashCode, miHashCode);
            throw new NonexistentEntityException(ex.getMessage());
        }
        GestorConexion.eliminarEntityManager(miEntityManagerHashCode, miHashCode);
    }
    /**
     * Anula las Asignaciones de Estudiante relacionadas a la Asignación de Grado especificada.
     * @param hashCodeEntityManager
     * @param idAsignacionGrado
     * @param razonAnulacion
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public static void anularAsignacionEstudiantesPorAnulacionGrado(int hashCodeEntityManager, Long idAsignacionGrado, String razonAnulacion)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        if (idAsignacionGrado == null) {
            return;
        } if (razonAnulacion.length() == 0) {
            throw new ExcepcionParametrosIncompletos("Especifique la razón de anulación de las Asignaciones de Estudiante");
        }
        // Búsqueda de todas las Asignaciones de Estudiante relacionadas a la Asignación de Grado a anular
        AsignacionEstudianteJpaController controller = new AsignacionEstudianteJpaController(GestorConexion.crearEntityManagerFactory());
        List<AsignacionEstudianteEntity> listaEstudiantes = controller.buscarPorAsignacionGrado(idAsignacionGrado);
        
        int miHashCodeEntityManager = GestorConexion.solicitarEntityManager(hashCodeEntityManager).hashCode();
        int miHashCode = new AsignacionEstudianteAnulador().hashCode();
        GestorConexion.transaccionBEGIN(miHashCodeEntityManager, miHashCode);
        try {
            for (AsignacionEstudianteEntity estudiante : listaEstudiantes) {
                anularAsignacionEstudianteCurso(hashCodeEntityManager, estudiante.getId(), razonAnulacion);
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
    public static void anularAsignacionEstudiantesPorAnulacionCarrera(int hashCodeEntityManager, Long idAsignacionCarrera, String razonAnulacion)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        if (idAsignacionCarrera == null) {
            throw new ExcepcionParametrosIncompletos("La Asignación de Carrera con id=null no puede existir");
        } if (razonAnulacion.length() == 0) {
            throw new ExcepcionParametrosIncompletos("Especifique la razón de anulación de las Asignaciones de Estudiante");
        }
        // Búsqueda de todas las Asignaciones de Estudiante relacionadas a la Asignación de Carrera a anular
        AsignacionEstudianteJpaController controller = new AsignacionEstudianteJpaController(GestorConexion.crearEntityManagerFactory());
        List<AsignacionEstudianteEntity> listaEstudiantes = controller.buscarPorAsignacionCarrera(idAsignacionCarrera);
        
        int miHashCodeEntityManager = GestorConexion.solicitarEntityManager(hashCodeEntityManager).hashCode();
        int miHashCode = new AsignacionEstudianteAnulador().hashCode();
        GestorConexion.transaccionBEGIN(miHashCodeEntityManager, miHashCode);
        try {
            for (AsignacionEstudianteEntity estudiante : listaEstudiantes) {
                anularAsignacionEstudianteCurso(hashCodeEntityManager, estudiante.getId(), razonAnulacion);
            }
            GestorConexion.transaccionCOMMIT(miHashCodeEntityManager, miHashCode);
        } catch (ExcepcionParametrosIncompletos | NonexistentEntityException ex) {
            GestorConexion.transaccionROLLBACK(miHashCodeEntityManager, miHashCode);
            throw ex;
        }
    }
}