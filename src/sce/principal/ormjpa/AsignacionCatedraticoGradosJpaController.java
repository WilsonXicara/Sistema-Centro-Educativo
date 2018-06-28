/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.principal.ormjpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.principal.entity.AsignacionCatedraticoGradosEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AsignacionCatedraticoGradosJpaController implements Serializable {

    public AsignacionCatedraticoGradosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionCatedraticoGradosEntity asignacionCatedraticoGradosEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignacionCatedraticoGradosEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionCatedraticoGradosEntity asignacionCatedraticoGradosEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignacionCatedraticoGradosEntity = em.merge(asignacionCatedraticoGradosEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionCatedraticoGradosEntity.getId();
                if (findAsignacionCatedraticoGradosEntity(id) == null) {
                    throw new NonexistentEntityException("The asignacionCatedraticoGradosEntity with id " + id + " no longer exists.");
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
            AsignacionCatedraticoGradosEntity asignacionCatedraticoGradosEntity;
            try {
                asignacionCatedraticoGradosEntity = em.getReference(AsignacionCatedraticoGradosEntity.class, id);
                asignacionCatedraticoGradosEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionCatedraticoGradosEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(asignacionCatedraticoGradosEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionCatedraticoGradosEntity> findAsignacionCatedraticoGradosEntityEntities() {
        return findAsignacionCatedraticoGradosEntityEntities(true, -1, -1);
    }

    public List<AsignacionCatedraticoGradosEntity> findAsignacionCatedraticoGradosEntityEntities(int maxResults, int firstResult) {
        return findAsignacionCatedraticoGradosEntityEntities(false, maxResults, firstResult);
    }

    private List<AsignacionCatedraticoGradosEntity> findAsignacionCatedraticoGradosEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignacionCatedraticoGradosEntity.class));
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

    public AsignacionCatedraticoGradosEntity findAsignacionCatedraticoGradosEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionCatedraticoGradosEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionCatedraticoGradosEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignacionCatedraticoGradosEntity> rt = cq.from(AsignacionCatedraticoGradosEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
