package org.openinsula.arena.gwt.components.client.ui.table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.ui.Grid;

/**
 * @author João Galli
 * 
 */
public class SelectionGrid extends Grid {

	private SelectionPolicy selectionPolicy;

	private final Set<Integer> selectedRows = new HashSet<Integer>();

	private final List<TableSelectionListener> selectionListeners = new ArrayList<TableSelectionListener>();

	public static enum SelectionPolicy {
		DISABLED, ONE_ROW, MULTI_ROW
	}

	public SelectionGrid() {
	}

	public SelectionGrid(SelectionPolicy selectionPolicy) {
		super();
		this.selectionPolicy = selectionPolicy;
	}

	public Set<Integer> getSelectedRows() {
		return selectedRows;
	}

	/**
	 * Remove a seleção de todas as linhas.
	 */
	public void deselectRows() {
		selectedRows.clear();
		for (final TableSelectionListener listener : selectionListeners) {
			listener.onAllRowsDeselected();
		}
	}

	public SelectionPolicy getSelectionPolicy() {
		return selectionPolicy;
	}

	public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
		this.selectionPolicy = selectionPolicy;
	}

	/**
	 * Seleciona uma linha.
	 * @param row Número de index da linha a ser selecionada.
	 */
	public void selectRow(Integer row) {
		if (selectionPolicy.equals(SelectionPolicy.ONE_ROW)) {
			selectedRows.clear();
		}
		selectedRows.add(row);
		for (final TableSelectionListener listener : selectionListeners) {
			listener.onRowSelected(row);
		}
	}

	/**
	 * Remove a seleção de uma linha.
	 * @param row Número de index da linha a perder a seleção.
	 */
	public void deselectRow(Integer row) {
		selectedRows.remove(row);
		for (final TableSelectionListener listener : selectionListeners) {
			listener.onRowDeselected(row);
		}
	}

	public void addTableSelectionListener(TableSelectionListener listener) {
		selectionListeners.add(listener);
	}

	public void removeTableSelectionListener(TableSelectionListener oldListener) {
		selectionListeners.remove(oldListener);
	}

}
