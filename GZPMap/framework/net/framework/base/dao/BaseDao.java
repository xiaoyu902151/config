package net.framework.base.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.framework.exception.BusinessException;
import net.framework.httpModel.PageResults;
import net.framework.httpModel.page.Page;
import net.framework.utils.StringUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springside.modules.utils.reflection.ReflectionUtils;

import com.google.common.collect.Lists;


/**
 * 封装通用的dao
 * @author ldw
 *
 * @param <T>
 * @param <ID>
 */
@Repository("baseDao")
public class BaseDao<T, ID extends Serializable> implements IBaseDao<T, ID>{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected Class<T> entityClass;
	protected Class<ID> idClass;
	
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	/**
	 * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
	 * SimpleHibernateDao<User, Long>
	 */
	public BaseDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}
	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用SimpleHibernateDao的构造函数. 在构造函数中定义对象类型Class.
	 * eg. SimpleHibernateDao<User, Long> userDao = new SimpleHibernateDao<User,
	 * Long>(sessionFactory, User.class);
	 */
	
	/**
	 * 
	 * @Title: getCriteria 
	 * @Description: 获取Criteria 对象,方便子类获取
	 * @param @return 
	 * @return Criteria 
	 * @throws
	 */
	
	protected Criteria getCriteria(){
		return this.getSession().createCriteria(entityClass);
	}
	
	/**
	 * 
	 * @Title: getCriteria 
	 * @Description: 让Criteria具有分页功能
	 * @param @param page
	 * @param @return 
	 * @return Criteria 
	 * @throws
	 */
	protected Criteria getCriteria(Page page){
		Criteria criteria= this.getCriteria();
		criteria.setFirstResult((page.getPage() - 1) * page.getRows()).setMaxResults(page.getRows());
		return criteria;
	}
	
	/**
	 * 
	 * @Title: getCriteriaExtendPage 
	 * @Description: 扩展criteria分页功能
	 * @param @param criteria 不具备分页能力的criteria
	 * @param @param page
	 * @param @return 
	 * @return Criteria 
	 * @throws
	 */
	protected void getCriteriaExtendPage(Criteria criteria,Page page){
		 criteria.setFirstResult((page.getPage() - 1) * page.getRows()).setMaxResults(page.getRows());
	}
	
	/**
	 * 
	 * @Title: getQuery 
	 * @Description: 获取Query对象,方便子类获取
	 * @param @param hql
	 * @param @return 
	 * @return Query 
	 * @throws
	 */
	protected Query getQuery(String hql){
		return this.getSession().createQuery(hql);
	}
	
	
