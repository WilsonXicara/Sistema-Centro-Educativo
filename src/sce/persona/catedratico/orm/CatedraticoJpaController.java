/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.catedratico.orm;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.persona.catedratico.orm.CatedraticoEntity;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class CatedraticoJpaController implements Serializable {

    public CatedraticoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CatedraticoEntity buscarPorDpi(String dpi){
        EntityManager em = getEntityManager();
        TypedQuery<CatedraticoEntity> query = em.createNamedQuery("Catedratico.buscarPorDpi",CatedraticoEntity.class);
        List<CatedraticoEntity> encontrados = query.setParameter("dpi", dpi).getResultList();
        if (encontrados.isEmpty()){
            return null;
        }
        return encontrados.get(0);
    }

    public void create(CatedraticoEntity catedraticoEntity) {
        EntityManager em = null;
        try {
            CatedraticoEntity existente = buscarPorDpi(catedraticoEntity.getDpi());
            if (existente != null){
                catedraticoEntity.copy(existente);
                return;
            }
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(catedraticoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void create(CatedraticoEntity catedraticoEntity, EntityManager em) {
        em.persist(catedraticoEntity);
    }

    public void edit(CatedraticoEntity catedraticoEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            catedraticoEntity = em.merge(catedraticoEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = catedraticoEntity.getId();
                if (findCatedraticoEntity(id) == null) {
                    throw new NonexistentEntityException("The catedraticoEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void edit(CatedraticoEntity catedraticoEntity, EntityManager em) {
        em.merge(catedraticoEntity);
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatedraticoEntity catedraticoEntity;
            try {
                catedraticoEntity = em.getReference(CatedraticoEntity.class, id);
                catedraticoEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catedraticoEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(catedraticoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CatedraticoEntity> findCatedraticoEntityEntities() {
        return findCatedraticoEntityEntities(true, -1, -1);
    }

    public List<CatedraticoEntity> findCatedraticoEntityEntities(int maxResults, int firstResult) {
        return findCatedraticoEntityEntities(false, maxResults, firstResult);
    }

    private List<CatedraticoEntity> findCatedraticoEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CatedraticoEntity.class));
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

    public CatedraticoEntity findCatedraticoEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatedraticoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatedraticoEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CatedraticoEntity> rt = cq.from(CatedraticoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
