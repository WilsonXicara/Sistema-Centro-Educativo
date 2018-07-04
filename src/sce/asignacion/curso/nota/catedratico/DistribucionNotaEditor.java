/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.catedratico;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sce.asignacion.curso.nota.estudiante.NotaActividadEntity;
import sce.asignacion.curso.nota.estudiante.NotaActividadJpaController;
import sce.asignacion.curso.nota.orm.ActividadEntity;
import sce.asignacion.curso.nota.orm.ActividadJpaController;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class DistribucionNotaEditor {
    private final EntityManagerFactory emf;
    private final Long idAsignacionCurso;
    private final ArrayList<ActividadEntity> listaActividades, listaActividadesEliminadas;
    private final ArrayList<Boolean> listaActividadModificada;
    
    public DistribucionNotaEditor(EntityManagerFactory emf, Long idAsignacionCurso) {
        this.emf = emf;
        this.idAsignacionCurso = idAsignacionCurso;
        // Obtención de las Actividades asociadas a la Distribución de Nota asociada
        List<ActividadEntity> actividades = new ActividadJpaController(emf).buscarPorAsignacionCurso(idAsignacionCurso);
        this.listaActividades = new ArrayList<>();
        this.listaActividadesEliminadas = new ArrayList<>();
        this.listaActividadModificada = new ArrayList<>();
        for (ActividadEntity actividad : actividades) {
            this.listaActividades.add(actividad);
            this.listaActividadModificada.add(false);
        }
    }
    
    public boolean modificarGrupoDeActividad(int indexActividad, String nuevoNombreGrupo) {
        if (indexActividad<0 || indexActividad>=listaActividades.size()) {
            return false;
        }
        listaActividades.get(indexActividad).setGrupo_actividad(nuevoNombreGrupo);
        listaActividadModificada.set(indexActividad, true);
        return true;
    }
    public boolean modificarNombreActividad(int indexActividad, String nuevoNombre) {
        if (indexActividad<0 || indexActividad>=listaActividades.size()) {
            return false;
        }
        listaActividades.get(indexActividad).setActividad(nuevoNombre);
        listaActividadModificada.set(indexActividad, true);
        return true;
    }
    public boolean modificarNotaEsperada(int indexActividad, Float nuevaNotaEsperada) {
        if (indexActividad<0 || indexActividad>=listaActividades.size() || nuevaNotaEsperada<0.f) {
            return false;
        }
        listaActividades.get(indexActividad).setEsperado(nuevaNotaEsperada);
        listaActividadModificada.set(indexActividad, true);
        return true;
    }
    public boolean eliminarActividad(int indexActividad) {
        if (indexActividad<0 || indexActividad>=listaActividades.size()) {
            return false;
        }
        listaActividadesEliminadas.add(listaActividades.remove(indexActividad));
        listaActividadModificada.remove(indexActividad);
        return true;
    }
    
    public void guardarDistribucionNota() throws NonexistentEntityException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Actualización de las Actividades cambiadas
        ActividadJpaController controllerActividad = new ActividadJpaController(emf);
        int cantidad = listaActividades.size(), index;
        for(index=0; index<cantidad; index++) {
            if (listaActividadModificada.get(index)) {
                controllerActividad.edit(listaActividades.get(index), em);
            }
        }
        // Eliminación de las Actividades borradas
        for (ActividadEntity actividad : listaActividadesEliminadas) {
            // Eliminación de las Nota Actividad asignadas a cada Asignación Estudiante Curso relacionadas a
            // las Actividades que se eliminarán
            NotaActividadJpaController controllerNotaA = new NotaActividadJpaController(emf);
            List<NotaActividadEntity> notasActividad = controllerNotaA.buscarPoraActividad(actividad.getId());
            for (NotaActividadEntity notaActividad : notasActividad) {
                controllerNotaA.destroy(notaActividad, em);
            }
            // Eliminación de la Actividad
            controllerActividad.destroy(actividad, em);
        }
        em.getTransaction().commit();
    }
}