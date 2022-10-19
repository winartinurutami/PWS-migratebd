/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klmpk8.traktix;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import klmpk8.traktix.exceptions.NonexistentEntityException;
import klmpk8.traktix.exceptions.PreexistingEntityException;

/**
 *
 * @author winarti nur utami
 */
public class PassageJpaController implements Serializable {

    public PassageJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Passage passage) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(passage);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPassage(passage.getTipeid()) != null) {
                throw new PreexistingEntityException("Passage " + passage + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Passage passage) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            passage = em.merge(passage);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = passage.getTipeid();
                if (findPassage(id) == null) {
                    throw new NonexistentEntityException("The passage with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Passage passage;
            try {
                passage = em.getReference(Passage.class, id);
                passage.getTipeid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The passage with id " + id + " no longer exists.", enfe);
            }
            em.remove(passage);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Passage> findPassageEntities() {
        return findPassageEntities(true, -1, -1);
    }

    public List<Passage> findPassageEntities(int maxResults, int firstResult) {
        return findPassageEntities(false, maxResults, firstResult);
    }

    private List<Passage> findPassageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Passage.class));
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

    public Passage findPassage(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Passage.class, id);
        } finally {
            em.close();
        }
    }

    public int getPassageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Passage> rt = cq.from(Passage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
