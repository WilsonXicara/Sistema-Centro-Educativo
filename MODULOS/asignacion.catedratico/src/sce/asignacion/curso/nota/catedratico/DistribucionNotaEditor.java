/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.catedratico;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
//import sce.asignacion.curso.ConsultorAsignacionCurso;
import sce.asignacion.curso.nota.estudiante.NotaActividadEntity;
import sce.asignacion.curso.nota.estudiante.NotaActividadJpaController;
import sce.asignacion.curso.nota.orm.ActividadEntity;
import sce.asignacion.curso.nota.orm.ActividadJpaController;
import sce.excepciones.ExcepcionEntityAnulado;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.excepciones.NonexistentEntityException;

/**
 * LISTO!!! Clase utilizada por un Catedrático para crear, editar o actualizar la Distribución de notas para una
 * Asignación de Curso que tenga asignada.
 * Cuando elimina actividades, se elimina las Notas de Actividades de todos los Estudiantes que estén asignados a la
 * Asignación de Curso.
 * Cuando crea o edita actividades, dichos cambios no se reflejan en las Notas de actividades de los Estudiantes
 * asignados, sino que se debe hacerse desde la Asignación de Notas a los Estudiante por cada curso.
 * Se implementa un estilo propio para manejar las Transacciones.
 * @author Usuario
 */
public class DistribucionNotaEditor {
    private final EntityManagerFactory emf;
    private final Long idAsignacionCurso;
    private final ArrayList<ActividadEntity> listaActividades, listaActividadesEliminadas;
    private final ArrayList<Boolean> listaActividadModificada;
    
