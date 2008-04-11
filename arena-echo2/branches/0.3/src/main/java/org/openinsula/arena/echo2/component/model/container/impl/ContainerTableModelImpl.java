package org.openinsula.arena.echo2.component.model.container.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openinsula.arena.echo2.component.model.container.ContainerTableModel;

public abstract class ContainerTableModelImpl<T> extends AbstractTableModel implements ContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

	protected final Log logger = LogFactory.getLog(getClass());

	protected List<T> container = new ArrayList<T>();

	private boolean permitDuplicates;

	private boolean substituteDuplicate;

	public ContainerTableModelImpl() {
		this(true, true);
	}

	public ContainerTableModelImpl(boolean permitDuplicates, boolean substituteDuplicate) {
		this.permitDuplicates = permitDuplicates;
		this.substituteDuplicate = substituteDuplicate;
	}

	/**
	 * Este método deve ser implementado para trazer um array de String com o
	 * nome das colunas
	 * @return Um array de String com o nome das colunas
	 */
	public abstract String[] getColumns();

	public int getColumnCount() {
		return getColumns().length;
	}

	public String getColumnName(int i) {
		return getColumns()[i];
	}

	public int getRowCount() {
		return container.size();
	}

	public Object getValueAt(int columnIndex, int rowIndex) {
		T t = container.get(rowIndex);
		return getColumnValue(columnIndex, t);
	}

	/**
	 * Este método é chamado pelo getValueAt que busca o bean do tipo T e
	 * repassa para que seja retornado o valor da propriedade de acordo com a
	 * coluna requisitada.
	 * @param columnIndex
	 * @param t
	 * @return
	 */
	public abstract T getColumnValue(int columnIndex, T t);

	/** Métodos da interface ContainerTableModel */
	public void addItem(T bean) {
		if (permitDuplicates) {
			container.add(bean);
		}
		else {
			if (container.contains(bean) && substituteDuplicate) {
				container.remove(bean);
			}
			container.add(bean);
		}
	}

	public void clear() {
		container.clear();
	}

	public boolean deleteItem(Serializable itemId) {
		try {
			int index = ((Integer) itemId).intValue();
			return (container.remove(index) != null);
		}
		catch (Exception e) {
			return false;
		}
	}

	public boolean deleteItem(T bean) {
		try {
			return container.remove(bean);
		}
		catch (Exception e) {
			return false;
		}
	}

	public T getSelectedItem(ActionEvent ae) {
		try {
			String itemId = ae.getActionCommand();
			return getSelectedItem(itemId);
		}
		catch (Exception e) {
			return null;
		}
	}

	public T getSelectedItem(Serializable itemId) {
		if (itemId == null) {
			return null;
		}

		try {
			int index = ((Integer) itemId).intValue();
			return container.get(index);
		}
		catch (Exception e) {
			logger.warn("Ocorreu um erro ao buscar um item pelo id: " + itemId.toString());
			return null;
		}
	}

	public void addItems(T... beans) {
		for (T bean : beans) {
			this.addItem(bean);
		}
	}

	public void addItems(Collection<T> beans) {
		for (T bean : beans) {
			this.addItem(bean);
		}
	}

	public void setItems(Collection<T> beans) {
		this.clear();
		this.addItems(beans);
	}

}
