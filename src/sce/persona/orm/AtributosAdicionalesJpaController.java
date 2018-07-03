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
import sce.persona.orm.AtributosAdicionalesEntity;
import sce.excepciones.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class AtributosAdicionalesJpaController implements Serializable {

    public AtributosAdicionalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<AtributosAdicionalesEntity> buscarAtributosParaTabla(Long idTablaExtendida) {
        EntityManager em = getEntityManager();
        TypedQuery<AtributosAdicionalesEntity> query = em.createNamedQuery("AtributoAdicional.buscarAtributosParaTabla", AtributosAdicionalesEntity.class);
        return query.setParameter("idTablaExtendida", idTablaExtendida).getResultList();
    }

    public void create(AtributosAdicionalesEntity atributosAdicionalesEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(atributosAdicionalesEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void create(AtributosAdicionalesEntity atributosAdicionalesEntity, EntityManager em) {
        em.persist(atributosAdicionalesEntity);
    }

    public void edit(AtributosAdicionalesEntity atributosAdicionalesEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            atributosAdicionalesEntity = em.merge(atributosAdicionalesEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = atributosAdicionalesEntity.getId();
                if (findAtributosAdicionalesEntity(id) == null) {
                    throw new NonexistentEntityException("The atributosAdicionalesEntity with id " + id + " no longer exists.");
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
            AtributosAdicionalesEntity atributosAdicionalesEntity;
            try {
                atributosAdicionalesEntity = em.getReference(AtributosAdicionalesEntity.class, id);
                atributosAdicionalesEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The atributosAdicionalesEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(atributosAdicionalesEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public void destroy(Long id, EntityManager em) throws NonexistentEntityException {
        try {
            AtributosAdicionalesEntity atributosAdicionalesEntity;
            try {
                atributosAdicionalesEntity = em.getReference(AtributosAdicionalesEntity.class, id);
                atributosAdicionalesEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("No existe un AtributoAdicional con id="+id, enfe);
            }
            em.remove(atributosAdicionalesEntity);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AtributosAdicionalesEntity> findAtributosAdicionalesEntityEntities() {
        return findAtributosAdicionalesEntityEntities(true, -1, -1);
    }

    public List<AtributosAdicionalesEntity> findAtributosAdicionalesEntityEntities(int maxResults, int firstResult) {
        return findAtributosAdicionalesEntityEntities(false, maxResults, firstResult);
    }

    private List<AtributosAdicionalesEntity> findAtributosAdicionalesEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AtributosAdicionalesEntity.class));
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

    public AtributosAdicionalesEntity findAtributosAdicionalesEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AtributosAdicionalesEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAtributosAdicionalesEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AtributosAdicionalesEntity> rt = cq.from(AtributosAdicionalesEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
