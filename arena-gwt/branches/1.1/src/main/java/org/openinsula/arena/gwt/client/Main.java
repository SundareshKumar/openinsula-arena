package org.openinsula.arena.gwt.client;

import java.util.Arrays;

import org.openinsula.arena.gwt.client.components.datatable.AbstractDataTableModel;
import org.openinsula.arena.gwt.client.components.datatable.DataTable;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {

	public void onModuleLoad() {
		
		DataTable<String> table = new DataTable<String>();
		table.setModel(new AbstractDataTableModel<String>() {
			private final String[] columns = new String[] {"Valor", "Tamanho"};
			@Override
			protected String[] getColumnNames() {
				return columns;
			}

			@Override
			protected Object getValueAtColumn(String bean, int column) {
				switch (column) {
				case 0:
					return bean;
				case 1: 
					return bean.length();
				}
				return null;
			}
		});
		table.setVisibleRows(4);
		table.getModel().setItens(Arrays.asList("teste", "123", "maeda", "insula", "java", "maringa", "googlewebtoolkit", "php", "pascal",
				"ruby", "phyton", "smalltalk", "c++", "blues", "rock", "jazz"));
		
		RootPanel.get().add(table);
		
	}
}
