package org.openinsula.arena.echo2.component.model.data;

import java.util.Collection;
import java.util.List;

import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.table.TableColumnModel;

public interface DataTableModel {
	public void addColumn(String label, String beanField);

	public void addColumn(String label, String beanField, int width);

	public void setTableColumnModel(TableColumnModel tableColumnModel);

	public void clear();

	public void setRows(Collection beans);

	public void addRow(Object bean);

	public long getSelectedBeanId(ActionEvent ae);

	public void deleteRow(Object bean);

	public void setDeleteColumn(boolean deleteColumn);

	public void setEditColumn(boolean editColumn);

	public void setDeleteButtonActionListener(ActionListener deleteButtonActionListener);

	public void setEditButtonActionListener(ActionListener editButtonActionListener);

	public List getBeanList();
}
