@TypeDefs( {		
	@TypeDef(
			name = ArenaTypes.MONEY,
			typeClass = MoneyUserType.class
	),
	
	@TypeDef(
			name = ArenaTypes.MONEY_BRAZIL,
			typeClass = MoneyUserType.class,
			parameters = {
				@Parameter(name=MoneyUserType.PARAM_LANGUAGE, value="pt"),
				@Parameter(name=MoneyUserType.PARAM_COUNTRY, value="BR")
			}
	),
	
	@TypeDef(
			name = ArenaTypes.MONEY_US,
			typeClass = MoneyUserType.class,
			parameters = {
				@Parameter(name=MoneyUserType.PARAM_LANGUAGE, value="en"),
				@Parameter(name=MoneyUserType.PARAM_COUNTRY, value="US")
			}
	),
	
	@TypeDef(
			name = ArenaTypes.DECIMAL,
			typeClass = DecimalUserType.class
	)
	
})

package org.openinsula.arena.hibernate;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.openinsula.arena.hibernate.types.ArenaTypes;
import org.openinsula.arena.hibernate.types.DecimalUserType;
import org.openinsula.arena.hibernate.types.MoneyUserType;


