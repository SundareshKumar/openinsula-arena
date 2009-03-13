package org.openinsula.arena.gwt.rebind;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public abstract class AbstractGenerator extends Generator {
	protected CommonsLog logger;

	protected GeneratorContext context;

	protected String typeName;

	private TypeOracle typeOracle;

	private JClassType requestedClass;

	@Override
	public String generate(final TreeLogger logger, final GeneratorContext context, final String typeName)
			throws UnableToCompleteException {

		this.logger = new CommonsLog(logger);
		this.context = context;
		this.typeName = typeName;
		
		try {
			return doGenerate();
		} catch (Exception e) {
			this.logger.error(e, null, "Error in doGenerate()");
			throw new UnableToCompleteException();
		}
	}

	protected abstract String doGenerate() throws Exception;

	protected TypeOracle getTypeOracle() {
		return getTypeOracle(true);
	}

	protected TypeOracle getTypeOracle(final boolean required) {
		if (typeOracle == null) {
			typeOracle = context.getTypeOracle();

			if (required) {
				assert (typeOracle != null);
			}
		}
		return typeOracle;
	}

	protected JClassType getRequestedClass() throws UnableToCompleteException {
		if (requestedClass == null) {
			requestedClass = getTypeOracle(true).findType(typeName);

			if (requestedClass == null) {
				logger.error("Unable to find metadata for type '%s'", typeName);
				assert requestedClass != null;
			}
		}
		return requestedClass;
	}
	
}
