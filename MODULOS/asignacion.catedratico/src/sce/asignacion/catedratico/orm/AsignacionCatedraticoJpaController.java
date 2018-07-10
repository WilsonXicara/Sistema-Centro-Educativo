/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.asignacion.catedratico.orm;

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
public class AsignacionCatedraticoJpaController implements Serializable {

    public AsignacionCatedraticoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<AsignacionCatedraticoEntity> buscarPorCarrera(Long idCarrera){
        EntityManager em = getEntityManager();
        TypedQuery<AsignacionCatedraticoEntity> query = em.createNamedQuery("AsignacionCatedratico.buscarPorCarrera", AsignacionCatedraticoEntity.class);
        return query.setParameter("idCarrera",idCarrera).getResultList();
    }
     public List<AsignacionCatedraticoEntity> buscarPorCatedratico(Long idCatedratico){
        EntityManager em = getEntityManager();
        TypedQuery<AsignacionCatedraticoEntity> query = em.createNamedQuery("AsignacionCatedratico.buscarPorCatedratico", AsignacionCatedraticoEntity.class);
        return query.setParameter("idCatedratico", idCatedratico).getResultList();
    }

    public void create(AsignacionCatedraticoEntity asignacionCatedraticoEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignacionCatedraticoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsignacionCatedraticoEntity asignacionCatedraticoEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignacionCatedraticoEntity = em.merge(asignacionCatedraticoEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = asignacionCatedraticoEntity.getId();
                if (findAsignacionCatedraticoEntity(id) == null) {
                    throw new NonexistentEntityException("The asignacionCatedraticoEntity with id " + id + " no longer exists.");
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
            AsignacionCatedraticoEntity asignacionCatedraticoEntity;
            try {
                asignacionCatedraticoEntity = em.getReference(AsignacionCatedraticoEntity.class, id);
                asignacionCatedraticoEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignacionCatedraticoEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(asignacionCatedraticoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsignacionCatedraticoEntity> findAsignacionCatedraticoEntityEntities() {
        return findAsignacionCatedraticoEntityEntities(true, -1, -1);
    }

    public List<AsignacionCatedraticoEntity> findAsignacionCatedraticoEntityEntities(int maxResults, int firstResult) {
        return findAsignacionCatedraticoEntityEntities(false, maxResults, firstResult);
    }

    private List<AsignacionCatedraticoEntity> findAsignacionCatedraticoEntityEntities(boolean all, int maxResults, int firstResult) {
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

    public AsignacionCatedraticoEntity findAsignacionCatedraticoEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsignacionCatedraticoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignacionCatedraticoEntityCount() {
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
