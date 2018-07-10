/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante;

import java.util.ArrayList;
import java.util.List;
import sce.asignacion.estudiante.orm.AsignacionCursosEstudianteEntity;
import sce.asignacion.estudiante.orm.AsignacionCursosEstudianteJpaController;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.excepciones.NonexistentEntityException;
import sce.principal.GestorConexion;

/**
 *
 * @author Usuario
 */
public class AsignacionEstudianteCursoAnulador {
    private final Long idAsignacionCurso;
    private final Long idAsignacionEstudiante;
    private final ArrayList<Long> listaIDAsignacionEsutdiantesCurso;
    private final ArrayList<String> listaRazonAnulacion;

    public AsignacionEstudianteCursoAnulador() {
        idAsignacionCurso = idAsignacionEstudiante = 0l;
        listaIDAsignacionEsutdiantesCurso = new ArrayList<>();
        listaRazonAnulacion = new ArrayList<>();
    }
    public AsignacionEstudianteCursoAnulador(Long idAsignacionCurso, Long idAsigancionEstudiante) {
        this.idAsignacionCurso = idAsignacionCurso;
        this.idAsignacionEstudiante = idAsigancionEstudiante;
        this.listaIDAsignacionEsutdiantesCurso = new ArrayList<>();
        this.listaRazonAnulacion = new ArrayList<>();
    }
    public void addAsignacionEstudianteCursoParaAnular(Long idAsignacionEstudianteCurso, String razonAnulacion) {
        listaIDAsignacionEsutdiantesCurso.add(idAsignacionEstudianteCurso);
        listaRazonAnulacion.add(razonAnulacion);
    }
    /**
     * En teoría, se llama a este método cuando no hay anulación en cadena
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public void anularAsignacionesEstudiante()
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        int hashCodeEntityManager = GestorConexion.solicitarEntityManager(GestorConexion.NUEVO_ENTITY_MANAGER).hashCode();
        int miHashCode = this.hashCode();
        GestorConexion.transaccionBEGIN(hashCodeEntityManager, miHashCode);
        int cantidad = listaIDAsignacionEsutdiantesCurso.size(), index;
        Long idAsignacionEstudianteCurso;
        String razonAnulacion;
        try {
            for(index=0; index<cantidad; index++) {
                idAsignacionEstudianteCurso = listaIDAsignacionEsutdiantesCurso.get(index);
                razonAnulacion = listaRazonAnulacion.get(index);
                // Anulación de la Asignación de Estudiante a Curso
                AsignacionEstudianteCursoAnulador
                        .anularAsignacionEstudianteCurso(hashCodeEntityManager, idAsignacionEstudiante, idAsignacionCurso, razonAnulacion);
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
     * @param idAsignacionCurso
     * @param razonAnulacion
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public static void anularAsignacionEstudianteCurso(int hashCodeEntityManager, Long idAsignacionEstudiante, Long idAsignacionCurso, String razonAnulacion)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        // Se comprueba que la Asignación de Estudiante exista y no esté anulada
        if (idAsignacionEstudiante == null) {
            throw new ExcepcionParametrosIncompletos("El ID de la Asignación de Estudiante no puede ser nulo");
        } if (razonAnulacion.length() == 0) {
            throw new ExcepcionParametrosIncompletos("Especifique la razón de anulación de la Asignación de Estudiante con id="+idAsignacionEstudiante);
        }
        AsignacionCursosEstudianteJpaController controller = new AsignacionCursosEstudianteJpaController(GestorConexion.crearEntityManagerFactory());
        AsignacionCursosEstudianteEntity asignacion = controller.buscarPorEstudianteCurso(idAsignacionEstudiante, idAsignacionCurso);
        if (asignacion == null) {
            throw new NonexistentEntityException("No existe una Asignación de Estudiante con id="+idAsignacionEstudiante+" relacionada con una Asignación de Curso con id="+idAsignacionCurso);
        } if (asignacion.getAnulado()) {
            System.err.println("La Asigación de Estudiante a Curso con id="+idAsignacionEstudiante+" ya ha sido anulada");
        }
        // Inicio de la Transacción
        int miEntityManagerHashCode = GestorConexion.solicitarEntityManager(hashCodeEntityManager).hashCode();
        int miHashCode = new AsignacionEstudianteCursoAnulador().hashCode();
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
     * Anula las Asignaciones de Estudiante a Curso relacionadas a la Asignación de Estudiante especificada.
     * @param hashCodeEntityManager
     * @param idAsignacionEstudianteCurso
     * @param razonAnulacion
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public static void anularAsignacionEstudianteCursoPorAnulacionEstudiante(int hashCodeEntityManager, Long idAsignacionEstudianteCurso, String razonAnulacion)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        if (idAsignacionEstudianteCurso == null) {
            return;
        } if (razonAnulacion.length() == 0) {
            throw new ExcepcionParametrosIncompletos("Especifique la razón de anulación de las Asignaciones de Estudiantes a Curso");
        }
        // Búsqueda de todas las Asignaciones de Estudiante a Curso relacionadas a la Asignación de Estudiante a anular
        AsignacionCursosEstudianteJpaController controller = new AsignacionCursosEstudianteJpaController(GestorConexion.crearEntityManagerFactory());
        List<AsignacionCursosEstudianteEntity> listaEstudiantesCurso = controller.buscarPorAsignacionEstudiante(idAsignacionEstudianteCurso);
        
        int miHashCodeEntityManager = GestorConexion.solicitarEntityManager(hashCodeEntityManager).hashCode();
        int miHashCode = new AsignacionEstudianteCursoAnulador().hashCode();
        GestorConexion.transaccionBEGIN(miHashCodeEntityManager, miHashCode);
        try {
            for (AsignacionCursosEstudianteEntity estudiantesCurso : listaEstudiantesCurso) {
                anularAsignacionEstudianteCurso(hashCodeEntityManager, idAsignacionEstudianteCurso, estudiantesCurso.getAsignacion_curso_id(), razonAnulacion);
            }
            GestorConexion.transaccionCOMMIT(miHashCodeEntityManager, miHashCode);
        } catch (ExcepcionParametrosIncompletos | NonexistentEntityException ex) {
            GestorConexion.transaccionROLLBACK(miHashCodeEntityManager, miHashCode);
            throw ex;
        }
    }
    /**
     * Anula las Asignaciones de Estudiante a Curso relacionadas a la Asignación de Curso especificada.
     * @param hashCodeEntityManager
     * @param idAsignacionCurso 
     * @param razonAnulacion
     * @throws ExcepcionParametrosIncompletos
     * @throws NonexistentEntityException 
     */
    public static void anularAsignacionEstudiantesPorAnulacionCurso(int hashCodeEntityManager, Long idAsignacionCurso, String razonAnulacion)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException {
        if (idAsignacionCurso == null) {
            throw new ExcepcionParametrosIncompletos("La Asignación de Carrera con id=null no puede existir");
        } if (razonAnulacion.length() == 0) {
            throw new ExcepcionParametrosIncompletos("Especifique la razón de anulación de las Asignaciones de Estudiante");
        }
        // Búsqueda de todas las Asignaciones de Estudiante relacionadas a la Asignación de Carrera a anular
        AsignacionCursosEstudianteJpaController controller = new AsignacionCursosEstudianteJpaController(GestorConexion.crearEntityManagerFactory());
        List<AsignacionCursosEstudianteEntity> listaEstudiantesCurso = controller.buscarPorAsignacionCurso(idAsignacionCurso);
        
        int miHashCodeEntityManager = GestorConexion.solicitarEntityManager(hashCodeEntityManager).hashCode();
        int miHashCode = new AsignacionEstudianteCursoAnulador().hashCode();
        GestorConexion.transaccionBEGIN(miHashCodeEntityManager, miHashCode);
        try {
            for (AsignacionCursosEstudianteEntity estudianteCurso : listaEstudiantesCurso) {
                anularAsignacionEstudianteCurso(hashCodeEntityManager, estudianteCurso.getAsignacion_estudiante_id(), idAsignacionCurso, razonAnulacion);
            }
            GestorConexion.transaccionCOMMIT(miHashCodeEntityManager, miHashCode);
        } catch (ExcepcionParametrosIncompletos | NonexistentEntityException ex) {
            GestorConexion.transaccionROLLBACK(miHashCodeEntityManager, miHashCode);
            throw ex;
        }
    }
}
