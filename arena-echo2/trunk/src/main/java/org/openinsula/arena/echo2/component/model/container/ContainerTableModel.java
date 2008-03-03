package org.openinsula.arena.echo2.component.model.container;

import java.io.Serializable;
import java.util.Collection;

import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.table.TableModel;

public interface ContainerTableModel<T> extends TableModel {
//	public void addColumn(String label, String beanField);
//
//	public void addColumn(String label, String beanField, int width);

//	public void setTableColumnModel(TableColumnModel tableColumnModel);

	public void clear();

	public void addItems(T ... beans);

	public void addItems(Collection<T> beans);

	public void setItems(Collection<T> beans);

	public void addItem(T bean);

	public T getSelectedItem(ActionEvent ae);
	
	public T getSelectedItem(Serializable itemId);

	public boolean deleteItem(T bean);
	
	public boolean deleteItem(Serializable itemId);
}
