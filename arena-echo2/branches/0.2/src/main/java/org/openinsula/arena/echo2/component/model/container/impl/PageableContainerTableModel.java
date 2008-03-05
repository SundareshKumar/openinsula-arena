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
	
	private int pageSize;
	
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
	}

	public void setPageSize(int index) {
		this.pageSize = index;
	}

	public int getPageSize() {
		return pageSize;
	}

}
