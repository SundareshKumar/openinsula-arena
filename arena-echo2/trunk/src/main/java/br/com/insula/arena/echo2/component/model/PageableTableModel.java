package br.com.insula.arena.echo2.component.model;

import nextapp.echo2.app.table.TableModel;

public interface PageableTableModel extends TableModel {

	public int getPageCount();
	
	public int getCurrentPage();
	
	public void setCurrentPage(int index);
	
	public void setPageSize(int index);

}
