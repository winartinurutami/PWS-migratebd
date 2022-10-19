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
public class DetailJpaController implements Serializable {

    public DetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detail detail) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detail);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetail(detail.getNama()) != null) {
                throw new PreexistingEntityException("Detail " + detail + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detail detail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detail = em.merge(detail);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = detail.getNama();
                if (findDetail(id) == null) {
                    throw new NonexistentEntityException("The detail with id " + id + " no longer exists.");
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
            Detail detail;
            try {
                detail = em.getReference(Detail.class, id);
                detail.getNama();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detail with id " + id + " no longer exists.", enfe);
            }
            em.remove(detail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detail> findDetailEntities() {
        return findDetailEntities(true, -1, -1);
    }

    public List<Detail> findDetailEntities(int maxResults, int firstResult) {
        return findDetailEntities(false, maxResults, firstResult);
    }

    private List<Detail> findDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detail.class));
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

    public Detail findDetail(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detail.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detail> rt = cq.from(Detail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
