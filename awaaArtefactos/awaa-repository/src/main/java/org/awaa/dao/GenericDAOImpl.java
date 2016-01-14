/**
 * 
 */
package org.awaa.dao;

/**
 * @author john
 *
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.awwa.utils.exeptions.BusinessExeption;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

;

/**
 * Represents the Data Access Object class, and defines the generic interaction
 * between the system and the database.
 * 
 * @author John
 */
@Repository("GenericDAO")
@Transactional
public class GenericDAOImpl<T, Key extends Serializable> implements GenericDAO<T, Key> {

	/**
	 * variable que contrala la session con la base de datos
	 */
	@Autowired
	private SessionFactory sessionFactory;
	// Valores para operaciones especiales
	private Session session;
	private Transaction transaction;

	/**
	 * Función encargada de retornal la actual session para realizar
	 * operaciones con la base de datos
	 * 
	 * @return CurrentSession
	 */
	@Transactional
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void handleException(Exception e) throws BusinessExeption {
		throw new BusinessExeption(e);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void handleExceptionRollBack(Exception e) throws BusinessExeption {
		this.transaction.rollback();
		if (this.session != null) {
			this.session.flush();
			this.session.close();
			this.session = null;
		}
		throw new BusinessExeption(e);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T obj) throws BusinessExeption {
		try {
			this.getSession().delete(obj);
		} catch (Exception ex) {
			handleException(ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public <Z> void delete(Class<Z> clazz, Z obj) throws BusinessExeption {
		try {
			this.getSession().delete(obj);
		} catch (Exception ex) {
			handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T find(Class<T> clazz, Key id) throws BusinessExeption {
		T resultado = null;
		try {
			resultado = ((T) this.getSession().get(clazz, id));
		} catch (Exception ex) {
			handleException(ex);
		}
		return resultado;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param <Z>
	 * @param <KClass>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <Z, CKey extends Serializable> Z find(Class<Z> clazz, Class<CKey> cKey, CKey id) throws BusinessExeption {
		Z resultado = null;
		try {
			resultado = ((Z) this.getSession().get(clazz, id));
		} catch (Exception ex) {
			handleException(ex);
		}
		return resultado;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findAll(Class<T> clazz) throws BusinessExeption {
		List<T> resultados = null;
		try {
			resultados = this.getSession().createCriteria(clazz).list();
		} catch (Exception ex) {
			handleException(ex);
		}
		return resultados;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findAllByExample(T ejemplo) throws BusinessExeption {
		List<T> resultados = null;
		try {
			Criteria criteria = this.getSession().createCriteria(ejemplo.getClass());
			criteria.add(Example.create(ejemplo));
			resultados = criteria.list();
		} catch (Exception ex) {
			handleException(ex);
		}
		return resultados;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T findByExample(T ejemplo) throws BusinessExeption {
		T result = null;
		try {
			Criteria criteria = this.getSession().createCriteria(ejemplo.getClass());
			criteria.add(Example.create(ejemplo));
			criteria.setMaxResults(1);
			result = (T) criteria.uniqueResult();
		} catch (Exception ex) {
			handleException(ex);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public T save(T obj) throws BusinessExeption {
		try {

			this.getSession().save(obj);

		} catch (Exception ex) {
			handleException(ex);
		}
		return obj;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public <Z> Z save(Class<Z> clazz, Z obj) throws BusinessExeption {
		try {

			this.getSession().save(obj);

		} catch (Exception ex) {
			handleException(ex);
		}
		return obj;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T obj) throws BusinessExeption {
		try {

			this.getSession().update(obj);

		} catch (Exception ex) {
			handleException(ex);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public <Z> void update(Class<Z> Z, Z obj) throws BusinessExeption {
		try {

			this.getSession().update(obj);

		} catch (Exception ex) {
			handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer saveOrUpdateSql(Class<T> clazz, final String sql) throws BusinessExeption {
		try {

			Query q = getSession().createSQLQuery(sql);
			int i = q.executeUpdate();

			return i;
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer editHQL(final String hql, Map<String, Object> params) throws BusinessExeption {
		try {

			Query q = this.getSession().createQuery(hql);
			if (params != null) {
				Iterator<Entry<String, Object>> itParams = params.entrySet().iterator();
				while (itParams.hasNext()) {
					Map.Entry<String, Object> e = (Map.Entry<String, Object>) itParams.next();
					q.setParameter(e.getKey(), e.getValue());
				}
			}
			int i = q.executeUpdate();

			return i;
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> clazz, String filter) throws BusinessExeption {
		try {
			StringBuilder sb = new StringBuilder("from ").append(clazz.getSimpleName());
			if (!StringUtils.isEmpty(filter)) {
				sb.append(" where ").append(filter);
			}
			return this.getSession().createCriteria(sb.toString()).list();
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@SuppressWarnings("unchecked")
	public List<T> findAllSql(final Class<T> clazz, final String sql) throws BusinessExeption {
		try {
			Query query = this.getSession().createSQLQuery(sql).addEntity(clazz);
			return query.list();
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<T> findSql(Class<T> clazz, final String sql, final Map<String, Object> params) throws BusinessExeption {
		try {
			Query qr = this.getSession().createSQLQuery(sql).addEntity(clazz);
			for (Entry<String, Object> parm : params.entrySet()) {
				qr.setParameter(parm.getKey(), parm.getValue());
			}
			qr.isReadOnly();
			return qr.list();
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public <Z> List<Z> findSqlBean(Class<Z> clazz, final String sql, final Map<String, Object> params)
			throws BusinessExeption {
		try {
			Query qr = this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clazz));
			if (params != null) {
				for (Entry<String, Object> parm : params.entrySet()) {
					qr.setParameter(parm.getKey(), parm.getValue());
				}
			}
			qr.isReadOnly();
			return new ArrayList<Z>(qr.list());
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findHql(Class<T> clazz, String hql) throws BusinessExeption {
		try {
			if (!StringUtils.isEmpty(hql)) {
				return this.getSession().createQuery(hql).list();
			}
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findHql(Class<T> clazz, String hql, Map<String, Object> params) throws BusinessExeption {
		try {
			if (!StringUtils.isEmpty(hql)) {
				Query q = this.getSession().createQuery(hql);
				Iterator<Entry<String, Object>> itParams = params.entrySet().iterator();
				while (itParams.hasNext()) {
					Map.Entry<String, Object> e = (Map.Entry<String, Object>) itParams.next();
					q.setParameter(e.getKey(), e.getValue());
				}
				return q.list();
			} else {
				return this.getSession().createCriteria(clazz).list();
			}
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findAll(Class<T> clazz, String filter, String Order) throws BusinessExeption {
		try {
			StringBuilder sb = new StringBuilder("from ").append(clazz.getSimpleName());
			if (!StringUtils.isEmpty(filter)) {
				sb.append(" where ").append(filter);
			}
			if (!StringUtils.isEmpty(Order)) {
				sb.append(" order by ").append(Order);
			}
			return this.getSession().createQuery(sb.toString()).list();
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdate(T obj) throws BusinessExeption {
		try {

			this.getSession().saveOrUpdate(obj);

		} catch (Exception ex) {
			handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public <Z> void saveOrUpdate(Class<Z> clazz, Z obj) throws BusinessExeption {
		try {

			this.getSession().saveOrUpdate(obj);

		} catch (Exception ex) {
			handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findAll(Class<T> clazz, Map<String, Object> params) throws BusinessExeption {
		try {
			DetachedCriteria crit = DetachedCriteria.forClass(clazz);
			if (params != null) {
				Set<Entry<String, Object>> setParams = params.entrySet();
				Iterator<Entry<String, Object>> itParams = setParams.iterator();
				while (null != itParams && itParams.hasNext()) {
					Entry<String, Object> entry = itParams.next();
					if (String.class.isAssignableFrom(entry.getValue().getClass())) {
						crit.add(Restrictions.like(entry.getKey(), entry.getValue()));
					} else {
						crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
					}
				}
			}
			Criteria criteria = crit.getExecutableCriteria(this.getSession());

			return criteria.list();
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findAll(Class<T> clazz, Map<String, Object> params, String orderProperty) throws BusinessExeption {
		try {
			DetachedCriteria crit = DetachedCriteria.forClass(clazz);
			Set<Entry<String, Object>> setParams = params.entrySet();
			Iterator<Entry<String, Object>> itParams = setParams.iterator();
			while (null != itParams && itParams.hasNext()) {
				Entry<String, Object> entry = itParams.next();
				if (String.class.isAssignableFrom(entry.getValue().getClass())) {
					crit.add(Restrictions.like(entry.getKey(), entry.getValue()));
				} else {
					crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
				}
			}
			if (!StringUtils.isEmpty(orderProperty)) {
				crit.addOrder(Order.asc(orderProperty));
			}
			Criteria criteria = crit.getExecutableCriteria(this.getSession());

			return criteria.list();
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findAll(Class<T> clazz, Map<String, Object> params, String orderProperty, boolean desc)
			throws BusinessExeption {
		try {
			DetachedCriteria crit = DetachedCriteria.forClass(clazz);
			Set<Entry<String, Object>> setParams = params.entrySet();
			Iterator<Entry<String, Object>> itParams = setParams.iterator();
			while (null != itParams && itParams.hasNext()) {
				Entry<String, Object> entry = itParams.next();
				if (String.class.isAssignableFrom(entry.getValue().getClass())) {
					crit.add(Restrictions.like(entry.getKey(), entry.getValue()));
				} else {
					crit.add(Restrictions.eq(entry.getKey(), entry.getValue()));
				}
			}
			if (!StringUtils.isEmpty(orderProperty)) {
				crit.addOrder(desc ? Order.desc(orderProperty) : Order.asc(orderProperty));
			}
			Criteria criteria = crit.getExecutableCriteria(this.getSession());

			return criteria.list();
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdateAll(List<T> listaElementos) throws BusinessExeption {
		try {
			// this.startOperation();
			for (T t : listaElementos) {
				this.getSession().saveOrUpdate(t);
			}
			// this.endOperation();
		} catch (Exception ex) {
			handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public <Z> void saveOrUpdateAll(Class<Z> clazz, List<Z> listaElementos) throws BusinessExeption {
		try {
			// this.startOperation();
			for (Z z : listaElementos) {
				this.getSession().saveOrUpdate(z);
			}
			// this.endOperation();
		} catch (Exception ex) {
			handleException(ex);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@SuppressWarnings("unchecked")
	public List<T> executeNameQuery(String queryName, String[] nombreParametros, Object[] valoresParametros)
			throws BusinessExeption {
		try {
			Query queryObject = this.getSession().getNamedQuery(queryName);
			if (valoresParametros != null) {
				for (int i = 0; i < valoresParametros.length; i++) {
					applyNamedParameterToQuery(queryObject, nombreParametros[i], valoresParametros[i]);
				}
			}
			return queryObject.list();
		} catch (Exception ex) {
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@SuppressWarnings("unchecked")
	public T merge(T obj) throws BusinessExeption {
		T resultado = null;
		try {

			resultado = ((T) this.getSession().merge(obj));

		} catch (Exception ex) {
			handleException(ex);
		}
		return resultado;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<T> findCriteriaDinamico(DetachedCriteria detachedCriteria) throws BusinessExeption {

		try {
			Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());

			return new ArrayList<T>(criteria.list());
		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public boolean findCriteriaDinamicoExist(DetachedCriteria detachedCriteria) throws BusinessExeption {

		try {
			Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
			criteria.setMaxResults(1);
			return criteria.uniqueResult() != null;
		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<T> findCriteriaDinamico(DetachedCriteria detachedCriteria, int limit) throws BusinessExeption {

		List<T> tmpLst = new ArrayList<T>();
		try {
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());
			if (limit > 0) {
				executableCriteria.setMaxResults(limit);
			}
			return executableCriteria.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return tmpLst;

		// return this.getSession().findByCriteria(detachedCriteria);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public <Z> List<Z> findCriteriaDinamico(Class<Z> clazz, Projection projections, DetachedCriteria detachedCriteria,
			int pageSize, int page) throws BusinessExeption {
		detachedCriteria.setProjection(projections);
		detachedCriteria.setResultTransformer(Transformers.aliasToBean(clazz));
		List<Z> tmpLst = new ArrayList<Z>();
		try {
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());

			if (page > 0) {
				executableCriteria.setFirstResult((page - 1) * pageSize);
			}
			if (pageSize > 0) {
				executableCriteria.setMaxResults(pageSize);
			}

			return executableCriteria.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return tmpLst;

		// return this.getSession().findByCriteria(detachedCriteria);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public <Z> List<Z> findCriteriaDinamico(Class<Z> clazz, DetachedCriteria detachedCriteria,
			ResultTransformer transformers, Projection projections) throws BusinessExeption {
		detachedCriteria.setProjection(projections);
		detachedCriteria.setResultTransformer(transformers);
		List<Z> tmpLst = new ArrayList<Z>();
		try {
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());

			return executableCriteria.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return tmpLst;

		// return this.getSession().findByCriteria(detachedCriteria);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public T findCriteriaDinamicouniqueResult(DetachedCriteria detachedCriteria, int pageSize, int page)
			throws BusinessExeption {
		try {
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());
			if (page > 0) {
				executableCriteria.setFirstResult((page - 1) * pageSize);
			}
			if (pageSize > 0) {
				executableCriteria.setMaxResults(pageSize);
			}
			return ((T) executableCriteria.uniqueResult());
		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return null;

		// return this.getSession().findByCriteria(detachedCriteria);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public <Z> Z findCriteriaDinamicouniqueResult(Class<Z> Z, DetachedCriteria detachedCriteria,
			ResultTransformer resultTransformer, Projection projection) throws BusinessExeption {
		try {
			if (projection != null)
				detachedCriteria.setProjection(projection);
			if (resultTransformer != null)
				detachedCriteria.setResultTransformer(resultTransformer);

			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());
			executableCriteria.setMaxResults(1);
			return ((Z) executableCriteria.uniqueResult());
		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return null;

		// return this.getSession().findByCriteria(detachedCriteria);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<T> findCriteriaDinamico(DetachedCriteria detachedCriteria, int pageSize, int page)
			throws BusinessExeption {

		List<T> tmpLst = new ArrayList<T>();
		try {
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());
			if (page > 0) {
				executableCriteria.setFirstResult((page - 1) * pageSize);
			}
			if (pageSize > 0) {
				executableCriteria.setMaxResults(pageSize);
			}
			return executableCriteria.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return tmpLst;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public <Z> PagingResult<Z> findCriteriaDinamicoPageResult(Class<Z> clazz, DetachedCriteria detachedCriteria,
			int pageSize, int page) throws BusinessExeption {
		PagingResult<Z> pagingResult = new PagingResult<Z>();
		List<Z> tmpLst = new ArrayList<Z>();
		try {
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());
			if (page > 0) {
				executableCriteria.setFirstResult((page - 1) * pageSize);
			}
			if (pageSize > 0) {
				executableCriteria.setMaxResults(pageSize);
			}
			tmpLst = executableCriteria.list();

			detachedCriteria.setProjection(Projections.rowCount());

			executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());
			if (page > 0) {
				executableCriteria.setFirstResult(0);
			}
			if (pageSize > 0) {
				executableCriteria.setMaxResults(0);
			}
			Long rwLst = (Long) executableCriteria.uniqueResult();

			pagingResult.setList(tmpLst);
			pagingResult.setRowsCount(rwLst);

		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return pagingResult;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public PagingResult<T> findCriteriaDinamicoPageResult(DetachedCriteria detachedCriteria, int pageSize, int page)
			throws BusinessExeption {
		PagingResult<T> pagingResult = new PagingResult<T>();
		List<T> tmpLst = new ArrayList<T>();
		try {
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());
			if (page > 0) {
				executableCriteria.setFirstResult((page - 1) * pageSize);
			}
			if (pageSize > 0) {
				executableCriteria.setMaxResults(pageSize);
			}
			tmpLst = executableCriteria.list();

			detachedCriteria.setProjection(Projections.rowCount());

			executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());
			if (page > 0) {
				executableCriteria.setFirstResult(0);
			}
			if (pageSize > 0) {
				executableCriteria.setMaxResults(0);
			}
			Long rwLst = (Long) executableCriteria.uniqueResult();

			pagingResult.setList(tmpLst);
			pagingResult.setRowsCount(rwLst);

		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return pagingResult;
	}

	/**
	 * {@inheritDoc}
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	@Override
	public List<T> findDetachedCriteriaFirst(DetachedCriteria detachedCriteria, int pageSize, int page)
			throws BusinessExeption {
		List<T> tmpLst = new ArrayList<T>();
		try {
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());
			if (page > 0) {
				executableCriteria.setFirstResult((page - 1) * pageSize);
			}
			if (pageSize > 0) {
				executableCriteria.setMaxResults(pageSize);
			}
			tmpLst = executableCriteria.list();

			detachedCriteria.setProjection(Projections.rowCount());

			executableCriteria = detachedCriteria.getExecutableCriteria(this.getSession());
			Long rwLst = (Long) executableCriteria.uniqueResult();

			List<T> lst = new ArrayList<T>(rwLst.intValue());
			int il = (page - 1) * pageSize;
			int iMin = rwLst.intValue() - pageSize;
			for (int i = 0; i < iMin; i++) {
				lst.add(null);
			}
			lst.addAll(il, tmpLst);
			return lst;
		} catch (Exception ex) {
			ex.printStackTrace();
			handleException(ex);
		}
		return new ArrayList<T>(0);
	}

	/**
	 * Apply the given name parameter to the given Query object.
	 * 
	 * @param queryObject
	 *            the Query object
	 * @param paramName
	 *            the name of the parameter
	 * @param value
	 *            the value of the parameter
	 * @throws HibernateException
	 *             if thrown by the Query object
	 */
	protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)
			throws HibernateException {

		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection<?>) value);
		} else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		} else {
			queryObject.setParameter(paramName, value);
		}
	}
}