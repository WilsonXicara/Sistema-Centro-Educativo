/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.orm;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AsignacionCursoJpaController implements Serializable {

    public AsignacionCursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<AsignacionCursoEntity> buscarPorAsignacionCarrera(Long idAsignacionCarrera) {
        EntityManager em = getEntityManager();
        TypedQuery<AsignacionCursoEntity> query = em.createNamedQuery("AsignacionCurso.buscarPorAsignacionCarrera", AsignacionCursoEntity.class);
        return query
                .setParameter("idAsignacionCarrera", idAsignacionCarrera)
                .getResultList();
    }
    public List<AsignacionCursoEntity> buscarPorAsignacionGrado(Long idAsignacionCarrera, Long idAsignacionGrado) {
        EntityManager em = getEntityManager();
        TypedQuery<AsignacionCursoEntity> query = em.createNamedQuery("AsignacionCurso.buscarPorAsignacionGrado", AsignacionCursoEntity.class);
        return query
                .setParameter("idAsignacionCarrera", idAsignacionCarrera)
                .setParameter("idAsignacionGrado", idAsignacionGrado)
                .getResultList();
    }
    public void create(AsignacionCursoEntity asignacion_Curso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignacion_Curso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void create(AsignacionCursoEntity asignacion_Curso, EntityManager em) {
        em.persist(asignacion_Curso);
    }

    public void edit(AsignacionCursoEntity asignacion_Curso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignacion_Curso = em.merge(asignacion_Curso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacion_Curso.getId();
                if (findAsignacion_Curso(id) == null) {
                    throw new NonexistentEntityException("The asignacion_Curso with id " + id + " no longer exists.");
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
            AsignacionCursoEntity asignacion_Curso;
            try {
                asignacion_Curso = em.getReference(AsignacionCursoEntity.class, id);
                asignacion_Curso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacion_Curso with id " + id + " no longer exists.", enfe);
            }
            em.remove(asignacion_Curso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionCursoEntity> findAsignacion_CursoEntities() {
        return findAsignacion_CursoEntities(true, -1, -1);
    }

    public List<AsignacionCursoEntity> findAsignacion_CursoEntities(int maxResults, int firstResult) {
        return findAsignacion_CursoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionCursoEntity> findAsignacion_CursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignacionCursoEntity.class));
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

    public AsignacionCursoEntity findAsignacion_Curso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionCursoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacion_CursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignacionCursoEntity> rt = cq.from(AsignacionCursoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