    /**
     * Crea una nueva instancia para crear, editar o eliminar la Distribución de Notas para una Asignación de Curso
     * existente y no anulada.
     * @param emf
     * @param idAsignacionCurso el ID de la Asignación de Curso del que el Catedrático desea obtener la Distribución de notas.
     * @throws ExcepcionParametrosIncompletos se lanza si emf == null, emf está cerrada o idAsignacionCurso == null
     * @throws NonexistentEntityException se lanza si no existe una Asignación de Curso con ID = idAsignacionCurso
     * @throws ExcepcionEntityAnulado se lanza si la Asignación de Curso ya está anulada (y por lo tanto, no se
     * pueden modificar las Notas). Esta anulación es entendida como finalización del Curso.
     */
    public DistribucionNotaEditor(EntityManagerFactory emf, Long idAsignacionCurso)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        this.emf = emf;
        this.idAsignacionCurso = idAsignacionCurso;
        evaluarParametros();
        // Obtención de las Actividades asociadas a la Asignación de Curso
        List<ActividadEntity> actividades = new ActividadJpaController(emf).buscarPorAsignacionCurso(idAsignacionCurso);
        this.listaActividades = new ArrayList<>();
        this.listaActividadesEliminadas = new ArrayList<>();
        this.listaActividadModificada = new ArrayList<>();
        for (ActividadEntity actividad : actividades) {
            this.listaActividades.add(actividad);
            this.listaActividadModificada.add(false);
        }
    }
    /**
     * Evalúa que los parámetros proporcionados sean correctos y que la Asignación de Curso sea válida.
     * @throws ExcepcionParametrosIncompletos se lanza si emf == null, emf está cerrada o idAsignacionCurso == null
     * @throws NonexistentEntityException se lanza si no existe una Asignación de Curso con ID = idAsignacionCurso
     * @throws ExcepcionEntityAnulado se lanza si la Asignación de Curso ya está anulada (y por lo tanto, no se
     * pueden modificar las Notas). Esta anulación es entendida como finalización del Curso.
     */
    private void evaluarParametros()
            throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        if (emf == null) {
            throw new ExcepcionParametrosIncompletos("No se ha proporcionado una conexión válida");
        } if (!emf.isOpen()) {
            throw new ExcepcionParametrosIncompletos("La conexión ya ha sido cerrada");
        } if (idAsignacionCurso == null) {
            throw new ExcepcionParametrosIncompletos("El ID de la Asignación de Curso no puede ser nulo");
        }/* if (!ConsultorAsignacionCurso.existeAsignacionCurso(emf, idAsignacionCurso)) {
            throw new NonexistentEntityException("No existe una Asignación de Curso con id="+idAsignacionCurso);
        } if (!ConsultorAsignacionCurso.esAsignacionCursoAnulada(emf, idAsignacionCurso)) {
            throw new ExcepcionEntityAnulado("La Asignación de Curso con id="+idAsignacionCurso+" está anulada");
        }*/
    }
    
    /**
     * Cambia el nombre del grupo al que pertenece una actividad, siempre que el nombre del grupo exista.
     * @param indexActividad índice de la actividad al que se cambiará su nombre de grupo.
     * @param nuevoNombreGrupo el nuevo nombre del grupo.
     * @return true si el nombre del grupo a editar existe, el nuevo nombre no es nulo o vacío, y se edita; false en caso contrario.
     */
    public boolean modificarNombreGrupo(int indexActividad, String nuevoNombreGrupo) {
        if (nuevoNombreGrupo == null) {
            return false;
        } if (nuevoNombreGrupo.length() == 0) {
            return false;
        } if (indexActividad<0 || indexActividad>=listaActividades.size()) {
            return false;
        }
        listaActividades.get(indexActividad).setGrupo_actividad(nuevoNombreGrupo);
        listaActividadModificada.set(indexActividad, true);
        return true;
    }
    /**
     * Cambia el nombre de una actividad, siempre que exista.
     * @param indexActividad índice de la actividad al que le cambiará el nombre.
     * @param nuevoNombre el nuevo nombre de la Actividad.
     * @return true si la actividad a renombrear existe, el nuevo nombre no es nulo o vacío, y se edita; false en caso contrario.
     */
    public boolean modificarNombreActividad(int indexActividad, String nuevoNombre) {
        if (nuevoNombre == null) {
            return false;
        } if (nuevoNombre.length() == 0) {
            return false;
        } if (indexActividad<0 || indexActividad>=listaActividades.size()) {
            return false;
        }
        listaActividades.get(indexActividad).setActividad(nuevoNombre);
        listaActividadModificada.set(indexActividad, true);
        return true;
    }
    /**
     * Cambia la Nota asignada a una actividad, siempre que exista.
     * @param indexActividad índice de la actividad al que se cambiará la nota esperada.
     * @param nuevaNotaEsperada la nueva nota de la actividad.
     * @return true si la actividad existe, la nueva nota no es nula y sea mayor a cero; false en caso contrario.
     */
    public boolean modificarNotaEsperada(int indexActividad, Float nuevaNotaEsperada) {
        if (nuevaNotaEsperada == null) {
            return false;
        }
        if (indexActividad<0 || indexActividad>=listaActividades.size() || nuevaNotaEsperada<0.f) {
            return false;
        }
        listaActividades.get(indexActividad).setEsperado(nuevaNotaEsperada);
        listaActividadModificada.set(indexActividad, true);
        return true;
    }
    /**
     * Elimina una actividad, siempre que exista.
     * @param indexActividad el índice de la actividad que se desea eliminar.
     * @return true si la actividad existe y se elimina; false en caso contrario.
     */
    public boolean eliminarActividad(int indexActividad) {
        if (indexActividad<0 || indexActividad>=listaActividades.size()) {
            return false;
        }
        listaActividadesEliminadas.add(listaActividades.remove(indexActividad));
        listaActividadModificada.remove(indexActividad);
        return true;
    }
    
    /**
     * Crea, edita y/o elimina actividades relacionadas a la Asignación de Curso.
     * @throws ExcepcionParametrosIncompletos se lanza si emf == null, emf está cerrada o idAsignacionCurso == null
     * @throws NonexistentEntityException se lanza si las actividades a eliminar no existen en la Base de datos.
     * @throws ExcepcionEntityAnulado se lanza si la Asignación de Curso ya está anulada (y por lo tanto, no se
     * pueden modificar las Notas). Esta anulación es entendida como finalización del Curso.
     */
    public void guardarDistribucionNota()
            throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        // Se comprueba que los parámetros no hayan cambiado
        evaluarParametros();
        // Eliminación de las Actividades borradas
        eliminarActividadesBorradas();
        // Actualización de las Actividades cambiadas
        actualizarActividadesCambiadas();    
    }
    /**
     * Auxiliar para borrar de la BD las actividades eliminadas.
     * @throws NonexistentEntityException se lanza si las actividades a eliminar no existen en la BD.
     */
    private void eliminarActividadesBorradas() throws NonexistentEntityException {
        ArrayList<List<NotaActividadEntity>> listaNotasActividad = new ArrayList<>();
        // Eliminación de las Actividades borradas
        ActividadJpaController controller1 = new ActividadJpaController(emf);
        NotaActividadJpaController controller2 = new NotaActividadJpaController(emf);
        int cantidad1=listaActividadesEliminadas.size(), cantidad2=0, indexError1=0, indexError2=0;
        try {
            for(indexError1=0; indexError1<cantidad1; indexError1++) {
                ActividadEntity actividad = listaActividadesEliminadas.get(indexError1);
                // Eliminación de las Nota Actividad asignadas a cada Asignación Estudiante Curso relacionadas a
                // las Actividades que se eliminarán
                List<NotaActividadEntity> notasActividad = controller2.buscarPoraActividad(actividad.getId());
                cantidad2 = notasActividad.size();
                try {
                    for (indexError2=0; indexError2<cantidad2; indexError2++) {
                        controller2.destroy(notasActividad.get(indexError2).getId());
                    }
                    listaNotasActividad.add(notasActividad);    // Agregación de las Nota de Actividad eliminadas
                } catch (NonexistentEntityException ex) {   // Se realizará un "ROLLBACK" en el catch del try exterior
                    throw new NonexistentEntityException(ex.getMessage());
                }
                // Eliminación de la Actividad
                controller1.destroy(actividad.getId());
            }
        } catch (NonexistentEntityException ex) {   // Se realiza un "ROLLBACK" de todo
            cantidad1 = indexError1 + 1;
            cantidad2 = indexError2;
            int index1, index2;
            for(index1=0; index1<cantidad1; index1++) {
                // Creación de las Actividades recién eliminadas
                controller1.create(listaActividadesEliminadas.get(index1));
                List<NotaActividadEntity> notasEliminadas = listaNotasActividad.get(index1);
                for(index2=0; index2<cantidad2; index2++) {
                    // Creación de las Notas de Actividad recién eliminadas
                    controller2.create(notasEliminadas.get(index2));
                }
            }
            throw new NonexistentEntityException(ex.getMessage());
        }
    }
    /**
     * Auxiliar para escribir en la BD las actividades modificadas.
     * @throws NonexistentEntityException se lanza si las actividades a editar no existen en la BD
     */
    private void actualizarActividadesCambiadas() throws NonexistentEntityException {
        // La actualización de una Actividad sólamente afecta a su Nombre, su Grupo o su Nota Esperada
        ArrayList<ActividadEntity> copiaActividades = new ArrayList<>();
        ActividadJpaController controller = new ActividadJpaController(emf);
        int cantidad = listaActividades.size(), indexError=0;
        try {
            for(indexError=0; indexError<cantidad; indexError++) {
                ActividadEntity copia = new ActividadEntity();
                if (listaActividadModificada.get(indexError)) {
                    // Realizo una copia de la Actividad que se modificará
                    copia.copy(listaActividades.get(indexError));
                    // Modificación de la Actividad actual
                    controller.edit(listaActividades.get(indexError));
                }
                copiaActividades.add(copia);
            }
            // Marcar todas las entidades como no modificadas
            for(indexError=0; indexError<cantidad; indexError++) {
                listaActividadModificada.set(indexError, false);
            }
        } catch (Exception ex) {    // Se realiza un "ROLLBACK"
            for(cantidad=0; cantidad<indexError; cantidad++) {
                if (listaActividadModificada.get(cantidad)) {
                    try {
                        controller.edit(copiaActividades.get(cantidad));
                    } catch (Exception ex1) {
                        // En teoría no tendría que hacer algo ya que intentará revertir todos los cambios
                        System.err.println("Error al intentar revertir la Actividad con id="+copiaActividades.get(cantidad).getId());
                    }
                }
            }
            throw new NonexistentEntityException(ex.getMessage());
        } 
    }
}