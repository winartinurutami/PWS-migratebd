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
public class ListJpaController implements Serializable {

    public ListJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(List list) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(list);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findList(list.getIdTiket()) != null) {
                throw new PreexistingEntityException("List " + list + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(List list) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            list = em.merge(list);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = list.getIdTiket();
                if (findList(id) == null) {
                    throw new NonexistentEntityException("The list with id " + id + " no longer exists.");
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
            List list;
            try {
                list = em.getReference(List.class, id);
                list.getIdTiket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The list with id " + id + " no longer exists.", enfe);
            }
            em.remove(list);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<klmpk8.traktix.List> findListEntities() {
        return findListEntities(true, -1, -1);
    }

    public List<klmpk8.traktix.List> findListEntities(int maxResults, int firstResult) {
        return findListEntities(false, maxResults, firstResult);
    }

    private List<klmpk8.traktix.List> findListEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(List.class));
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

    public klmpk8.traktix.List findList(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(List.class, id);
        } finally {
            em.close();
        }
    }

    public int getListCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<List> rt = cq.from(List.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
