/*
 *  (C) Copyright 2008 Insula Tecnologia da Informacao Ltda.
 * 
 *  This file is part of Arena-Hibernate.
 *
 *  Arena-Hibernate is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Arena-Hibernate is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Arena-Lang.  If not, see <http://www.gnu.org/licenses/>.
 */
@TypeDefs( {
		@TypeDef(name = ArenaTypes.MONEY, typeClass = MoneyUserType.class),

		@TypeDef(name = ArenaTypes.MONEY_BRAZIL, typeClass = MoneyUserType.class, parameters = {
				@Parameter(name = MoneyUserType.PARAM_LANGUAGE, value = "pt"),
				@Parameter(name = MoneyUserType.PARAM_COUNTRY, value = "BR") }),

		@TypeDef(name = ArenaTypes.MONEY_US, typeClass = MoneyUserType.class, parameters = {
				@Parameter(name = MoneyUserType.PARAM_LANGUAGE, value = "en"),
				@Parameter(name = MoneyUserType.PARAM_COUNTRY, value = "US") }),

		@TypeDef(name = ArenaTypes.DECIMAL, typeClass = DecimalUserType.class)

})
package org.openinsula.arena.hibernate;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.openinsula.arena.hibernate.types.ArenaTypes;
import org.openinsula.arena.hibernate.types.DecimalUserType;
import org.openinsula.arena.hibernate.types.MoneyUserType;


