/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.estudiante.orm.AsignacionCursosEstudianteEntity;
import sce.asignacion.estudiante.orm.AsignacionEstudianteEntity;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.persona.estudiante.orm.EstudianteEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.asignacion.curso.ConsultorAsignacionCurso;
import sce.asignacion.estudiante.orm.AsignacionCursosEstudianteJpaController;
import sce.asignacion.estudiante.orm.AsignacionEstudianteJpaController;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.persona.estudiante.orm.EstudianteJpaController;
import sce.excepciones.ExcepcionEntityAnulado;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.excepciones.NonexistentEntityException;

/**
 * Clase para crear UNA ASIGNACIÓN de un Estudiante a una Asignación de Carrera, a una Asignación de Grado (si se
 * especifica) y a varias Asignación de Curso (ya sea que estén asignados a la carrera o al grado). Cabe mencionar
 * que el hecho de asignar al estudiante a un grado, no fuerza a asignar al estudiante a todos los cursos que
 * posiblemente estén relacionados al grado.
 * @author Usuario
 */
public class AsignacionEstudianteCreador implements AsignacionCommand {
    private final EntityManagerFactory emf;
    private final Long idAsignacionCarrera;
    private Long idAsignacionGrado;
    private Long idEstudiante;
    private ArrayList<Long> listaIDAsignacionCursos;

    public AsignacionEstudianteCreador(EntityManagerFactory emf, Long idAsignacionCarrera) {
        this.emf = emf;
        this.idAsignacionCarrera = idAsignacionCarrera;
        this.listaIDAsignacionCursos = new ArrayList<>();
    }
    
    public void setGrado(Long idAsignacionGrado) { this.idAsignacionGrado = idAsignacionGrado; }
    public void setEstudiante(Long idEstudiante) { this.idEstudiante = idEstudiante; }
    public void addCurso(Long curso) { this.listaIDAsignacionCursos.add(curso); }
    public void setCursos(ArrayList<Long> listaIDCursos) { this.listaIDAsignacionCursos = listaIDCursos; }

    @Override
    public void crearAsignacion()
            throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        // Se comprueba que exista la Asignación Carrera y que no esté anulada
        // @Nota para módulo correspondiente
        AsignacionCarreraEntity asigCarrera = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntity(idAsignacionCarrera);
        if (asigCarrera == null) {
            throw new NonexistentEntityException("No existe una Asignación de Carrera con id="+idAsignacionCarrera);
        } if (asigCarrera.getAnulado()) {
            throw new ExcepcionEntityAnulado("La Asignación de Carrera con id="+idAsignacionCarrera+" ya ha sido anulada");
        }
        // Se comprueba que exista la Asignación Grado (si se proporciona) y que no esté anulada
        if (idAsignacionGrado != null) {
            // Si el grado existe, se comprueba que no esté anulado
            // @Nota para módulo correspondiente
            AsignacionGradoJpaController asigGrado = new AsignacionGradoJpaController(emf);
            AsignacionGradoEntity grado = asigGrado.findAsignacion_Grado(idAsignacionGrado);
            if (grado == null) {
                throw new NonexistentEntityException("No existe una Asgnación de Grado con id="+idAsignacionGrado);
            } if (grado.getAnulado()) {
                throw new ExcepcionEntityAnulado("La Asignación de Grado con id="+idAsignacionGrado+" ya ha sido anulada");
            }
        }
        // Se comprueba que exista el Estudiante y que no esté anulado
        // @Nota para módulo correspondiente
        EstudianteEntity estudiante = new EstudianteJpaController(emf).findEstudianteEntity(idEstudiante);
        if (estudiante == null) {
            throw new NonexistentEntityException("No existe un Estudiante con id="+idEstudiante);
        } if (estudiante.getAnulado()) {
            throw new ExcepcionEntityAnulado("El Estudiante con id="+idEstudiante+" ya ha sido anulado");
        }
        // Se crean tantos registros en 'asignacion_estudiante_cursos' como Cursos estén agregados. Los pasos son:
        // 1. Crear el registro en 'asignacion_estudiante'
        // 2. Para cada AsignacionCursoEntity:
	//    2.1. Crear el registro en 'asignacion_estudiante_cursos'
        // 3. Actualizar 'estudiante.asignacion_id' = 'asignacion_estudiante.id'
        // PASO 1:
        AsignacionEstudianteEntity asignacionEst = new AsignacionEstudianteEntity();
        asignacionEst.setAsignacion_carrera_id(idAsignacionCarrera);
        asignacionEst.setEstudiante_id(idEstudiante);
        asignacionEst.setAsignacion_grado_id(idAsignacionGrado);
        new AsignacionEstudianteJpaController(emf).create(asignacionEst);   // Se crea en su propia transacción
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // PASO 2:
        for (Long idAsignacionCurso : listaIDAsignacionCursos) {
            // Se comprueba que exista la Asignación Curso y que no esté anulada
            // Una Asignación Curso está anulada si la Asignación Carrera o la Asignación Grado relacionadas están anuladas
            if (!ConsultorAsignacionCurso.existeAsignacionCurso(emf, idAsignacionCurso)) {
                em.getTransaction().rollback();
                // Eliminación de la AsignacionEstudianteEntity recién creada
                new AsignacionEstudianteJpaController(emf).destroy(asignacionEst.getId());
                throw new NonexistentEntityException("No existe una Asignación de Curso con id="+idAsignacionCarrera);
            } if (!ConsultorAsignacionCurso.esAsignacionCursoAnulada(emf, idAsignacionCurso)) {
                em.getTransaction().rollback();
                // Eliminación de la AsignacionEstudianteEntity recién creada
                new AsignacionEstudianteJpaController(emf).destroy(asignacionEst.getId());
                throw new ExcepcionEntityAnulado("Puede que la Asignación de Curso con id="+idAsignacionCarrera+" ya ha sido anulado");
            } if (!ConsultorAsignacionCurso.validarAsignacionCurso(emf, idAsignacionCurso, idAsignacionCarrera, idAsignacionGrado)) {
                em.getTransaction().rollback();
                // Eliminación de la AsignacionEstudianteEntity recién creada
                new AsignacionEstudianteJpaController(emf).destroy(asignacionEst.getId());
                throw new NonexistentEntityException("No existe una Asignación de Curso con los parámetros especificados");
            }
            // Creación de la Asignación del Estudiante al Curso especificado
            AsignacionCursosEstudianteEntity asignacionCurso = new AsignacionCursosEstudianteEntity();
            asignacionCurso.setAsignacion_estudiante_id(asignacionEst.getId());
            asignacionCurso.setAsignacion_curso_id(idAsignacionCurso);
            asignacionCurso.setAcumulado(0f);
            new AsignacionCursosEstudianteJpaController(emf).create(asignacionCurso);
        }
        em.getTransaction().commit();
        // PASO 3:
        // @Nota para módulo correspondiente
        estudiante.setAsignacion_id(asignacionEst.getId());
        try {
            new EstudianteJpaController(emf).edit(estudiante);
        } catch (Exception ex) {
            throw new NonexistentEntityException(ex.getMessage());
        }
    }
}
