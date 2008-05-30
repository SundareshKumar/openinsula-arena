package org.openinsula.arena.echo2.component.model.container.impl;

import org.openinsula.arena.echo2.component.model.PageableTableModel;

public class PageableContainerTableModel<T> extends BeanReflectionContainerTableModel<T> implements PageableTableModel {
	private static final long serialVersionUID = 1L;

	public PageableContainerTableModel() {
	}

	public PageableContainerTableModel(boolean permitDuplicates, boolean substituteDuplicate) {
		super(permitDuplicates, substituteDuplicate);
	}

	private int currentPage;

	private int pageSize = 20;

	@Override
	public Object getValueAt(int columnIndex, int rowIndex) {
		return super.getValueAt(columnIndex, getCurrentPageItemIndex(rowIndex));
	}

	@Override
	public int getRowCount() {
		return getCurrentPageSize();
	}

	/**
	 * @param index
	 * @return The right item index for this page.
	 */
	public int getCurrentPageItemIndex(int index) {
		return index + (getPageSize() * getCurrentPage());
	}

	/**
	 * @return The number of items in the current page
	 */
	protected int getCurrentPageSize() {
		int currentPageSize = getPageSize();

		int lastPage = (getPageCount() > 0) ? (getPageCount() - 1) : 0;

		if (lastPage == getCurrentPage()) {
			if (getCurrentPage() == 0) {
				currentPageSize = size();
			} else if ((size() % getPageSize()) == 0) {
				return getPageSize();
			} else {
				currentPageSize = (size() % getPageSize());
			}
		}

		if (size() == 0) {
			currentPageSize = 0;
		}
		
		return currentPageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageCount() {
		int size = size();

		int pageCount = size / pageSize;

		if ((size % pageSize) > 0) {
			pageCount++;
		}

		return pageCount;
	}

	public void setCurrentPage(int index) {
		this.currentPage = index;
		this.fireTableDataChanged();
	}

	public void setPageSize(int index) {
		this.pageSize = index;
	}

	public int getPageSize() {
		return pageSize;
	}

}
