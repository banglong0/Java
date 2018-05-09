package com.taotao.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;
import com.taotao.manage.pojo.ItemCat;

public class BaseService<T extends BasePojo> {

	// public abstract Mapper<T> mapper;

	@Autowired
	public Mapper<T> mapper;

	/*
	 * // 当前Service上泛型的字节码对象 private Class<T> clazz; { // 读取当前类上的泛型
	 * ParameterizedType type = (ParameterizedType)
	 * this.getClass().getGenericSuperclass(); clazz = (Class<T>)
	 * type.getActualTypeArguments()[0]; }
	 */

	/**
	 * 根据主键查询单个对象
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(Object id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据id查询多个对象
	 * 
	 * @param record
	 * @return
	 */
	public List<T> queryListByrecord(T record) {

		return this.mapper.select(record);
	}

	/**
	 * 查询全部
	 * 
	 * @return
	 */
	public List<T> queryAll() {
		return this.mapper.select(null);
	}

	/**
	 * 根据条件查询1条数据
	 * 
	 * @param record
	 * @return
	 */
	public T queryOne(T record) {
		return this.mapper.selectOne(record);
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param record
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<T> queryPageListByWhere( Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = this.queryAll();
		return new PageInfo<>(list);
	}

	/**
	 * 新增
	 * 
	 * @param record
	 * @return
	 */
	public Boolean save(T record) {
		record.setCreated(new Date());
		record.setUpdated(record.getCreated());// 更新时间和创建时间为同一时间
		return this.mapper.insertSelective(record) == 1;
	}

	/**
	 * 更新 注意:主键不能为空
	 * 
	 * @param record
	 * @return
	 */
	public Boolean updateById(T record) {
		return this.mapper.updateByPrimaryKeySelective(record) == 1;
	}

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 * @return
	 */
	public Boolean deleteById(Object id) {
		return this.mapper.deleteByPrimaryKey(id) == 1;
	}

	/**
	 * 根据集合删除多个对象
	 * 
	 * @param property
	 * @param ids
	 * @param clazz
	 * @return
	 */
	public Integer deleteByIds(String property, List<Object> ids, Class clazz) {

		Example example = new Example(clazz);
		Criteria criteria = example.createCriteria();
		criteria.andIn(property, ids);
		return this.mapper.deleteByExample(example);
	}

	/**
	 * 根据条件删除对象
	 * 
	 * @param record
	 * @return
	 */
	public Integer deleteByWhere(T record) {
		return this.mapper.delete(record);
	}

	public Mapper<ItemCat> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}
}