//	public BaseDao(final SessionFactory sessionFactory,
//			final Class<T> entityClass) {
//		super.setSessionFactory(sessionFactory);
//		this.entityClass = entityClass;
//	}
//	/**
//	 * 采用@Autowired按类型注入SessionFactory, 当有多个SesionFactory的时候在子类重载本函数.
//	 */
//	@Autowired
//	public void init(final SessionFactory sessionFactory) {
//		super.setSessionFactory(sessionFactory);
//	}
	
	
	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	/**
	 * 采用@Autowired按类型注入SessionFactory
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * 获取session
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 
	 * @Title: evict 
	 * @Description: 让持久化对象脱离hibernate管理
	 * @param @param entity 
	 * @return void 
	 * @throws
	 */
	public void evict(T entity){
		this.getSession().evict(entity);
	}
	
	/**
	 * 保存对象
	 * @return 
	 */
	public Serializable save(T t) {
		Assert.notNull(t, "entity不能为空");
		logger.debug("save entity: {}", t);
		return sessionFactory.getCurrentSession().save(t);
	}
	
	/**
	 * 保存或者更新对象
	 */
	public void saveOrUpdate(T t) {
		Assert.notNull(t, "entity不能为空");
		getSession().saveOrUpdate(t);
		logger.debug("saveOrUpdate entity: {}", t);
	}
	/**
	 * 查询对象，先缓存后数据库
	 */
	public T load(ID id) {
		Assert.notNull(id, "id不能为空");
		return (T)getSession().load(entityClass, id);
	}
	/**
	 * 查询对象，直接查询数据库
	 */
	public T get(ID id) {
		Assert.notNull(id, "id不能为空");
		return (T)getSession().get(entityClass, id);
	}
	/**
	 * 查询对象是否存在
	 */
	public boolean contains(T t) {
		Assert.notNull(t, "entity不能为空");
		return getSession().contains(t);
	}
	/**
	 * 删除对象
	 */
	public void delete(T t) {
		Assert.notNull(t, "entity不能为空");
		getSession().delete(t);
		logger.debug("delete entity: {}", t);
	}
	/**
	 * 根据ID删除对象
	 */
	public void deleteById(ID id) {
		Assert.notNull(id, "id不能为空");
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}
	/**
	 * 删除对象数组
	 */
	public void deleteAll(Collection<T> entities) {
		if(entities.size() > 0) {
			for(T object : entities) {
				delete(object);
			}
		}
	}
	/**
	 * 刷新对象
	 */
	public void refresh(T t) {
		Assert.notNull(t, "entity不能为空");
		getSession().refresh(t);
		logger.debug("refresh entity: {}", t);
	}
	/**
	 * 更新对象
	 */
	public void update(T t) {
		Assert.notNull(t, "entity不能为空");
		getSession().update(t);
		logger.debug("update entity: {}", t);
	}
	/**
	 * 根据条件查询数据量
	 */
	public Long countByArray(String hql, Object... param) {
		Query q = getQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}
	/**
	 * 根据条件查询数据量
	 */
	public Long countByList(String hql, List<Object> param) {
		Query q = getQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}
	
	/**
	 * 按对像查询数据
	 * 
	 * @param object
	 * @return
	 */
	public List<T> findByExample(T object) {
		Criteria c = getCriteria();
		Example example = Example.create(object);
		example.enableLike(MatchMode.ANYWHERE);// 匹配模式,使用模糊查询必填项。
		example.excludeNone();// 空的不做查询条件
		example.excludeZeroes();// 0不要查询
		c.add(example);
		List<T> list = c.list();
		return list;
	}
	
	public void addExampleForCriteria(Criteria criteria,T object){
		Example example = Example.create(object);
		example.enableLike(MatchMode.ANYWHERE);// 匹配模式,使用模糊查询必填项。
		example.excludeNone();// 空的不做查询条件
		example.excludeZeroes();// 0不要查询
		criteria.add(example);
	}
	
	
	
	/**
	 * 按对像查找唯一的数据
	 * 
	 * @param object
	 * @return
	 */
	public T findUniqueByExample(T object) {
		List<T> rst = findByExample(object);
		if (rst != null && !rst.isEmpty()) {
			return rst.get(0);
		}
		return null;
	}
	
	/**
	 * 通过HQL查询对象数组
	 */
	public List<T> find(String hql, Object... param) {
		Query q = getQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}
	/**
	 * 通过HQL查询对象数组
	 */
	public List<T> find(String hql, List<Object> param) {
		Query q = getQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}
	/**
	 * 查询分页对象
	 */
	public List<T> findByPageList(String hql, int page, int rows, List<Object> param) {
		Query q = getQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	/**
	 * 查询分页对象
	 */
	public List<T> findByPageArray(String hql, int page, int rows, Object... param) {
		Assert.notNull(hql, "hql不能为空");
		Query q = getQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	/**
	 * 合并对象
	 */
	public void merge(T t) {
		Assert.notNull(t, "entity不能为空");
		getSession().merge(t);
		logger.debug("merge entity: {}", t);
	}
	
	
	/**
	 *  根据条件获取对象
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, Object... param) {
		Assert.notNull(hql, "hql不能为空");
		List l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return (T) l.get(0);
		}
		return null;
	}
	/**
	 *  根据条件获取对象
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param) {
		Assert.notNull(hql, "hql不能为空");
		List l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return (T) l.get(0);
		}
		return null;
	}
	/**
	 * 获取对象
	 * @param c
	 * @param id
	 * @return
	 */
	public T get(Class<T> c, Serializable id) {
		return (T) getSession().get(c, id);
	}
	/**
	 * 获取对象
	 * @param c
	 * @param id
	 * @return
	 */
	public T load(Class<T> c, Serializable id) {
		return (T) getSession().load(c, id);
	}
	
	/**
	 * 
	 * Title: getObjListForPage
	 * Description: 主要用于easyui datagrid 查询
	 * Created On: 2015-3-16 上午9:58:30
	 * @author ldw 
	 * @param page
	 * @param t
	 * @return
	 */
	public PageResults<T> getObjListForPage(PageResults<T> page, T t) {
		Criteria criteria = this.getCriteria();
		Example example = Example.create(t);
		example.enableLike(MatchMode.ANYWHERE);// 匹配模式,使用模糊查询必填项。
		example.excludeNone();// 空的不做查询条件
		example.excludeZeroes();// 0不要查询
		example.ignoreCase(); // 不区分大小写
		criteria.add(example);
		
		Long totalCount =(Long) criteria.setProjection(Projections.rowCount()).uniqueResult(); 
		criteria.setProjection(null);
		List list = null;
		if (!StringUtil.trimToEmpty(page.getOrder()).equals("") && !StringUtil.trimToEmpty(page.getOrderBy()).equals(""))
			if(PageResults.ASC.equals(page.getOrder()))
				list = criteria.addOrder(Order.asc(page.getOrderBy())).setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
			else
				list = criteria.addOrder(Order.desc(page.getOrderBy())).setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
		else
			list = criteria.setFirstResult((page.getPageNo() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).list();
        page.setTotalCount(totalCount);
        page.setResult(list);
		return page;
	}
	
	public Criteria createCriteria(T t) {
		Criteria criteria = this.getCriteria();
		Example example = Example.create(t);
		example.enableLike(MatchMode.ANYWHERE);// 匹配模式,使用模糊查询必填项。
		example.excludeNone();// 空的不做查询条件
		example.excludeZeroes();// 0不要查询
		example.ignoreCase(); // 不区分大小写
		criteria.add(example);
		return criteria;
	}

	/**
	 * 获取所有数据表
	 * 
	 * @return
	 */
	public Integer getAllDbTableSize() {
		SessionFactory factory = getSession().getSessionFactory();
		Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
		return metaMap.size();
	}

	/**
	 * 根据实体名字获取唯一记录
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 按属性查找对象列表.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (List<T>) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 批量保存数据
	 * 
	 * @param <T>
	 * @param entitys
	 *            要持久化的临时实体对象集合
	 */
	public <T> void batchSave(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			getSession().save(entitys.get(i));
			if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}
		}
		// 最后清理一下----防止大于20小于40的不保存
		getSession().flush();
		getSession().clear();
	}

	/**
	 * 根据主键删除指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		delete(get(entityName, id));
		getSession().flush();
	}

	/**
	 * 删除全部的实体
	 * 
	 * @param <T>
	 * 
	 * @param entitys
	 */
	public <T> void deleteAllEntitie(Collection<T> entitys) {
		for (Object entity : entitys) {
			getSession().delete(entity);
			getSession().flush();
		}
	}

	/**
	 * 根据主键获取实体并加锁。
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @param lock
	 * @return
	 */
	public <T> T getEntity(Class entityName, Serializable id) {

		T t = (T) getSession().get(entityName, id);
		if (t != null) {
			getSession().flush();
		}
		return t;
	}

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo) {
		getSession().update(pojo);
		getSession().flush();
	}

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(String className, Object id) {
		getSession().update(className, id);
		getSession().flush();
	}

	/**
	 * 根据主键更新实体
	 */
	public <T> void updateEntityById(Class entityName, Serializable id) {
		updateEntitie(get(entityName, id));
	}

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public List<T> findByQueryString(final String query) {

		Query queryObject = getSession().createQuery(query);
		List<T> list = queryObject.list();
		if (list.size() > 0) {
			getSession().flush();
		}
		return list;

	}

	/**
	 * 通过hql查询唯一对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> T singleResult(String hql) {
		T t = null;
		Query queryObject = getSession().createQuery(hql);
		List<T> list = queryObject.list();
		if (list.size() == 1) {
			getSession().flush();
			t = list.get(0);
		} else if (list.size() > 0) {
			throw new BusinessException("查询结果数:" + list.size() + "大于1");
		}
		return t;
	}

	/**
	 * 通过hql 查询语句查找HashMap对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public Map<Object, Object> getHashMapbyQuery(String hql) {

		Query query = getSession().createQuery(hql);
		List list = query.list();
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			map.put(tm[0].toString(), tm[1].toString());
		}
		return map;

	}
	
	public List getListMapbyQuery(String hql){
		Query query = getSession().createQuery(hql);
		List list = query.list();
		return list;
	}

	/**
	 * 通过sql更新记录
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public int updateBySqlString(final String query) {

		Query querys = getSession().createSQLQuery(query);
		return querys.executeUpdate();
	}

	/**
	 * 通过sql查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public List<T> findListbySql(final String sql) {
		Query querys = getSession().createSQLQuery(sql).addEntity(entityClass);
		return querys.list();
	}

	/**
	 * 创建Criteria对象，有排序功能。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass, boolean isAsc,
			Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc("asc"));
		} else {
			criteria.addOrder(Order.desc("desc"));
		}
		return criteria;
	}

	/**
	 * 创建Criteria对象带属性比较
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	protected <T> Criteria createCriteria(Class<T> entityClass,
			Criterion... criterions) {
		Criteria criteria = getCriteria();
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public <T> List<T> loadAll(final Class<T> entityClass) {
		Criteria criteria = createCriteria(entityClass);
		return criteria.list();
	}

	/**
	 * 创建单一Criteria对象
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass) {
		Criteria criteria = getCriteria();
		return criteria;
	}

	/**
	 * 根据属性名和属性值查询. 有排序
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {
		Assert.hasText(propertyName);
		return createCriteria(entityClass, isAsc,
				Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据属性名和属性值 查询 且要求对象唯一.
	 * 
	 * @return 符合条件的唯一对象.
	 */
	public <T> T findUniqueBy(Class<T> entityClass, String propertyName,
			Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 根据查询条件与参数列表创建Query对象
	 * 
	 * @param session
	 *            Hibernate会话
	 * @param hql
	 *            HQL语句
	 * @param objects
	 *            参数列表
	 * @return Query对象
	 */
	public Query createQuery(Session session, String hql, Object... objects) {
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query;
	}

	/**
	 * 批量插入实体
	 * 
	 * @param clas
	 * @param values
	 * @return
	 */
	public int batchInsertsEntitie(List<T> entityList) {
		int num = 0;
		for (int i = 0; i < entityList.size(); i++) {
			//save(entityList.get(i));
			T t = entityList.get(i);
			save(t);
			num++;
		}
		return num;
	}

	/**
	 * 根据实体名返回全部对象
	 * 
	 * @param <T>
	 * @param hql
	 * @param size
	 * @return
	 */
	/**
	 * 使用占位符的方式填充值 请注意：like对应的值格式："%"+username+"%" Hibernate Query
	 * 
	 * @param hibernateTemplate
	 * @param hql
	 * @param valus
	 *            占位符号?对应的值，顺序必须一一对应 可以为空对象数组，但是不能为null
	 * @return 2008-07-19 add by liuyang
	 */
	public List<T> executeQuery(final String hql, final Object[] values) {
		Query query = getSession().createQuery(hql);
		// query.setCacheable(true);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}

		return query.list();

	}

	/**
	 * 根据实体模版查找
	 * 
	 * @param entityName
	 * @param exampleEntity
	 * @return
	 */

	public List findByExample(final String entityName,
			final Object exampleEntity) {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		Criteria executableCriteria = (entityName != null ? getSession()
				.createCriteria(entityName) : getSession().createCriteria(
				exampleEntity.getClass()));
		executableCriteria.add(Example.create(exampleEntity));
		return executableCriteria.list();
	}



	/**
	 * 调用存储过程
	 */
	public void callableStatementByName(String proc) {
	}

	/**
	 * 查询指定实体的总记录数
	 * 
	 * @param clazz
	 * @return
	 */
	public int getCount(Class<T> clazz) {

		int count = DataAccessUtils.intResult(getSession().createQuery(
				"select count(*) from " + clazz.getName()).list());
		return count;
	}

	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC
	 */
	public Long getCountForJdbc(String sql) {
		T t = this.jdbcTemplate.queryForObject(sql, entityClass);
		return (Long)t;
		//return this.jdbcTemplate.queryForLong(sql);
	}

	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 * 
	 */
	public Long getCountForJdbcParam(String sql, Object[] objs) {
		T t = this.jdbcTemplate.queryForObject(sql, objs, entityClass);
		//return this.jdbcTemplate.queryForLong(sql, objs);
		return (Long)t;
	}

	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		return this.jdbcTemplate.queryForList(sql, objs);
	}

	public Integer executeSql(String sql, List<Object> param) {
		return this.jdbcTemplate.update(sql, param);
	}

	public Integer executeSql(String sql, Object... param) {
		return this.jdbcTemplate.update(sql, param);
	}

	public Integer executeSql(String sql, Map<String, Object> param) {
		return this.namedParameterJdbcTemplate.update(sql, param);
	}
	public Object executeSqlReturnKey(final String sql, Map<String, Object> param) {
		Object keyValue = null;
		try{
			KeyHolder keyHolder = new GeneratedKeyHolder(); 
			SqlParameterSource sqlp  = new MapSqlParameterSource(param);
			this.namedParameterJdbcTemplate.update(sql,sqlp, keyHolder);
			keyValue = keyHolder.getKey().longValue();
		}catch (Exception e) {
			keyValue = null;
		}
		return keyValue;
	}
	public Integer countByJdbc(String sql, Object... param) {
		T t = this.jdbcTemplate.queryForObject(sql, entityClass, param);
		return (Integer)t;
		//return this.jdbcTemplate.queryForInt(sql, param);
	}

	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		try {
			return this.jdbcTemplate.queryForMap(sql, objs);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findHql(String hql, Object... param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	/**
	 * 执行HQL语句操作更新
	 * 
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql) {
		Query q = getSession().createQuery(hql);
		return q.executeUpdate();
	}

	public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
			int maxResult) {
		Criteria criteria = dc.getExecutableCriteria(getSession());
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		return criteria.list();
	}

	/**
	 * 离线查询
	 */
	public <T> List<T> findByDetached(DetachedCriteria dc) {
		return dc.getExecutableCriteria(getSession()).list();
	}
	
	/**
	 * 
	 * @Title: findAll 
	 * @Description: 列表查询
	 * @param @return 
	 * @return List<T> 
	 * @throws
	 */
	public List<T> findAll(){
		StringBuilder hql=new StringBuilder();
		hql.append("FROM ").append(entityClass.getName());
		return this.find(hql.toString());
	}
	
	/**
	 * 
	 * @Title: findByExample 
	 * @Description: 按标准对象查询
	 * @param @param object
	 * @param @return 
	 * @return List<T> 
	 * @throws
	 */
	public List<T> findByEntity(T entity) {
		Criteria c = this.getCriteria();
		Example example = Example.create(entity);
		example.enableLike(MatchMode.EXACT);//精确匹配
		c.add(example);
		return c.list();
	}
	
	/**
	 * 
	 * @Title: getUnique 
	 * @Description: 按对像查找唯一的数据
	 * @param @param entity
	 * @param @return 
	 * @return T 
	 * @throws
	 */
	public T getUnique(T entity){
		List<T> list=this.findByEntity(entity);
		if(list!=null && !list.isEmpty()){
			if(list.size()>1){
				logger.warn("按对像查找唯一的数据,找到不止1个结果");
				//throw new RuntimeException(this.getClass().getName()+"getUnique(),按对像查找唯一的数据,找到不止1个结果");
			}
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: findByIds 
	 * @Description: 根据ids查询多个对象
	 * @param @param ids
	 * @param @return 
	 * @return List<T> 
	 * @throws
	 */
	public List<T> findByIds(ID[] ids) {
		if (ids != null) {
			List<T> list = Lists.newArrayList();
			for (ID id : ids) {
				T t = this.get(id);
				list.add(t);
			}
			return list;
		}
		return null;
	}
	
	public List<T> findByIds(Set<ID> ids) {
		
		if (ids != null) {
			List<T> list = Lists.newArrayList();
			for (ID id : ids) {
				T t = this.get(id);
				list.add(t);
			}
			return list;
		}
		return null;
	}
	
	
	
	public Long countByCriteria(Criteria criteria){
		Long count=(Long)criteria.setProjection(Projections.rowCount()).uniqueResult(); 
		criteria.setProjection(null);
		return count;
	}
	
	/**
	 * 
	 * Title: saveForSerializable
	 * Description: 保存数据并返回主键
	 * Created On: 2015-6-16 上午9:22:06
	 * @author ldw 
	 * @param t
	 * @return
	 */
	public Serializable saveForSerializable(T t) {
		return this.getSession().save(t);
	}
	
	/**
	 * 
	 * Title: getCountByLikeDate
	 * Description: 查询日期数
	 * Created On: 2015-7-8 下午3:59:33
	 * @author ldw 
	 * @param map
	 * @return
	 */
	public Long getCountByLikeDate(Map<String, Object> map) {
		Criteria criteria = this.getCriteria();
		Iterator<String> itr = map.keySet().iterator();
		while(itr.hasNext()) {
			String key = itr.next();
			Object value = map.get(key);
			criteria.add(Restrictions.like(key, value));
		}
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
								
	/*
	 * 查找小于半径r的数据集
	 * minX:最小x轴值
	 * minY：最小Y轴的值
	 * maxX：最大X轴值
	 * maxY：最大Y轴值
	 * temp：过滤的数据集
	 */
	public List<T> pack(double minX, double minY,
			double maxX, double maxY, double r, List<T> temp) throws Exception {
		List<T> reDan = new ArrayList<T>();
		 		
		 //圆心坐标		
		double x0 = ((maxX - minX) / 2 + minX);
		double y0 = ((maxY - minY) / 2 + minY);
		 		 
		 for(T real : temp){
			Field x = real.getClass().getDeclaredField("x");
			x.setAccessible(true);
			 Field y = real.getClass().getDeclaredField("y");
			 y.setAccessible(true);
			 
			 String xValue = (String)x.get(real);
			 String yValue = (String)y.get(real);
			 
			 double x1 = 0;
			 double y1 =0;
			 
			if(null != xValue &&  null != yValue){
				
				//图上坐标
				 x1 = Double.parseDouble(xValue);
				 y1 = Double.parseDouble(yValue);
				 
			}
			 			 					 
			double powX = StrictMath.pow((x1 - x0),2);
			double powY = StrictMath.pow((y1 - y0),2);			 		 
			 if(r > StrictMath.pow((powX + powY), 1.0/2)){
				 
				 reDan.add(real);
			 }
			
	 
		 }
		return reDan;
	}
	
	
}


