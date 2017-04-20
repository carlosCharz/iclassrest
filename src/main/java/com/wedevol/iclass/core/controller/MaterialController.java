package com.wedevol.iclass.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.iclass.core.annotation.Authorize;
import com.wedevol.iclass.core.entity.Material;
import com.wedevol.iclass.core.service.MaterialService;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Material Controller
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("materials")
public class MaterialController {

	@Autowired
	private MaterialService materialService;

	@ApiIgnore
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Material> findAll() {
		return materialService.findAll();
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{materialId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Material findById(@PathVariable Long materialId) {
		return materialService.findById(materialId);
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Material create(@Valid @RequestBody Material material) {
		return materialService.create(material);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{materialId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long materialId, @Valid @RequestBody Material material) {
		materialService.update(materialId, material);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{materialId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long materialId) {
		materialService.delete(materialId);
	}

}
