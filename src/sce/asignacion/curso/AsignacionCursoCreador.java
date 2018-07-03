/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.AsignacionCommand;
import sce.excepciones.ExcepcionParametrosIncompletos;
import sce.principal.elemento_asignatura.curso.ConsultorRegistroCurso;
import sce.asignacion.curso.orm.AsignacionCursoEntity;
import sce.asignacion.grado.orm.AsignacionGradoEntity;
import sce.asignacion.curso.orm.AsignacionCursoJpaController;
import sce.asignacion.grado.orm.AsignacionGradoJpaController;
import sce.excepciones.ExcepcionEntityAnulado;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AsignacionCursoCreador implements AsignacionCommand {
    private final EntityManagerFactory emf;
    private final Long idAsignacionCarrera;
    private Long idAsignacionGrado;
    private final ArrayList<Long> listaIDCursos;
    private final ArrayList<Float> listaNotaRequerida;
    
    public AsignacionCursoCreador(EntityManagerFactory emf, Long idAsignacionCarrera) {
        this.emf = emf;
        this.idAsignacionCarrera = idAsignacionCarrera;
        this.listaIDCursos = new ArrayList<>();
        this.listaNotaRequerida = new ArrayList<>();
    }
    
    public void setGrado(Long idAsignacionGrado) { this.idAsignacionGrado = idAsignacionGrado; }
    public void addCurso(Long curso, Float notaRequerida) {
        this.listaIDCursos.add(curso);
        this.listaNotaRequerida.add(notaRequerida);
    }

    @Override
    public void crearAsignacion() throws ExcepcionParametrosIncompletos, NonexistentEntityException, ExcepcionEntityAnulado {
        if (emf == null) {
            throw new ExcepcionParametrosIncompletos("No se ha proporcionado una conexión válida");
        } if (!emf.isOpen()) {
            throw new ExcepcionParametrosIncompletos("La conexión ya ha sido cerrada");
        } if (idAsignacionCarrera == null) {
            throw new ExcepcionParametrosIncompletos("El ID de la Asignación de Carrera no puede ser nulo");
        } if (listaIDCursos.isEmpty()) {
            throw new ExcepcionParametrosIncompletos("No se ha proporcinado Cursos para asignar");
        }
        // Previo a empezar, se comprueba que exista el Grado (si se especifica) y el Curso
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if (idAsignacionGrado != null) {
            // Si el grado existe, se comprueba que no esté anulado
            // @Nota para módulo correspondiente
            AsignacionGradoJpaController asigGrado = new AsignacionGradoJpaController(emf);
            AsignacionGradoEntity grado = asigGrado.findAsignacion_Grado(idAsignacionGrado);
            if (grado == null) {
                em.getTransaction().rollback();
                throw new NonexistentEntityException("No existe una Asgnación de Grado con id="+idAsignacionGrado);
            } if (grado.getAnulado()) {
                em.getTransaction().rollback();
                throw new ExcepcionEntityAnulado("La Asignación de Grado con id="+idAsignacionGrado+" ya ha sido anulada");
            }
        }
        // Se crean tantos registros en 'asignacion_curso' como cursos estén agregados. Los pasos son:
        // 1. Para cada curso, se comprueba que el Curso exista.
        // 2. Si el curso existe, crear el registro en 'asignacion_curso'
        int cantidad = listaIDCursos.size(), index;
        AsignacionCursoJpaController controllerAsigCurso = new AsignacionCursoJpaController(emf);
        for(index=0; index<cantidad; index++) {
            // PASO 1:
            Long idCurso = listaIDCursos.get(index);
            if (!ConsultorRegistroCurso.existeCurso(idCurso, emf)) {
                em.getTransaction().rollback();
                throw new NonexistentEntityException("No existe un Curso con id="+idCurso);
            }
            // PASO 2:
            AsignacionCursoEntity asignacionCur = new AsignacionCursoEntity();
            asignacionCur.setAsignacion_carrera_id(idAsignacionCarrera);
            asignacionCur.setAsignacion_grado_id(idAsignacionGrado);
            asignacionCur.setCurso_id(idCurso);
            asignacionCur.setEsperado(listaNotaRequerida.get(index));
            controllerAsigCurso.create(asignacionCur, em);
        }
        em.getTransaction().commit();
    }
}