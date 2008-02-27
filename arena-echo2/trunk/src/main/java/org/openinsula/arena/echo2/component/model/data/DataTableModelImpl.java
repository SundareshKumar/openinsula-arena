package org.openinsula.arena.echo2.component.model.data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.table.DefaultTableModel;
import nextapp.echo2.app.table.TableColumnModel;

import org.apache.commons.beanutils.PropertyUtils;
import org.openinsula.arena.echo2.component.div.Div;
import org.openinsula.arena.echo2.component.util.FormFactory;
import org.openinsula.arena.echo2.component.util.Styles;
import org.springframework.beans.factory.annotation.Autowired;

public class DataTableModelImpl extends DefaultTableModel implements DataTableModel {
	private static final long serialVersionUID = 1L;

	private Map<String, String> columnMap = new LinkedHashMap<String, String>();

	private List<Object> beanList = new ArrayList<Object>();

	private boolean editColumn = true;

	private boolean deleteColumn = true;

	private ActionListener editButtonActionListener;

	private ActionListener deleteButtonActionListener;

	private TableColumnModel tableColumnModel;

	private List<Extent> widthColumns = new ArrayList<Extent>();

	@Autowired(required = false)
	private Styles styles;

	public void setTableColumnModel(TableColumnModel tableColumnModel) {
		this.tableColumnModel = tableColumnModel;
	}

	public void addColumn(String label, String beanField) {
		addColumn(label, beanField, 0);
	}

	public void addColumn(String label, String beanField, int width) {
		columnMap.put(beanField, label);
		widthColumns.add(new Extent(width, Extent.PX));
		buildColumns();
	}

	public void clear() {
		while (getRowCount() > 0) {
			deleteRow(0);
		}

		beanList.clear();
	}

	public void setRows(Collection beans) {
		clear();
		for (Object bean : beans) {
			addRow(bean);
		}
	}

	public void addRow(Object bean) {
		List<Object> row = new ArrayList<Object>();

		int columnIndex = 0;
		for (String field : columnMap.keySet()) {
			try {
				String valorCampo = formatProperty(field, PropertyUtils.getProperty(bean, field)).toString();

				Div columnDiv = new Div();
				columnDiv.setOverflow(Div.OVERFLOW_HIDDEN);
				columnDiv.setToolTipText(valorCampo);

				if (tableColumnModel != null && widthColumns.get(columnIndex) != null) {
					tableColumnModel.getColumn(columnIndex).setWidth(widthColumns.get(columnIndex));
				}

				Label valorDescricaoLabel = new Label();
				valorDescricaoLabel.setText(valorCampo);
				valorDescricaoLabel.setLineWrap(false);
				valorDescricaoLabel.setFont(new Font(Font.VERDANA, Font.PLAIN, new Extent(10, Extent.PX)));

				columnDiv.add(valorDescricaoLabel);

				row.add(columnDiv);
				columnIndex++;
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

		long beanId = getBeanId(bean);

		Button button = null;
		if (isEditColumn()) {
			if (styles != null) {
				button = FormFactory.iconButton("Alterar", styles.getEditIcon());
			}
			else {
				button = FormFactory.button("Alterar");
			}

			if ((editButtonActionListener != null) && (beanId != -1)) {
				button.setActionCommand(Long.toString(beanId));
				button.addActionListener(editButtonActionListener);
			}
			row.add(button);
		}
		if (isDeleteColumn()) {
			if (styles != null) {
				button = FormFactory.iconButton("Excluir", styles.getDeleteIcon());
			}
			else {
				button = FormFactory.button("Excluir");
			}

			if ((deleteButtonActionListener != null) && (beanId != -1)) {
				button.setActionCommand("" + beanId);
				button.addActionListener(deleteButtonActionListener);
			}
			row.add(button);
		}

		if (!beanList.contains(bean)) {
			super.addRow(row.toArray());
			beanList.add(bean);
		}
	}

	protected long getBeanId(Object bean) {
		try {
			return (Long) PropertyUtils.getProperty(bean, "id");
		}
		catch (IllegalAccessException e) {
		}
		catch (InvocationTargetException e) {
		}
		catch (NoSuchMethodException e) {
		}
		return -1;
	}
	
	/*
	 * Método que recebe o nome e o valor da propriedade para ser formatado em
	 * sub-classes
	 */
	protected Object formatProperty(String property, Object value) {
		return value;
	}

	public long getSelectedBeanId(ActionEvent ae) {
		return Long.parseLong(ae.getActionCommand());
	}

	public void deleteRow(Object bean) {
		int index = -1;
		int counter = 0;

		for (Object item : beanList) {
			if (item.equals(bean)) {
				index = counter;
				break;
			}
			else {
				counter++;
			}
		}
		if (index >= 0) {
			super.deleteRow(index);
			beanList.remove(bean);
		}
	}

	private void buildColumns() {
		int columnCount = columnMap.size();
		if (isEditColumn())
			columnCount++;
		if (isDeleteColumn())
			columnCount++;
		setColumnCount(columnCount);

		int columnIndex = 0;
		for (String column : columnMap.values()) {
			setColumnName(columnIndex, column);
			columnIndex++;
		}

		if (isEditColumn()) {
			setColumnName(columnIndex, "");
			columnIndex++;
		}
		if (isDeleteColumn()) {
			setColumnName(columnIndex, "");
			columnIndex++;
		}
	}

	public List getBeanList() {
		return beanList;
	}

	public boolean isDeleteColumn() {
		return deleteColumn;
	}

	public void setDeleteColumn(boolean deleteColumn) {
		this.deleteColumn = deleteColumn;
		buildColumns();
	}

	public boolean isEditColumn() {
		return editColumn;
	}

	public void setEditColumn(boolean editColumn) {
		this.editColumn = editColumn;
		buildColumns();
	}

	public ActionListener getDeleteButtonActionListener() {
		return deleteButtonActionListener;
	}

	public void setDeleteButtonActionListener(ActionListener deleteButtonActionListener) {
		this.deleteButtonActionListener = deleteButtonActionListener;
	}

	public ActionListener getEditButtonActionListener() {
		return editButtonActionListener;
	}

	public void setEditButtonActionListener(ActionListener editButtonActionListener) {
		this.editButtonActionListener = editButtonActionListener;
	}

	public Styles getStyles() {
		return styles;
	}

	public void setStyles(Styles styles) {
		this.styles = styles;
	}
}