/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.curso.nota.orm;

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
public class ActividadJpaController implements Serializable {

    public ActividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public List<ActividadEntity> buscarPorAsignacionCurso(Long idAsignacionCurso) {
        EntityManager em = getEntityManager();
        TypedQuery<ActividadEntity> query = em.createNamedQuery("Actividad.buscarPorAsignacionCurso", ActividadEntity.class);
        return query.setParameter("idAsignacionCurso", idAsignacionCurso).getResultList();
    }

    public void create(ActividadEntity actividadEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(actividadEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ActividadEntity actividadEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        Long id = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            actividadEntity = em.merge(actividadEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                id = actividadEntity.getId();
                if (findActividadEntity(id) == null) {
                    throw new NonexistentEntityException("No existe la Actividad con id="+id);
                }
            }
            throw new Exception("Ocurrió un error al actualizar la Actividad con id="+id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void edit(ActividadEntity actividadEntity, EntityManager em) {
        em.merge(actividadEntity);
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActividadEntity actividadEntity;
            try {
                actividadEntity = em.getReference(ActividadEntity.class, id);
                actividadEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("No existe una Actividad con id="+id);
            }
            em.remove(actividadEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void destroy(ActividadEntity actividadEntity, EntityManager em) {
        em.remove(actividadEntity);
    }

    public List<ActividadEntity> findActividadEntityEntities() {
        return findActividadEntityEntities(true, -1, -1);
    }

    public List<ActividadEntity> findActividadEntityEntities(int maxResults, int firstResult) {
        return findActividadEntityEntities(false, maxResults, firstResult);
    }

    private List<ActividadEntity> findActividadEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActividadEntity.class));
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

    public ActividadEntity findActividadEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActividadEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActividadEntity> rt = cq.from(ActividadEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
