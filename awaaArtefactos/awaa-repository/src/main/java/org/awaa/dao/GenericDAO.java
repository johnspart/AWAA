/**
 * 
 */
package org.awaa.dao;

/**
 * @author john
 *
 */

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.awwa.utils.exeptions.BusinessExeption;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.transform.ResultTransformer;

public interface GenericDAO<T, Key extends Serializable> {

	/**
	 * Elimina un elemento
	 * 
	 * @param clazz
	 * @param obj
	 * @throws BusinessExeption
	 */
	<Z> void delete(Class<Z> clazz, Z obj) throws BusinessExeption;

	/**
	 * Busca un elemento por su identificador (clave primaria)
	 * 
	 * @param clazz
	 *            representa la clase del elemento a buscar
	 * @param id
	 * @return elemento T
	 * @throws BusinessExeption
	 */
	T find(Class<T> clazz, Key id) throws BusinessExeption;

	/**
	 * Busca un elemento por su identificador (clave primaria) diferentes a las
	 * clases principal
	 * 
	 * @param clazz
	 * @param cKey
	 * @param id
	 * @return
	 * @throws BusinessExeption
	 */
	<Z, CKey extends Serializable> Z find(Class<Z> clazz, Class<CKey> cKey, CKey id) throws BusinessExeption;

	public Integer editHQL(final String hql, Map<String, Object> params) throws BusinessExeption;

	List<T> findCriteriaDinamico(DetachedCriteria detachedCriteria) throws BusinessExeption;

	/**
	 * Actualiza elementos deacuerdo al sql recibido
	 * 
	 * @param clazz
	 *            representa la clase del elemento a actualizar
	 * @param sql
	 *            condiciÃ³n a aplicar a la actualizaciÃ³n
	 * @return valor del cambio
	 * @throws BusinessExeption
	 */
	public Integer saveOrUpdateSql(Class<T> clazz, final String sql) throws BusinessExeption;

	/**
	 * Busca todos los elementos, aplicando la condiciÃ³n especificada en la
	 * variable <code>filter</code> en el order especificado <code>order</code>
	 * 
	 * @param clazz
	 *            representa la clase del elemento a buscar
	 * @param filter
	 *            condiciÃ³n a aplicar a la bÃºsqueda
	 * @return lista de elementos
	 * @throws BusinessExeption
	 */
	List<T> findAll(Class<T> clazz, String filter, String order) throws BusinessExeption;

	/**
	 * @param clazz
	 *            representa la clase del elemento a buscar
	 * @param params
	 *            mapa de <nombre parametro, valor> los cuales se aplican a la
	 *            busqueda
	 * @param orderProperty
	 *            nombre del o los atributo por el cual se debe ordenar el
	 *            resultado.
	 * @param desc
	 *            si es true el orden es descendente en caso de que el parametro
	 *            orderProperty no sea vacÃ­o. Si es false el orden es
	 *            ascendente
	 * @return lista de elementos
	 * 
	 * @throws BusinessExeption
	 */
	List<T> findParams(Class<T> clazz, Map<String, Object> params, String orderProperty, boolean desc)
			throws BusinessExeption;

	/**
	 * Busca todos los elementos que cumplan con las condiciones indicadas en el
	 * atributo <code>params</code>
	 * 
	 * @param clazz
	 *            representa la clase del elemento a buscar
	 * @param hql
	 *            estructura hql de la busqueda
	 * @param params
	 *            mapa de <nombre parametro, valor> los cuales se aplican a la
	 *            bÃºsqueda
	 * @return lista de elementos
	 * @throws BusinessExeption
	 */
	public List<T> findHql(Class<T> clazz, String hql, Map<String, Object> params) throws BusinessExeption;

	/**
	 * Busca los elementos aplicando el ejemplo recibido
	 * 
	 * @param ejemplo
	 * @return lista de elementos
	 * @throws BusinessExeption
	 */
	List<T> findAllByExample(T ejemplo) throws BusinessExeption;

