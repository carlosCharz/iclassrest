package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Material;

/**
 * Material Service Interface
 * 
 * @author charz
 *
 */
public interface MaterialService extends BaseService<Material>  {

	List<Material> findAll();
	
	Material findByName(String name);
	
	List<Material> findMaterialsByCourseId(Long courseId);

}
