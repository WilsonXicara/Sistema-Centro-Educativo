/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.estudiante.orm;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.asignacion.estudiante.orm.AsignacionCursosEstudianteEntity;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AsignacionCursosEstudianteJpaController implements Serializable {

    public AsignacionCursosEstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public AsignacionCursosEstudianteEntity buscarCursoAsignado(Long idAsignacionEst, Long idCurso) {
        EntityManager em = getEntityManager();
        TypedQuery<AsignacionCursosEstudianteEntity> query = em.createNamedQuery("AsignacionCursosEstudiante.buscarCursoAsignado", AsignacionCursosEstudianteEntity.class);
        List<AsignacionCursosEstudianteEntity> encontrados = query
                .setParameter("idAsignacionEst", idAsignacionEst)
                .setParameter("idCurso", idCurso)
                .getResultList();
        if (encontrados.isEmpty()) {
            return null;
        }
        return encontrados.get(0);
    }

    public void create(AsignacionCursosEstudianteEntity asignacionCursosEstudiante) {
        EntityManager em = null;
        try {
            AsignacionCursosEstudianteEntity existente = buscarCursoAsignado(asignacionCursosEstudiante.getAsignacion_estudiante_id(), asignacionCursosEstudiante.getAsignacion_curso_id());
            if (existente != null) {
                asignacionCursosEstudiante.copy(existente);
                return;
            }
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignacionCursosEstudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void create(AsignacionCursosEstudianteEntity asignacionCursosEstudiante, EntityManager em) {
        em.persist(asignacionCursosEstudiante);
    }

    public void edit(AsignacionCursosEstudianteEntity asignacionCursosEstudiante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignacionCursosEstudiante = em.merge(asignacionCursosEstudiante);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionCursosEstudiante.getId();
                if (findAsignacionCursosEstudiante(id) == null) {
                    throw new NonexistentEntityException("The asignacionCursosEstudiante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsignacionCursosEstudianteEntity asignacionCursosEstudiante;
            try {
                asignacionCursosEstudiante = em.getReference(AsignacionCursosEstudianteEntity.class, id);
                asignacionCursosEstudiante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionCursosEstudiante with id " + id + " no longer exists.", enfe);
            }
            em.remove(asignacionCursosEstudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionCursosEstudianteEntity> findAsignacionCursosEstudianteEntities() {
        return findAsignacionCursosEstudianteEntities(true, -1, -1);
    }

    public List<AsignacionCursosEstudianteEntity> findAsignacionCursosEstudianteEntities(int maxResults, int firstResult) {
        return findAsignacionCursosEstudianteEntities(false, maxResults, firstResult);
    }

    private List<AsignacionCursosEstudianteEntity> findAsignacionCursosEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignacionCursosEstudianteEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AsignacionCursosEstudianteEntity findAsignacionCursosEstudiante(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionCursosEstudianteEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionCursosEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignacionCursosEstudianteEntity> rt = cq.from(AsignacionCursosEstudianteEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
