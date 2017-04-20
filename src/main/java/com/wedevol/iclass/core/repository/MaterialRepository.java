package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Material;

/**
 * Material Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface MaterialRepository extends CrudRepository<Material, Long> {

	/**
	 * Return the material having the passed material name or null if no material is found.
	 * 
	 * @param materialName
	 * @return material
	 */
	public Material findByName(String name);

	/**
	 * Return all the material order by name asc
	 * 
	 * @return material list
	 */
	public List<Material> findAllByOrderByNameAsc();
	
	/**
	 * Return the materials of a course order by material name asc
	 * 
	 * @param courseId
	 * @return list of materials
	 */
	@Query("SELECT mat "
			+ "FROM Material mat "
			+ "WHERE mat.courseId = :courseId "
			+ "order by mat.name asc")
	public List<Material> findMaterialsWithCourseId(@Param("courseId") Long courseId);

}
