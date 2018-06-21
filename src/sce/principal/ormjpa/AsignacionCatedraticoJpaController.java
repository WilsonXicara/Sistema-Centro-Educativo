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
import sce.principal.entity.AsignacionCatedraticoEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AsignacionCatedraticoJpaController implements Serializable {

    public AsignacionCatedraticoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsignacionCatedraticoEntity asignacion_Catedratico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignacion_Catedratico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionCatedraticoEntity asignacion_Catedratico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignacion_Catedratico = em.merge(asignacion_Catedratico);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacion_Catedratico.getId();
                if (findAsignacion_Catedratico(id) == null) {
                    throw new NonexistentEntityException("The asignacion_Catedratico with id " + id + " no longer exists.");
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
            AsignacionCatedraticoEntity asignacion_Catedratico;
            try {
                asignacion_Catedratico = em.getReference(AsignacionCatedraticoEntity.class, id);
                asignacion_Catedratico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacion_Catedratico with id " + id + " no longer exists.", enfe);
            }
            em.remove(asignacion_Catedratico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionCatedraticoEntity> findAsignacion_CatedraticoEntities() {
        return findAsignacion_CatedraticoEntities(true, -1, -1);
    }

    public List<AsignacionCatedraticoEntity> findAsignacion_CatedraticoEntities(int maxResults, int firstResult) {
        return findAsignacion_CatedraticoEntities(false, maxResults, firstResult);
    }

    private List<AsignacionCatedraticoEntity> findAsignacion_CatedraticoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsignacionCatedraticoEntity.class));
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

    public AsignacionCatedraticoEntity findAsignacion_Catedratico(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionCatedraticoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacion_CatedraticoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsignacionCatedraticoEntity> rt = cq.from(AsignacionCatedraticoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
