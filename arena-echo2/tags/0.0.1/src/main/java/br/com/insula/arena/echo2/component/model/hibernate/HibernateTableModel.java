package br.com.insula.arena.echo2.component.model.hibernate;

import org.openinsula.vulcano.orm.dao.query.DaoQuery;

import br.com.insula.arena.echo2.component.model.PageableTableModel;



public interface HibernateTableModel<T> extends PageableTableModel {

	public T getValue(int index);
	
	public void setDaoQuery(DaoQuery query);
	
}
