package com.wedevol.iclass.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Department Composed Id
 *
 * @author charz
 */
@Embeddable
public class DepartmentId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "universityid")
	private Long universityId;

	@NotNull
	@Column(name = "facultyid")
	private Long facultyId;

	protected DepartmentId() {
	}

	public DepartmentId(Long universityId, Long facultyId) {
		super();
		this.universityId = universityId;
		this.facultyId = facultyId;
	}

	public Long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Long universityId) {
		this.universityId = universityId;
	}

	public Long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}

	@Override
	public String toString() {
		return String.format("DepartmentId[universityId=%d, facultyId=%d]%n", universityId, facultyId);
	}
	
	@Override
	public int hashCode() {
		int hashed = 1;
		if (universityId != null) {
			hashed = hashed * 31 + universityId.hashCode();
		}
		if (facultyId != null) {
			hashed = hashed * 31 + facultyId.hashCode();
		}
		return hashed;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		if (obj == this)
			return true;
		DepartmentId other = (DepartmentId) obj;
		return this.hashCode() == other.hashCode();
	}

}