package com.wedevol.iclass.core.service;

import com.wedevol.iclass.core.util.DefaultInterface;

/**
 * Base Service Interface
 *
 * @author Charz++
 */
public interface BaseService<T> extends DefaultInterface {

	T findById(Long id);

	T create(T entity);

	void update(Long id, T entity);

	void delete(Long id);
}
