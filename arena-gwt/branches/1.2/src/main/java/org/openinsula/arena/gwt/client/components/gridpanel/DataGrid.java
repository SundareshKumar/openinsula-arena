package org.openinsula.arena.gwt.client.components.gridpanel;

import java.util.ArrayList;
import java.util.List;

import org.openinsula.arena.gwt.client.components.gridpanel.DataGridModel.Entry;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

public class DataGrid<R, C> extends Composite implements DataGridModelListener {

	private DataGridModel<R, C> model;

	private HorizontalPanel panel;

	private ColorGrid grid;

	private List<DataGridListener<R, C>> dataGridListeners;

	private FocusWidget blockSelectionBaseCell;

	private boolean multipleColumnSelectionAllowed;

	private boolean multipleRowSelectionAllowed;

	@SuppressWarnings("unchecked")
	private SelectionWrapper selectionWrapper = new SelectionWrapper<ToggleButton>() {
		public boolean isSelected(ToggleButton widget) {
			return widget.isDown();
		}

		public void setSelected(ToggleButton widget, boolean selected) {
			widget.setDown(selected);
		}
	};

	private TitleRender<Widget, C> defaultColumnTitleRenderer = new TitleRender<Widget, C>() {
		public Widget render(C value) {
			Label label = new Label(value.toString());
			label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			return label;
		}
	};

	private TitleRender<Widget, R> defaultRowTitleRenderer = new TitleRender<Widget, R>() {
		public Widget render(R value) {
			return new Label(value.toString());
		}
	};

	private DataGridCellRender<R, C> defaultCellRenderer = new DataGridCellRender<R, C>() {
		public FocusWidget render(R rowValue, C columnValue) {
			return new ToggleButton();
		}
	};

	private DataGridListener<R, C> blockSelectionDataGridListener;

	private DataGridListener<R, C> blockSelectionDataGridListener() {
		if (blockSelectionDataGridListener == null) {
			blockSelectionDataGridListener = new DataGridListener<R, C>() {

				@SuppressWarnings("unchecked")
				public void onCellClicked(FocusWidget sender, R rowValue, C columnValue, int row, int col) {
					if (blockSelectionBaseCell != null) {
						int[] coordinate = indexOfCell(blockSelectionBaseCell);
						boolean cellClickedSelected = selectionWrapper.isSelected(blockSelectionBaseCell);
						if (coordinate != null) {
							int firstRow, firstCol, lastRow, lastCol;
							if (coordinate[0] > row) {
								firstRow = row;
								lastRow = coordinate[0];
							}
							else {
								firstRow = coordinate[0];
								lastRow = row;
							}

							if (coordinate[1] > col) {
								firstCol = col;
								lastCol = coordinate[1];
							}
							else {
								firstCol = coordinate[1];
								lastCol = col;
							}

							boolean senderSelected = selectionWrapper.isSelected(sender);
							clearSelection();
							if (validateSelection(firstRow, firstCol, lastRow, lastCol)) {
								makeSelection(firstRow, firstCol, lastRow, lastCol, senderSelected);
							} else {
								fireInvalidSelection(sender, row, col);
							}
						}
						selectionWrapper.setSelected(blockSelectionBaseCell, cellClickedSelected);
					}
				}
				public void onInvalidSelection(FocusWidget sender, int row, int col) {
				}
			};
		}
		return blockSelectionDataGridListener;
	}

	protected void fireInvalidSelection(FocusWidget sender, int row, int col) {
		for (DataGridListener<R, C> listener : dataGridListeners()) {
			listener.onInvalidSelection(sender, row, col);
		}
	}

