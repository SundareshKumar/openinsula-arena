package org.openinsula.arena.io.textfile.line;

import org.openinsula.arena.io.textfile.field.Field;

public interface LineFactory {
	public void addField(Field<?> field);

	public Line createLine();

	public boolean matches(String s);

	public Line parseLine(String s);

	public int getTotalSize();
}
