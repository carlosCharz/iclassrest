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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
		result = prime * result + ((universityId == null) ? 0 : universityId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartmentId other = (DepartmentId) obj;
		if (facultyId == null) {
			if (other.facultyId != null)
				return false;
		} else if (!facultyId.equals(other.facultyId))
			return false;
		if (universityId == null) {
			if (other.universityId != null)
				return false;
		} else if (!universityId.equals(other.universityId))
			return false;
		return true;
	}

}