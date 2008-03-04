package org.openinsula.arena.echo2.component.model.container.impl;

import java.io.Serializable;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.event.ActionListener;

import org.openinsula.arena.echo2.component.model.container.impl.styles.ButtonContainerTableModelStyles;
import org.openinsula.arena.echo2.component.util.FormFactory;

/**
 * This table model have generic methods to build buttons to insert into the tableModel
 * @author realm
 *
 * @param <T>
 */
public class ButtonContainerTableModel<T> extends SortableContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

    private boolean editColumn = true;

    private boolean deleteColumn = true;

    private ActionListener editButtonActionListener;

    private ActionListener deleteButtonActionListener;
	
    private ButtonContainerTableModelStyles styles;

	@Override
	public int getColumnCount() {
		String[] columns = getColumns();
		
		int columnCount = columns.length;
		
		if (editColumn) {
			columnCount++;
		}
		
		if (deleteColumn) {
			columnCount++;
		}
		
		return columnCount; 
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex < getColumns().length) {
			return super.getColumnName(columnIndex);
		} else {
			return "";
		}
	}

	@Override
	public Object getColumnValue(int columnIndex, T t) {
		int columnsLength = getColumns().length;
		
		if (columnIndex < getColumns().length) {
			return super.getColumnValue(columnIndex, t);
		} else {
			
			if (editColumn
					&& columnIndex == (columnsLength)) {
				return buildEditButton(t);
			} else 
				if (deleteColumn) {
				return buildDeleteButton(t);
			}
		}
		
		return null;
	}
    
    /**
     * Builds the delete button
     * @param bean
     * @param beanId
     * @return
     */
    protected Button buildDeleteButton(T bean) {
    	Serializable beanId = findIdFromBean(bean);
    	if (logger.isDebugEnabled()) {
	    	logger.debug("Bean passed: "+bean.toString());
	    	logger.debug("Id found: "+beanId);
    	}
    	
    	Button button = null;
    	
    	if (styles != null) {
    		button = FormFactory.iconButton("Selecionar", styles.getEditImage());
    	} else {
    		button = FormFactory.button("Excluir");
    	}

    	if (deleteButtonActionListener != null) {
    		button.addActionListener(deleteButtonActionListener);
    	}
    	
    	if (beanId != null) {
    		button.setActionCommand(beanId.toString());
    	}

    	return button;
    }

    /**
     * Builds the edit button
     * @param bean
     * @return
     */
    protected Button buildEditButton(T bean) {
    	Serializable beanId = findIdFromBean(bean);
    	
    	Button button = null;
    	
    	if (styles != null) {
    		button = FormFactory.iconButton("Selecionar", styles.getEditImage());
    	} else {
    		button = FormFactory.button("Editar");
    	}
    	if (editButtonActionListener != null) {
    		button.addActionListener(editButtonActionListener);
    	}
    	
    	if (beanId != null) {
    		button.setActionCommand(beanId.toString());
    	}
    	
    	return button;
    }

	
	
	public boolean isEditColumn() {
		return editColumn;
	}

	public void setEditColumn(boolean editColumn) {
		this.editColumn = editColumn;
	}

	public boolean isDeleteColumn() {
		return deleteColumn;
	}

	public void setDeleteColumn(boolean deleteColumn) {
		this.deleteColumn = deleteColumn;
	}

	public ActionListener getEditButtonActionListener() {
		return editButtonActionListener;
	}

	public void setEditButtonActionListener(ActionListener editButtonActionListener) {
		this.editButtonActionListener = editButtonActionListener;
	}

	public ActionListener getDeleteButtonActionListener() {
		return deleteButtonActionListener;
	}

	public void setDeleteButtonActionListener(ActionListener deleteButtonActionListener) {
		this.deleteButtonActionListener = deleteButtonActionListener;
	}

	public ButtonContainerTableModelStyles getStyles() {
		return styles;
	}

	public void setStyles(ButtonContainerTableModelStyles styles) {
		this.styles = styles;
	}
	
}
