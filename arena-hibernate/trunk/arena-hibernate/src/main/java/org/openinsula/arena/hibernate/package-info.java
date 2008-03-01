@TypeDefs( {		
	@TypeDef(
			name = MoneyTypes.DEFAULT,
			typeClass = MoneyUserType.class
	),
	
	@TypeDef(
			name = MoneyTypes.BRAZIL,
			typeClass = MoneyUserType.class,
			parameters = {
				@Parameter(name=MoneyUserType.PARAM_LANGUAGE, value="pt"),
				@Parameter(name=MoneyUserType.PARAM_COUNTRY, value="BR")
			}
	),
	
	@TypeDef(
			name = MoneyTypes.US,
			typeClass = MoneyUserType.class,
			parameters = {
				@Parameter(name=MoneyUserType.PARAM_LANGUAGE, value="en"),
				@Parameter(name=MoneyUserType.PARAM_COUNTRY, value="US")
			}
	)
})

package org.openinsula.arena.hibernate;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.openinsula.arena.hibernate.types.MoneyTypes;
import org.openinsula.arena.hibernate.types.MoneyUserType;

