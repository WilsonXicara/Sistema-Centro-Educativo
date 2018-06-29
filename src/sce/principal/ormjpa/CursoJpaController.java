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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sce.principal.entity.CursoEntity;
import sce.principal.ormjpa.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class CursoJpaController implements Serializable {

    public CursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CursoEntity buscarCursoDescripcion(CursoEntity curso) {
        EntityManager em = getEntityManager();
        TypedQuery<CursoEntity> query = em.createNamedQuery("Curso.buscarCurso", CursoEntity.class);
        List<CursoEntity> encontrados = query
                .setParameter("nombreCurso", curso.getCurso())
                .getResultList();
        if (encontrados.isEmpty()) {
            return null;
        }
        return encontrados.get(0);
    }

    public void create(CursoEntity cursoEntity) {
        EntityManager em = null;
        try {
            CursoEntity existente = buscarCursoDescripcion(cursoEntity);
            if (existente != null) {
                cursoEntity.copy(existente);
                return;
            }
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cursoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CursoEntity cursoEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cursoEntity = em.merge(cursoEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cursoEntity.getId();
                if (findCursoEntity(id) == null) {
                    throw new NonexistentEntityException("The cursoEntity with id " + id + " no longer exists.");
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
            CursoEntity cursoEntity;
            try {
                cursoEntity = em.getReference(CursoEntity.class, id);
                cursoEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursoEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(cursoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CursoEntity> findCursoEntityEntities() {
        return findCursoEntityEntities(true, -1, -1);
    }

    public List<CursoEntity> findCursoEntityEntities(int maxResults, int firstResult) {
        return findCursoEntityEntities(false, maxResults, firstResult);
    }

    private List<CursoEntity> findCursoEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CursoEntity.class));
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

    public CursoEntity findCursoEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CursoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CursoEntity> rt = cq.from(CursoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
