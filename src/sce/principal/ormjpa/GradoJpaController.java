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
import sce.principal.entity.GradoEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;
import sce.principal.command.GradoCommand;

/**
 *
 * @author Usuario
 */
public class GradoJpaController implements Serializable, GradoCommand {

    public GradoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GradoEntity gradoEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(gradoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GradoEntity gradoEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            gradoEntity = em.merge(gradoEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = gradoEntity.getId();
                if (findGradoEntity(id) == null) {
                    throw new NonexistentEntityException("The gradoEntity with id " + id + " no longer exists.");
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
            GradoEntity gradoEntity;
            try {
                gradoEntity = em.getReference(GradoEntity.class, id);
                gradoEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gradoEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(gradoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GradoEntity> findGradoEntityEntities() {
        return findGradoEntityEntities(true, -1, -1);
    }

    public List<GradoEntity> findGradoEntityEntities(int maxResults, int firstResult) {
        return findGradoEntityEntities(false, maxResults, firstResult);
    }

    private List<GradoEntity> findGradoEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GradoEntity.class));
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

    public GradoEntity findGradoEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GradoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getGradoEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GradoEntity> rt = cq.from(GradoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public void consultarCursos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarCatedratico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarEstudiantes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
