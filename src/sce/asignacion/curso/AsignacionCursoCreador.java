/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso;

import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.asignacion.carrera.orm.AsignacionCarreraEntity;
import sce.asignacion.carrera.orm.AsignacionCarreraJpaController;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.principal.elemento_asignatura.curso.ConsultorRegistroCurso;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.curso.orm.AsignacionCursoJpaController;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.excepciones.ExcepcionEntityAnulado;
import sce.excepciones.NonexistentEntityException;
import sce.principal.elemento_asignatura.grado.orm.GradoEntity;
import sce.principal.elemento_asignatura.grado.orm.GradoJpaController;

/**
 * LISTO!! Falta JavaDoc para constructor y para la Clase
 * @author Usuario
 */
public class AsignacionCursoCreador implements AsignacionCommand {
    private final EntityManagerFactory emf;
    private final Long idAsignacionCarrera;
    private Long idAsignacionGrado;
    private final ArrayList<Long> listaIDCursos;
    private final ArrayList<Float> listaNotaRequerida;
    
    public AsignacionCursoCreador(EntityManagerFactory emf, Long idAsignacionCarrera)
            throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        this.emf = emf;
        this.idAsignacionCarrera = idAsignacionCarrera;
        evaluarParametros();
        this.listaIDCursos = new ArrayList<>();
        this.listaNotaRequerida = new ArrayList<>();
    }
    /**
     * Valida que los parámetros necesarios para crear las Asiganciones de Cursos sean especificados.
     * @throws NonexistentEntityException se lanza si un Curso agregado no existe.
     * @throws ExcepcionParametrosIncompletos se lanza si se ha cerrado la conexión.
     * @throws ExcepcionEntityAnulado se lanza si la Asignación de Carrera está anulada.
     */
    private void evaluarParametros()
            throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        if (emf == null) {
            throw new ExcepcionParametrosIncompletos("No se ha proporcionado una conexión válida");
        } if (!emf.isOpen()) {
            throw new ExcepcionParametrosIncompletos("La conexión ya ha sido cerrada");
        } if (idAsignacionCarrera == null) {
            throw new ExcepcionParametrosIncompletos("El ID de la Asignación de Carrera no puede ser nulo");
        }
        // @Nota para módulo correspondiente
        // Verificar que la Asignación de Carrera exista y no esté anulada
        AsignacionCarreraEntity asigCarrera = new AsignacionCarreraJpaController(emf).findAsignacionCarreraEntity(idAsignacionCarrera);
        if (asigCarrera == null) {
            throw new NonexistentEntityException("No existe una Asignación de Carrera con id="+idAsignacionCarrera);
        } if (asigCarrera.getAnulado()) {
            throw new ExcepcionEntityAnulado("La Asignación de Carrera con id="+idAsignacionCarrera+" está anulada");
        }
    }
    
    /**
     * Asigna una Asignación de Grado para las Asignaciones de Curso que se realizarán. Este parámetro no es obligatorio.
     * @param idAsignacionGrado 
     */
    public void setGrado(Long idAsignacionGrado) { this.idAsignacionGrado = idAsignacionGrado; }
    /**
     * Agrega un nuevo Curso con su respectiva nota requerida (para evaluación).
     * @param idCurso ID del Curso que se desea agregar (no valida si existe o no el Curso).
     * @param notaRequerida nota sobre la cual se calificará este curso. El curso se evaluará entre [0, notaRequerida] puntos.
     */
    public void addCurso(Long idCurso, Float notaRequerida) {
        this.listaIDCursos.add(idCurso);
        if (notaRequerida==null || notaRequerida<0f) {
            notaRequerida = 0f;
        }
        this.listaNotaRequerida.add(notaRequerida);
    }

    /**
     * Intenta crear la Asignación de todos los Cursos a la Asignación Carrera y Asiganción Grado (si se especifica).
     * @throws NonexistentEntityException se lanza si un Curso agregado no existe.
     * @throws ExcepcionParametrosIncompletos se lanza si se ha cerrado la conexión.
     * @throws ExcepcionEntityAnulado se lanza si la Asignación de Carrera y/o la Asignación de Grado están anuladas.
     */
    @Override
    public void crearAsignacion()
            throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        // Una Asignación Curso consiste en habilitar un Curso para una Carrera y un Grado (si se especifica) específicos
        // Para que una Asignación Curso pueda realizarse deben cumplirse dos condiciones:
        // 1. La Asignación Carrera no debe estar anulada
        // 2. La Asignación Grado (si se especifica) no debe estar anulada
        
        // Se comprueba que todos los parámetros hayan sido especificados
        evaluarParametros();
        
        // Se verifica que hayan Cursos para Asignar
        if (listaIDCursos.isEmpty()) {
            throw new ExcepcionParametrosIncompletos("No se ha proporcinado Cursos para asignar");
        }
        
        // @Nota para módulo correspondiente
        // Si se proporciona una Asignación Grado, verificar que exista y que no esté anulada
        if (idAsignacionGrado != null) {
            // De hecho, si la Asignación de Carrera está anulada entonces la Asignación de Grado debería estarlo
            // Por si acaso, el código
            if (idAsignacionGrado != null) {
                AsignacionGradoEntity asigGrado = new AsignacionGradoJpaController(emf)
                        .findAsignacion_Grado(idAsignacionGrado);
                if (asigGrado == null) {
                    throw new NonexistentEntityException("No existe una Asgnación de Grado con id="+idAsignacionGrado);
                } if (asigGrado.getAnulado()) {
                    throw new ExcepcionEntityAnulado("La Asignación de Grado con id="+idAsignacionGrado+" ya ha sido anulada");
                }
                // Se comprueba que el Grado exista
                GradoEntity grado = new GradoJpaController(emf).findGradoEntity(asigGrado.getGrado_id());
                if (grado == null) {
                    throw new NonexistentEntityException("La Asignación de Grado tiene un Grado con id="+asigGrado.getId()+"  el cual no existe");
                }
            }
        }
        
        // Hasta aquí la Asignación de Curso se puede realizar. Se crea la Asignación de Curso
        try {
            crearAsignacionesCurso();
        } catch (NonexistentEntityException ex) {
            throw new NonexistentEntityException(ex.getMessage());
        } catch (ExcepcionParametrosIncompletos ex) {
            throw new ExcepcionParametrosIncompletos(ex.getMessage());
        } catch (ExcepcionEntityAnulado ex) {
            throw new ExcepcionEntityAnulado(ex.getMessage());
        }
    }
    /**
     * Auxiliar para crear las Asignaciones de Cursos. Crea una Asignación de Curso (por cada Curso agregado) a la
     * instancia actual.
     * @throws NonexistentEntityException se lanza si un Curso agregado no existe.
     * @throws ExcepcionParametrosIncompletos se lanza si se ha cerrado la conexión.
     * @throws ExcepcionEntityAnulado se lanza si la Asignación de Carrera y/o la Asignación de Grado están anuladas.
     */
    private void crearAsignacionesCurso()
            throws NonexistentEntityException, ExcepcionParametrosIncompletos, ExcepcionEntityAnulado {
        // Evaluación de que los parámetros sean correctos
        evaluarParametros();
        // Se crean tantos registros en 'asignacion_curso' como cursos estén agregados. Los pasos son:
        // 1. Para cada curso, se comprueba que el Curso exista.
        // 2. Si el curso existe, crear el registro en 'asignacion_curso'
        AsignacionCursoJpaController controller = new AsignacionCursoJpaController(emf);
        int cantidad = listaIDCursos.size(), indexError=0;
        ArrayList<Long> listaIDAsignacionCurso = new ArrayList<>();
        try {
            for(indexError=0; indexError<cantidad; indexError++) {
                Long idCurso = listaIDCursos.get(indexError);
                // PASO 1:
                if (!ConsultorRegistroCurso.existeCurso(idCurso, emf)) {
                    throw new NonexistentEntityException("Para la Asignación de Curso, no existe un Curso con id="+idCurso);
                }
                // PASO 2:
                AsignacionCursoEntity asignacionCur = new AsignacionCursoEntity();
                asignacionCur.setAsignacion_carrera_id(idAsignacionCarrera);
                asignacionCur.setAsignacion_grado_id(idAsignacionGrado);
                asignacionCur.setCurso_id(idCurso);
                asignacionCur.setEsperado(listaNotaRequerida.get(indexError));
                controller.create(asignacionCur);
                listaIDAsignacionCurso.add(asignacionCur.getId());
            }
        } catch (NonexistentEntityException ex) {
            // Haciendo un "ROLLBACK" propio
            // Se eliminan todas las Asignación de Curso creadas, anteriores al error
            for(int index=0; index<indexError; index++) {
                controller.destroy(listaIDAsignacionCurso.get(index));
            }
            throw new NonexistentEntityException(ex.getMessage());
        }
    }
}