/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sce.persona.orm;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.persona.orm.TablaExtendidaEntity;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class TablaExtendidaJpaController implements Serializable {

    public TablaExtendidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public TablaExtendidaEntity buscarTablaExtendida(String nombreTabla) {
        EntityManager em = getEntityManager();
        TypedQuery<TablaExtendidaEntity> query = em.createNamedQuery("TablaExtendida.buscarTablaExtendida", TablaExtendidaEntity.class);
        List<TablaExtendidaEntity> encontrados = query.setParameter("nombreTabla", nombreTabla).getResultList();
        if (encontrados.isEmpty()) {
            return null;
        }
        return encontrados.get(0);
    }

    public void create(TablaExtendidaEntity nombreTablaEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nombreTablaEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void create(TablaExtendidaEntity nombreTablaEntity, EntityManager em) {
        em.persist(nombreTablaEntity);
    }

    public void edit(TablaExtendidaEntity nombreTablaEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nombreTablaEntity = em.merge(nombreTablaEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = nombreTablaEntity.getId();
                if (findNombreTablaEntity(id) == null) {
                    throw new NonexistentEntityException("The nombreTablaEntity with id " + id + " no longer exists.");
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
            TablaExtendidaEntity nombreTablaEntity;
            try {
                nombreTablaEntity = em.getReference(TablaExtendidaEntity.class, id);
                nombreTablaEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nombreTablaEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(nombreTablaEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TablaExtendidaEntity> findNombreTablaEntityEntities() {
        return findNombreTablaEntityEntities(true, -1, -1);
    }

    public List<TablaExtendidaEntity> findNombreTablaEntityEntities(int maxResults, int firstResult) {
        return findNombreTablaEntityEntities(false, maxResults, firstResult);
    }

    private List<TablaExtendidaEntity> findNombreTablaEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TablaExtendidaEntity.class));
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

    public TablaExtendidaEntity findNombreTablaEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TablaExtendidaEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getNombreTablaEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TablaExtendidaEntity> rt = cq.from(TablaExtendidaEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