	/**
	 * Busca el elemento que cumpla las condiciones del ejemplo (sea igual)
	 * 
	 * @param ejemplo
	 * @return el elemento
	 * @throws BusinessExeption
	 */
	T findByExample(T ejemplo) throws BusinessExeption;

	/**
	 * Realiza la actualización del elemento +
	 * 
	 * @param Z
	 * @param obj
	 * @throws BusinessExeption
	 */
	<Z> void update(Class<Z> Z, Z obj) throws BusinessExeption;

	/**
	 * Realiza la ejecución de la NamedQuery especificada con el
	 * <code>queryName</code>
	 * 
	 * @param queryName
	 *            nombre de la consulta a ejecutar
	 * @param nombreParametros
	 * @param valoresParametros
	 * @return lista de elementos
	 * @throws BusinessExeption
	 */
	List<T> executeNameQuery(String queryName, String[] nombreParametros, Object[] valoresParametros)
			throws BusinessExeption;

	/**
	 * 
	 * @param obj
	 * @return
	 * @throws BusinessExeption
	 */
	T merge(T obj) throws BusinessExeption;

	/**
	 * Busca todos los elementos que cumplan con las condiciones indicadas en el
	 * atributo <code>params</code> y transforma la salida a una clase que no es
	 * de tipo entidad
	 * 
	 * @param <Z>
	 * 
	 * @param clazz
	 *            representa la clase del elemento a buscar
	 * @param sql
	 *            estructura sql de la busqueda
	 * @param params
	 *            mapa de <nombre parÃ¡metro, valor> los cuales se aplican a la
	 *            búsqueda
	 * @return lista de elementos
	 * @throws BusinessExeption
	 */
	<Z> List<Z> findSqlBean(Class<Z> clazz, String sql, Map<String, Object> params) throws BusinessExeption;

	/**
	 * Realiza consulta limitada por los parametros recibidos
	 * 
	 * @param clazz
	 *            clase que especifica el tipo de elementos a devolver
	 * @param detachedCriteria
	 *            manejador de la consulta
	 * @param pageSize
	 *            tamaño de la lista a devolver y a buscar
	 * @param page
	 *            pagina actual
	 * @return Bean que contiene el numero de elemenstos totales y la lista de
	 *         elementos devueltos en la consulta
	 * @throws BusinessExeption
	 */
	public <Z> PagingResult<Z> findCriteriaDinamicoPageResult(Class<Z> clazz, DetachedCriteria detachedCriteria,
			int pageSize, int page) throws BusinessExeption;

	/**
	 * Realiza consulta limitada por los parametros recibidos
	 * 
	 * @param detachedCriteria
	 *            manejador de la consulta
	 * @param limit
	 *            tamaño de la lista a devolver y a buscar
	 * @return Lista de elementos devueltos en la consulta
	 * @throws BusinessExeption
	 */
	List<T> findCriteriaDinamico(DetachedCriteria detachedCriteria, int limit) throws BusinessExeption;


	boolean findCriteriaDinamicoExist(DetachedCriteria detachedCriteria) throws BusinessExeption;

	/**
	 * Guarda el elemento.
	 * 
	 * @param obj
	 * @return Z
	 * @throws BusinessExeption
	 */
	<Z> Z save(Class<Z> clazz, Z obj) throws BusinessExeption;

	<Z> List<Z> findCriteriaDinamico(Class<Z> clazz, DetachedCriteria detachedCriteria, ResultTransformer transformers,
			Projection projections) throws BusinessExeption;

	<Z> Z findCriteriaDinamicouniqueResult(Class<Z> Z, DetachedCriteria detachedCriteria,
			ResultTransformer resultTransformer, Projection projection) throws BusinessExeption;

	<Z> void saveOrUpdate(Class<Z> clazz, Z obj) throws BusinessExeption;

	<Z> void saveOrUpdateAll(Class<Z> clazz, List<Z> listaElementos) throws BusinessExeption;
}