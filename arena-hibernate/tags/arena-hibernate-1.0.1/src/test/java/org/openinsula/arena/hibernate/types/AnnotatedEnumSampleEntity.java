package org.openinsula.arena.hibernate.types;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;
import org.openinsula.arena.hibernate.entity.BaseEntity;

@Entity
public class AnnotatedEnumSampleEntity extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Type(type = UFStandardEnumType.TYPE)
	public UFStandardEnumType ufStandard;

	@Type(type = UFStringEnumType.TYPE)
	@Column(length = 6)
	public UFStringEnumType ufString;

	@Type(type = UFIntEnumType.TYPE)
	public UFIntEnumType ufInt;

	public AnnotatedEnumSampleEntity() {
		super();
	}

	public AnnotatedEnumSampleEntity(final UFStandardEnumType ufStandard, final UFStringEnumType ufString, final UFIntEnumType ufInt) {
		this.ufStandard = ufStandard;
		this.ufString = ufString;
		this.ufInt = ufInt;
	}

}
