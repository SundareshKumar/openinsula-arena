package org.openinsula.arena.io.textfile.line;

import java.math.BigDecimal;
import java.util.Date;

public interface Line {

	public void setValue(int index, Object value);

	public Object getValue(int index);

	public Date getDateValue(int index);

	public int getIntValue(int index);

	public long getLongValue(int index);

	public String getStringValue(int index);

	public BigDecimal getBigDecimalValue(int index);

	public BigDecimal getBigDecimalValue(int index, int scale);

	public LineFactory getLineFactory();

	public int getFieldsCount();

}
