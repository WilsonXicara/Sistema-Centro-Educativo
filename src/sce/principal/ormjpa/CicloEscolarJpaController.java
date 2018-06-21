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
import sce.principal.entity.CicloEscolarEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;
import sce.principal.command.CicloEscolarCommand;

/**
 *
 * @author Usuario
 */
public class CicloEscolarJpaController implements Serializable, CicloEscolarCommand {

    public CicloEscolarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CicloEscolarEntity cicloEscolarEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cicloEscolarEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CicloEscolarEntity cicloEscolarEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cicloEscolarEntity = em.merge(cicloEscolarEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cicloEscolarEntity.getId();
                if (findCicloEscolarEntity(id) == null) {
                    throw new NonexistentEntityException("The cicloEscolarEntity with id " + id + " no longer exists.");
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
            CicloEscolarEntity cicloEscolarEntity;
            try {
                cicloEscolarEntity = em.getReference(CicloEscolarEntity.class, id);
                cicloEscolarEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cicloEscolarEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(cicloEscolarEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CicloEscolarEntity> findCicloEscolarEntityEntities() {
        return findCicloEscolarEntityEntities(true, -1, -1);
    }

    public List<CicloEscolarEntity> findCicloEscolarEntityEntities(int maxResults, int firstResult) {
        return findCicloEscolarEntityEntities(false, maxResults, firstResult);
    }

    private List<CicloEscolarEntity> findCicloEscolarEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CicloEscolarEntity.class));
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

    public CicloEscolarEntity findCicloEscolarEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CicloEscolarEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getCicloEscolarEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CicloEscolarEntity> rt = cq.from(CicloEscolarEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public void abrirCiclo(CicloEscolarEntity cicloEscolar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cerrarCiclo(CicloEscolarEntity cicloEscolar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarGrados(CicloEscolarEntity cicloEscolar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarCalendario(CicloEscolarEntity cicloEscolar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}