package org.openinsula.arena.gwt.client;

import java.util.Date;

import org.openinsula.arena.gwt.client.ui.FocusUtils;
import org.openinsula.arena.gwt.client.ui.form.util.DateFormItem;
import org.openinsula.arena.gwt.client.ui.form.util.SimpleDateWidget;
import org.openinsula.arena.gwt.client.ui.form.util.SimpleDateWidget.LabelAlignment;
import org.openinsula.arena.gwt.client.ui.form.validator.DateFormItemValidator;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorActionAdapter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Main implements EntryPoint {

	public void onModuleLoad() {
//		RootPanel.get().add(new TesteDateWidget());
	}

	private class TesteDateWidget extends Composite {

		public TesteDateWidget() {
			VerticalPanel content = new VerticalPanel();
			content.setSpacing(5);
			initWidget(content);

			content.add(new Label("Oie"));
			final DateFormItem dateFormItem = new DateFormItem();

			content.add(dateFormItem);

			dateFormItem.addFormItemValidator(new DateFormItemValidator());

			Button validaDataButton = new Button("data ?");

			validaDataButton.addClickListener(new ClickListener() {
				public void onClick(Widget arg0) {
					dateFormItem.validate(new ValidatorActionAdapter() {
						public void onSuccess() {
							Window.alert("data = " + dateFormItem.getDate());
						}

						public void onFail() {
							super.onFail();
							Window.alert("falhou");
						}
					});
				}
			});

			content.add(validaDataButton);

			SimpleDateWidget widgetHoje = new SimpleDateWidget();
			widgetHoje.setDate(new Date());

			content.add(widgetHoje);

			SimpleDateWidget widgetHoje2 = new SimpleDateWidget(LabelAlignment.LEFT, "ano", "m\u00eas", "dia");
			content.add(new DateFormItem("Data bacaninha", widgetHoje2, null, false));

			content.add(new Label("Focus...[ENTER]* para o proximo campo"));

			final SimpleDateWidget widgetFocus = new SimpleDateWidget();

			Button focusButton = new Button("Foca no dia ?");
			focusButton.addClickListener(new ClickListener(){
				public void onClick(Widget arg0) {
					FocusUtils.deferredFocus(widgetFocus);
				}
			});

			content.add(widgetFocus);
			content.add(focusButton);

			content.add(new Label("Enable..."));

			final SimpleDateWidget widgetEnabling = new SimpleDateWidget();

			//*
			FocusUtils.nextOnEnter(widgetFocus, widgetEnabling);

			Button ativaButton = new Button("Ativa ?");
			Button desativaButton = new Button("DESAtiva ?");

			ativaButton.addClickListener(new ClickListener(){
				public void onClick(Widget arg0) {
					widgetEnabling.setEnabled(true);
				}
			});

			desativaButton.addClickListener(new ClickListener(){
				public void onClick(Widget arg0) {
					widgetEnabling.setEnabled(false);
				}
			});

			content.add(widgetEnabling);
			content.add(ativaButton);
			content.add(desativaButton);
		}

	}

	// private class Apartamento {
	// List<Integer> diasOcupado;
	// String numero;
	//
	// Apartamento(String numero, Integer ... diasOcupado) {
	// this.numero = numero;
	// this.diasOcupado = Arrays.asList(diasOcupado);
	// }
	//
	// public List<Integer> getDiasOcupado() {
	// return diasOcupado;
	// }
	//
	// public String getNumero() {
	// return numero;
	// }
	//
	// public boolean isDiaOcupado(int dia) {
	// return diasOcupado.contains(dia);
	// }
	//
	// }
	//
	// public void onModuleLoad() {
	//
	// DataGridModel<Apartamento, Integer> dataModel = new
	// DefaultDataGridModel<Apartamento, Integer>();
	// Apartamento s1 = new Apartamento("Std - 01", 3, 5);
	// Apartamento s2 = new Apartamento("Std - 02", 2, 3, 4);
	// Apartamento s3 = new Apartamento("Std - 03", 1, 3, 7);
	// Apartamento s4 = new Apartamento("Std - 04", 6);
	//
	//
	// dataModel.setData(Arrays.asList(s1, s2, s3, s4),
	// Arrays.asList(1, 2, 3, 4, 5, 6, 7));
	//
	// final DataGrid<Apartamento, Integer> grid = new DataGrid<Apartamento,
	// Integer>();
	// grid.setDefaultCellRenderer(new DataGridCellRender<Apartamento,
	// Integer>() {
	//
	// public FocusWidget render(Apartamento rowValue, Integer columnValue) {
	// ToggleButton button = new ToggleButton();
	// if (rowValue.isDiaOcupado(columnValue)) {
	// button.setText("(Reservado)");
	// button.setEnabled(false);
	// } else {
	// button.setText("(Livre)");
	// button.setEnabled(true);
	// }
	//
	// return button;
	// }
	// });
	// grid.setDefaultRowTitleRenderer(new TitleRender<Widget, Apartamento>() {
	// public Widget render(Apartamento value) {
	// return new Label(value.getNumero());
	// }
	// });
	//
	// grid.addDataGridListener(new DataGridListener<Apartamento, Integer>() {
	// public void onCellClicked(FocusWidget sender, Apartamento rowValue,
	// Integer columnValue, int row, int col) {
	// }
	//
	// public void onInvalidSelection(FocusWidget sender, int row, int col) {
	// Window.alert("Seleção inválida");
	// }
	// });
	// grid.setModel(dataModel);
	//
	// // grid.setMultipleRowSelectionAllowed(true);
	// grid.setMultipleColumnSelectionAllowed(true);
	//
	// Button reservarButton = new Button("Reservar");
	// reservarButton.addClickListener(new ClickListener() {
	// public void onClick(Widget sender) {
	// List<Entry<Apartamento, Integer>> selection = grid.getSelectedEntries();
	// StringBuilder sb = new StringBuilder();
	// for (Entry<Apartamento, Integer> entry : selection) {
	// sb.append("apartamento: ");
	// sb.append(entry.getRowValue().getNumero());
	// sb.append(", dia: ");
	// sb.append(entry.getColumnValue());
	// sb.append("\n");
	// }
	// Window.alert(sb.toString());
	// }
	// });
	//
	// RootPanel.get("main").add(grid);
	// RootPanel.get("main").add(reservarButton);
	//
	// }

}
