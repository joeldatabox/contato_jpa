/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.contatojpa.dao;

import br.com.contatojpa.dao.connection.JPAUtil;
import br.com.contatojpa.model.Model;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Joel Rodrigues
 */
public class GenericDao<T extends Model> implements Dao<T> {

    private Class<T> clazz;

    public GenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T getById(Long id) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        T model = null;
        try {
            model = em.find(clazz, id);
        } finally {
            close(em);
        }
        return model;
    }

    @Override
    public T save(T model) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            model = em.merge(model);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            close(em);
        }
        return model;
    }

    @Override
    public boolean delete(Model model) throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(model);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            close(em);
        }
    }

    @Override
    public List<T> list() throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT m FROM " + clazz.getSimpleName() + " AS m").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(em);
        }
        return null;
    }

    @Override
    public Long count() throws Exception {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return (Long) em.createQuery("SELECT COUNT(m) FROM " + clazz.getSimpleName() + " AS m").getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(em);
        }
        return null;
    }

    protected void close(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
