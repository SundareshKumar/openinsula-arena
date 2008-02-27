package org.openinsula.arena.echo2.component.model.hibernate;

import org.openinsula.arena.echo2.component.model.PageableTableModel;
import org.openinsula.vulcano.orm.dao.query.DaoQuery;

public interface HibernateTableModel<T> extends PageableTableModel {

	public T getValue(int index);

	public void setDaoQuery(DaoQuery query);

}
