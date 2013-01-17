package com.clip.core.base.dao;

import com.clip.core.utils.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 支持 Hibernate4 的操作
 */
@Transactional(propagation = Propagation.REQUIRED)
public class BaseHibernateDao4<E, PK extends Serializable> implements BaseHibernateDaoInterface<E, PK> {

    protected final Log log = LogFactory.getLog(getClass());
    protected Class<E> entityClass;

    public BaseHibernateDao4(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    public List<E> getAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(entityClass);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return criteria.list();
        } catch (Exception e) {
            log.error("BaseHibernateDao error:get", e);
//            e.printStackTrace();
        }
        return null;
    }

    public E get(PK id) throws ObjectRetrievalFailureException {
        E entity = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            entity = (E) session.get(this.entityClass, id);
        } catch (Exception e) {
            log.error("BaseHibernateDao error:get", e);
        }
        if (entity == null) {
            log.warn("Uh oh, '" + this.entityClass + "' object with id '" + id + "' not found...");
//            throw new ObjectRetrievalFailureException(this.entityClass, id);
        }
        return entity;
    }

    public E load(PK id) throws ObjectRetrievalFailureException {
        E entity = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            entity = (E) session.load(this.entityClass, id);
        } catch (Exception e) {
            log.error("BaseHibernateDao error:load", e);
        }
        if (entity == null) {
            log.warn("Uh oh, '" + this.entityClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.entityClass, id);
        }
        return entity;
    }

    public boolean exists(PK id) {
        try {
            E entity = get(id);
            return entity != null;
        } catch (ObjectRetrievalFailureException ignore) {
            return false;
        }
    }

    public void refresh(Object object) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.refresh(object);
        } catch (Exception e) {
            log.error("BaseHibernateDao error:refresh", e);
        }
    }

    public E add(E object) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return (E) session.merge(object);
        } catch (Exception e) {
            log.error("BaseHibernateDao error:add", e);
        }
        return null;
    }

    public boolean update(E entity) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(entity);
            return true;
        } catch (Exception e) {
            log.error("BaseHibernateDao error:update", e);
        }
        return false;
    }

    /**
     * 支持带 id 的对象数据更新，不会因对象不是 get 出来的而不可更新，对象可自己创建并设入 id 后更新
     *
     * @param object
     * @return
     */
    public E autoSave(E object) {
        //super.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        Object pkId = null;
        try {
            pkId = getPKValueFromPersistentObject(object);
            if (pkId != null) { //如果没主键 id 则插一条记录
                E entity = get((PK) pkId);
                //改造过的非 null 的函数，注意 source 和 dest 的区别
                if (entity != null) {
                    BeanUtilsNoNull.copyProperties(object /*org*/, entity /*dest*/);
                }
            }
            return add(object);
        } catch (Exception e) {
            log.error("BaseHibernateDao error:autoSave", e);
        }
        return null;
    }

    public void remove(PK id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(this.get(id));
        } catch (Exception e) {
            log.error("BaseHibernateDao error:remove", e);
        }
    }


    /**
     * 条件删除 目前只能先查询再删除 每好的解决办法
     *
     * @param hql
     * @param params
     */
    public void remove(String hql, Object[] params) {
        try {
            Session session = sessionFactory.getCurrentSession();
            List deleteList = find(hql, params);
            session.delete(deleteList);
        } catch (Exception e) {
            log.error("BaseHibernateDao error:remove", e);
        }
    }


    public void remove(E deleteOne) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(deleteOne);
        } catch (Exception e) {
            log.error("BaseHibernateDao error:remove", e);
        }
    }


    public List<E> find(final String queryString, final Object... values) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query queryObject = session.createQuery(queryString);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    queryObject.setParameter(i, values[i]);
                }
            }
            return queryObject.list();
        } catch (Exception e) {
            log.error("BaseHibernateDao error:find", e);
        }
        return null;
    }

    public E findFirst(final String queryString, final Object... values) {
        try {
            List<E> objects = find(queryString, values);
            if (objects == null || objects.size() == 0) {
                return null;
            }
            return objects.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void remove(List<E> deleteList) {
        try {
            Session session = sessionFactory.getCurrentSession();
            for (Iterator<E> iterator = deleteList.iterator(); iterator.hasNext(); ) {
                E delOne = iterator.next();
                try {
                    session.delete(delOne);
                } catch (HibernateException e) {
                    continue;
                }
            }
        } catch (Exception e) {
            log.error("BaseHibernateDao error:remove", e);
        }
    }

    public void removeBatch(final Class<E> clazz, final Serializable[] ids) {
        try {
            Session session = sessionFactory.getCurrentSession();
            for (int i = 0; i < ids.length; i++) {
                Object obj = session.load(clazz, ids[i]);
                if (obj != null) {
                    session.delete(obj);
                } else {
                    log.warn("无法删除主键为:" + ids[i] + "的" + clazz.getName());
                }
            }
        } catch (Exception e) {
            log.error("BaseHibernateDao error:remove", e);
        }
    }


    public boolean batchSave(List<E> list) {
        if (list.size() == 0) {
            return false;
        } else {
            try {
                for (Iterator<E> saveItemIterator = list.iterator(); saveItemIterator.hasNext(); ) {
                    E saveItem = saveItemIterator.next();
                    add(saveItem);
                }
                flushSession();
                return true;
            } catch (Exception e) {
                log.error("BaseHibernateDao error:batchSave", e);
                return false;
            }
        }
    }

    private void flushSession() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.flush();
            session.clear();
        } catch (Exception e) {
            log.error("BaseHibernateDao error:flushSession", e);
        }
    }

    public boolean autoBatchSave(List<E> list) {
        if (list.size() == 0) {
            return false;
        } else {
            try {
                for (Iterator<E> saveItemIterator = list.iterator(); saveItemIterator.hasNext(); ) {
                    E saveItem = saveItemIterator.next();
                    autoSave(saveItem);
                }
                flushSession();
                return true;
            } catch (Exception e) {
                log.error("BaseHibernateDao error:autoBatchSave", e);
                return false;
            }
        }
    }


    protected Object getPKValueFromPersistentObject(E object) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = object.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (int j = 0; j < annotations.length; j++) {
                Annotation annotation = annotations[j];
                if (annotation instanceof javax.persistence.Id) {
                    return method.invoke(object);
                }
            }
        }
        return null;
    }


    public List<E> find(final String hql, final Map paramMap) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql);
            setParamMap(query, paramMap);
            return query.list();
        } catch (Exception e) {
            log.error("BaseHibernateDao error:find", e);
        }
        return null;
    }

    protected void setParamMap(Query query, Map paramMap) {
        if (paramMap == null) return;
        for (Object obj : paramMap.entrySet()) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value.getClass().getName().startsWith("[") && value.getClass().getName().length() == 2) {
                //将元生类数组转为对象数组  primitive type
                if (value instanceof long[]) {
                    long[] p = (long[]) value;
                    Object[] objs = new Object[p.length];
                    for (int i = 0; i < p.length; i++) {
                        objs[i] = p[i];
                    }
                    value = objs;
                } else if (value instanceof int[]) {
                    int[] p = (int[]) value;
                    Object[] objs = new Object[p.length];
                    for (int i = 0; i < p.length; i++) {
                        objs[i] = p[i];
                    }
                    value = objs;
                } else if (value instanceof float[]) {
                    float[] p = (float[]) value;
                    Object[] objs = new Object[p.length];
                    for (int i = 0; i < p.length; i++) {
                        objs[i] = p[i];
                    }
                    value = objs;
                } else if (value instanceof double[]) {
                    double[] p = (double[]) value;
                    Object[] objs = new Object[p.length];
                    for (int i = 0; i < p.length; i++) {
                        objs[i] = p[i];
                    }
                    value = objs;
                } else if (value instanceof short[]) {
                    short[] p = (short[]) value;
                    Object[] objs = new Object[p.length];
                    for (int i = 0; i < p.length; i++) {
                        objs[i] = p[i];
                    }
                    value = objs;
                } else if (value instanceof byte[]) {
                    byte[] p = (byte[]) value;
                    Object[] objs = new Object[p.length];
                    for (int i = 0; i < p.length; i++) {
                        objs[i] = p[i];
                    }
                    value = objs;
                }
            }

            if (value.getClass().getName().startsWith("[")) {
                query.setParameterList(key.toString(), (Object[]) value);
            } else if (value instanceof Collection) {
                query.setParameterList(key.toString(), (Collection) value);
            } else {
                query.setParameter(key.toString(), value);
            }
        }
    }

}
