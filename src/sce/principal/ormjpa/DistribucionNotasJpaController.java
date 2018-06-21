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
import sce.principal.entity.DistribucionNotasEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class DistribucionNotasJpaController implements Serializable {

    public DistribucionNotasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DistribucionNotasEntity distribucionNotasEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(distribucionNotasEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DistribucionNotasEntity distribucionNotasEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            distribucionNotasEntity = em.merge(distribucionNotasEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = distribucionNotasEntity.getId();
                if (findDistribucionNotasEntity(id) == null) {
                    throw new NonexistentEntityException("The distribucionNotasEntity with id " + id + " no longer exists.");
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
            DistribucionNotasEntity distribucionNotasEntity;
            try {
                distribucionNotasEntity = em.getReference(DistribucionNotasEntity.class, id);
                distribucionNotasEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The distribucionNotasEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(distribucionNotasEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DistribucionNotasEntity> findDistribucionNotasEntityEntities() {
        return findDistribucionNotasEntityEntities(true, -1, -1);
    }

    public List<DistribucionNotasEntity> findDistribucionNotasEntityEntities(int maxResults, int firstResult) {
        return findDistribucionNotasEntityEntities(false, maxResults, firstResult);
    }

    private List<DistribucionNotasEntity> findDistribucionNotasEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DistribucionNotasEntity.class));
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

    public DistribucionNotasEntity findDistribucionNotasEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DistribucionNotasEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getDistribucionNotasEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DistribucionNotasEntity> rt = cq.from(DistribucionNotasEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
