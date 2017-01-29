package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Department Entity
 * 
 * @author charz
 *
 */
@Entity
@Table(name = "department")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DepartmentId id;

	protected Department() {
	}

	public DepartmentId getId() {
		return id;
	}

	public void setId(DepartmentId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("Department[id=%d']%n", id);
	}
}
