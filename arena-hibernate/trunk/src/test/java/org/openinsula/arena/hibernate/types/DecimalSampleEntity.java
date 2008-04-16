/*
 *  (C) Copyright 2008 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena Hibernate.
 *
 *  Arena Hibernate is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena Hibernate is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena Hibernate.  If not, see <http://www.gnu.org/licenses/>.
 */
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
