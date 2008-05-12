package org.openinsula.arena.echo2.component.model.info;

import java.util.Collection;
import java.util.List;

import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.table.TableColumnModel;
import nextapp.echo2.app.table.TableModel;

import org.openinsula.arena.echo2.component.model.PageableTableModel;

public interface InfoTableModel<T> extends TableModel, PageableTableModel {
	public void addColumn(String label, String beanField);
	
	public void addColumn(String label, String beanField, int width);
	
	public void setTableColumnModel(TableColumnModel tableColumnModel);
	
	public void clear();
	
	public void setRows(Collection<T> beans);

	public void addRow(T bean);
	
	public void addAll(Collection<T> collection);
	
	public long getSelectedBeanId(ActionEvent ae);
	
	public void deleteRow(long beanId);
	
	public void deleteRow(T bean);
	
	public void setDeleteColumn(boolean deleteColumn);

	public void setEditColumn(boolean editColumn);

	public void setDeleteButtonActionListener(ActionListener deleteButtonActionListener);

	public void setEditButtonActionListener(ActionListener editButtonActionListener);
	
	public void setEditImage(ImageReference editImage);
	
	public void setDeleteImage(ImageReference deleteImage);
	
	public List<T> getBeanList();
	
}