	private boolean validateSelection(int firstRow, int firstCol, int lastRow, int lastCol) {
		if (!multipleRowSelectionAllowed && firstRow != lastRow) {
			return false;
		}

		if (!multipleColumnSelectionAllowed && firstCol != lastCol) {
			return false;
		}

		for (int i = firstRow; i <= lastRow; i++) {
			for (int j = firstCol; j <= lastCol; j++) {
				FocusWidget component = (FocusWidget) grid.getWidget(i, j);
				if (component != null && !component.isEnabled()) {
					return false;
				}
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private void makeSelection(int firstRow, int firstCol, int lastRow, int lastCol, boolean selected) {
		for (int i = firstRow; i <= lastRow; i++) {
			for (int j = firstCol; j <= lastCol; j++) {
				Widget component = grid.getWidget(i, j);
				if (component instanceof FocusWidget) {
					selectionWrapper.setSelected((FocusWidget) component, selected);
				}
			}
		}
	}

	private void clearSelection() {
		makeSelection(1, 1, grid.getRowCount() - 1, grid.getColumnCount() - 1, false);
	}

	/**
	 * @return coordenadas da celula selecionada anteriormente [linha/coluna].
	 * ou null caso não exista célula previamente selecionada.
	 */
	@SuppressWarnings("unchecked")
	private int[] indexOfCell(FocusWidget widget) {
		int rowCount = grid.getRowCount() + 1;
		int colCount = grid.getColumnCount() + 1;

		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < colCount; j++) {
				int row = i - 1;
				int col = j - 1;
				Widget component = grid.getWidget(row, col);
				if (component != null && component.equals(widget)) {
					return new int[] { row, col };
				}
			}
		}
		return null;
	}

	private void setBlockSelectionAllowed(boolean allow) {
		if (allow) {
			if (!dataGridListeners.contains(blockSelectionDataGridListener())) {
				addDataGridListener(blockSelectionDataGridListener());
			}
		}
		else {
			removeDataGridListener(blockSelectionDataGridListener());
		}
	}

	public void setMultipleRowSelectionAllowed(boolean allow) {
		this.multipleRowSelectionAllowed = allow;
		if (allow && dataGridListeners().contains(blockSelectionDataGridListener())) {
			setBlockSelectionAllowed(allow);
		}

		setBlockSelectionAllowed(multipleColumnSelectionAllowed || multipleRowSelectionAllowed);
	}

	public void setMultipleColumnSelectionAllowed(boolean allow) {
		this.multipleColumnSelectionAllowed = allow;
		if (allow && dataGridListeners().contains(blockSelectionDataGridListener())) {
			setBlockSelectionAllowed(allow);
		}

		setBlockSelectionAllowed(multipleColumnSelectionAllowed || multipleRowSelectionAllowed);
	}

	/**
	 * //TODO método construtor
	 */
	public DataGrid() {
		panel = new HorizontalPanel();
		initWidget(panel);
	}

	private List<DataGridListener<R, C>> dataGridListeners() {
		if (dataGridListeners == null) {
			dataGridListeners = new ArrayList<DataGridListener<R, C>>();
		}
		return dataGridListeners;
	}

	private DataGridModel<R, C> model() {
		if (model == null) {
			model = new DefaultDataGridModel<R, C>();
		}
		return model;
	}

	public void addDataGridListener(DataGridListener<R, C> listener) {
		dataGridListeners().add(listener);
	}

	public void removeDataGridListener(DataGridListener<R, C> listener) {
		dataGridListeners().remove(listener);
	}

	protected void fireCellClicked(FocusWidget sender, R rowValue, C columnValue, int row, int col) {
		for (DataGridListener<R, C> listener : dataGridListeners()) {
			listener.onCellClicked(sender, rowValue, columnValue, row, col);
		}
	}

	private void renderGrid() {
		int rowCount = model().getRowList().size() + 1;
		int colCount = model().getColumnList().size() + 1;

		grid = new ColorGrid(rowCount, colCount);
		panel.clear();
		panel.add(grid);

		// montar titulo das linhas
		for (int i = 1; i < rowCount; i++) {
			grid.setWidget(i, 0, defaultRowTitleRenderer.render(model().getRowValueAt(i - 1)));
		}

		// montar titulo das colunas
		for (int i = 1; i < colCount; i++) {
			grid.setWidget(0, i, defaultColumnTitleRenderer.render(model().getColumnValueAt(i - 1)));
		}

		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < colCount; j++) {
				final int actualRow = i - 1;
				final int actualCol = j - 1;

				final R rowValue = model().getRowValueAt(actualRow);
				final C colValue = model().getColumnValueAt(actualCol);

				FocusWidget button = defaultCellRenderer.render(rowValue, colValue);
				button.addClickListener(new ClickListener() {
					@SuppressWarnings("unchecked")
					public void onClick(Widget sender) {
						if (blockSelectionBaseCell == null) {
							blockSelectionBaseCell = (FocusWidget) sender;
						}
						else if (blockSelectionBaseCell == sender) {
							blockSelectionBaseCell = null;
							clearSelection();
						}

						fireCellClicked((FocusWidget) sender, rowValue, colValue, actualRow + 1, actualCol + 1);
					}
				});
				grid.setWidget(i, j, button);
			}
		}
	}

	public DataGridModel<R, C> getModel() {
		return model();
	}

	public void setModel(DataGridModel<R, C> model) {
		this.model = model;
		model.addDataGridListener(this);
		renderGrid();
	}

	public void onDataChange() {
		renderGrid();
	}

	public TitleRender<Widget, C> getDefaultColumnTitleRenderer() {
		return defaultColumnTitleRenderer;
	}

	public void setDefaultColumnTitleRenderer(TitleRender<Widget, C> defaultColumnTitleRenderer) {
		this.defaultColumnTitleRenderer = defaultColumnTitleRenderer;
	}

	public TitleRender<Widget, R> getDefaultRowTitleRenderer() {
		return defaultRowTitleRenderer;
	}

	public void setDefaultRowTitleRenderer(TitleRender<Widget, R> defaultRowTitleRenderer) {
		this.defaultRowTitleRenderer = defaultRowTitleRenderer;
	}

	public DataGridCellRender<R, C> getDefaultCellRenderer() {
		return defaultCellRenderer;
	}

	public void setDefaultCellRenderer(DataGridCellRender<R, C> defaultCellRenderer) {
		this.defaultCellRenderer = defaultCellRenderer;
	}

	@SuppressWarnings("unchecked")
	private List<int[]> getSelectedCoordinates() {
		List<int[]> coordinates = new ArrayList<int[]>();
		for (int i = 1; i < grid.getRowCount(); i++) {
			for (int j = 1; j < grid.getColumnCount(); j++) {
				FocusWidget component = (FocusWidget) grid.getWidget(i, j);
				if (component != null && selectionWrapper.isSelected(component)) {
					coordinates.add(new int[] {i -1, j -1});
				}
			}
		}
		return coordinates;
	}

	public List<Entry<R, C>> getSelectedEntries() {
		List<Entry<R, C>> entries = new ArrayList<Entry<R,C>>();
		for (int[] coordinate : getSelectedCoordinates()) {
			entries.add(model.getEntryAt(coordinate[0], coordinate[1]));
		}
		return entries;
	}

	public boolean isMultipleColumnSelectionAllowed() {
		return multipleColumnSelectionAllowed;
	}

	public boolean isMultipleRowSelectionAllowed() {
		return multipleRowSelectionAllowed;
	}

}
