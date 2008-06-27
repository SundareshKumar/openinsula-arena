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
@TypeDefs( {
		@TypeDef(name = UFStandardEnumType.TYPE, typeClass = AnnotatedEnumUserType.class,
				parameters = {
					@Parameter(name = AnnotatedEnumUserType.PARAM_ENUM_CLASS, value = "org.openinsula.arena.hibernate.types.UFStandardEnumType")
				}
		),
		@TypeDef(name = UFStringEnumType.TYPE, typeClass = AnnotatedEnumUserType.class,
				parameters = {
					@Parameter(name = AnnotatedEnumUserType.PARAM_ENUM_CLASS, value = "org.openinsula.arena.hibernate.types.UFStringEnumType")
				}
		),
		@TypeDef(name = UFIntEnumType.TYPE, typeClass = AnnotatedEnumUserType.class,
				parameters = {
					@Parameter(name = AnnotatedEnumUserType.PARAM_ENUM_CLASS, value = "org.openinsula.arena.hibernate.types.UFIntEnumType")
				}
		),
})
package org.openinsula.arena.hibernate.types;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;








