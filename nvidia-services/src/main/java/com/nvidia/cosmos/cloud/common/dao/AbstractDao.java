package com.nvidia.cosmos.cloud.common.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
 
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
 
/**
 * @author pbatta
 *
 * @param <PK>
 * @param <T>
 */
public abstract class AbstractDao<PK extends Serializable, T> {
     
    private final Class<T> persistentClass;
     
    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
     
    @Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
 
    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }
 
    public void persist(T entity) {
        getSession().persist(entity);
    }
    
    public void merge(T entity) {
        getSession().merge(entity);
    }
    @SuppressWarnings("unchecked")
    public void delete(T entity) {
    	entity = (T) getSession().merge( entity);
        getSession().delete(entity);
    }
    
    public boolean deleteById(Class<?> type, Serializable id) {
        Object persistentInstance =  getSession().load(type, id);
        if (persistentInstance != null) {
        	 getSession().delete(persistentInstance);
            return true;
        }
        return false;
    }
     
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
 
}
