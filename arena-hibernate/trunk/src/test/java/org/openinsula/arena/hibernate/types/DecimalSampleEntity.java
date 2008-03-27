package org.openinsula.arena.hibernate.types;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;
import org.openinsula.arena.hibernate.entity.BaseEntity;
import org.openinsula.arena.lang.numbers.Decimal;

@Entity
public class DecimalSampleEntity extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Type(type = ArenaTypes.DECIMAL)
	@Column(precision = 4, scale = 3)
	Decimal decimal1_3;

	@Type(type = ArenaTypes.DECIMAL)
	@Column(precision = 4, scale = 2)
	Decimal decimal2_2;

	@Type(type = ArenaTypes.DECIMAL)
	@Column(precision = 4, scale = 0)
	Decimal decimal4_0;

}
