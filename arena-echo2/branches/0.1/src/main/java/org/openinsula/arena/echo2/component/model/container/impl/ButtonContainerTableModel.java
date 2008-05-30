package org.openinsula.arena.echo2.component.model.container.impl;

import java.io.Serializable;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.event.ActionListener;

import org.openinsula.arena.echo2.component.model.container.impl.styles.ButtonContainerTableModelStyles;
import org.openinsula.arena.echo2.component.util.FormFactory;

/**
 * This table model have generic methods to build buttons to insert into the
 * tableModel
 * 
 * It is strictly recommended to use buttons without the option permitDuplicates
 * set to false, because the comparison made to get the bean id, will get the
 * first found. If there is a duplicate, the buttons will delete the same first
 * bean found.
 * 
 * @author Joao Galli
 * 
 * @param <T>
 */
public class ButtonContainerTableModel<T> extends SortableContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

	private boolean editColumn = false;

	private boolean deleteColumn = false;

	private ActionListener editButtonActionListener;

	private ActionListener deleteButtonActionListener;

	private ButtonContainerTableModelStyles styles;

	public ButtonContainerTableModel() {
		super();
	}

	public ButtonContainerTableModel(boolean permitDuplicates, boolean substituteDuplicate) {
		super(permitDuplicates, substituteDuplicate);
	}

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
		}
		else {
			return "";
		}
	}

	@Override
	public Object getColumnValue(int columnIndex, T t) {
		int columnsLength = getColumns().length;

		if (columnIndex < getColumns().length) {
			return super.getColumnValue(columnIndex, t);
		}
		else {

			if (editColumn && (columnIndex == columnsLength)) {
				return buildEditButton(t);
			}
			else if (deleteColumn) {
				return buildDeleteButton(t);
			}
		}

		return null;
	}

	/**
	 * Builds a generic button with actionListener
	 * @param buttonCaption
	 * @param bean
	 * @param actionListener
	 * @param icon
	 * @return The constructed button
	 */
	protected Button buildGenericButton(String buttonCaption, T bean, ActionListener actionListener, ImageReference icon) {
		Button button = FormFactory.button(buttonCaption);

		if (icon != null) {
			button.setIcon(icon);
		}
		else if (buttonCaption != null) {
			button.setText(buttonCaption);
		}

		if (actionListener != null) {
			button.addActionListener(actionListener);
		}

		button.setActionCommand(getActionCommandFromBean(bean));

		return button;
	}

	/**
	 * Adds a generic button.
	 * @param columnHeaderCaption
	 * @param buttonCaption
	 * @param bean
	 * @param actionListener
	 * @param icon
	 * @param columnWidth
	 * @return
	 */
	public void addGenericButtonColumn(String columnHeaderCaption, String buttonCaption, ActionListener actionListener,
			ImageReference icon, int columnWidth, Style... styles) {
		GenericButtonBuilder genericButtonBuilder = new GenericButtonBuilder(buttonCaption, actionListener, icon, null,
				styles);
		addTableColumn(columnHeaderCaption, genericButtonBuilder, columnWidth);
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
			logger.debug("Bean passed: " + bean.toString());
			logger.debug("Id found: " + beanId);
		}

		Button button = null;

		if (styles != null && styles.getDeleteImage() != null) {
			button = FormFactory.iconButton("Selecionar", styles.getDeleteImage());
		}
		else {
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
		}
		else {
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
