package org.openinsula.arena.gwt.client;

import org.openinsula.arena.gwt.client.components.test.search.AbstractDetailsSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.AbstractSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.SearchFormTransitionListener;
import org.openinsula.arena.gwt.client.components.test.search.cliente.Cliente;
import org.openinsula.arena.gwt.client.components.test.search.cliente.ClienteSearchForm;
import org.openinsula.arena.gwt.client.ui.FocusUtils;
import org.openinsula.arena.gwt.client.ui.form.FormBuilder;
import org.openinsula.arena.gwt.client.ui.form.FormFactory;
import org.openinsula.arena.gwt.client.ui.form.FormItem;
import org.openinsula.arena.gwt.client.ui.form.GroupFormItem;
import org.openinsula.arena.gwt.client.ui.list.BeanListBox;
import org.openinsula.arena.gwt.client.ui.list.DefaultListBoxModel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Main implements EntryPoint {

	public void onModuleLoad() {

//		This code shows the html "id: 10" before the application is fully loaded
//		(before the <div id="main"></div>)

//		GwtEntity<Integer> entity = new GwtEntity<Integer>() {
//
//		};
//
//		entity.setId(10);

		DefaultListBoxModel<String> model = new DefaultListBoxModel<String>("123", "34", "asb", "teste");

		final BeanListBox<String> listBox = new BeanListBox<String>(model);

		TextBox dataTextBox = new TextBox();

		final ClienteSearchForm clienteSearchForm = new ClienteSearchForm(listBox);
		clienteSearchForm.setEditionAllowed(true);
		clienteSearchForm.setInsertionAllowed(true);
		clienteSearchForm.addTransitionListener(new SearchFormTransitionListener<Cliente>() {
			public boolean beforeDetailFormShow(AbstractDetailsSearchFormTemplate<Cliente> detailForm, boolean editionMode) {
				return "123".equals(listBox.getModel().getSelectedItem());
			}

			public boolean beforeSearchFormShow(AbstractSearchFormTemplate<Cliente> searchForm) {
				return true;
			}
		});


		final FormItem<ClienteSearchForm> clienteSearchFormItem = new FormItem<ClienteSearchForm>("CLiente", clienteSearchForm);

		FormBuilder builder = new FormBuilder();
		builder.add(clienteSearchFormItem);
		builder.add(new FormItem<BeanListBox<String>>("strings", listBox));
		builder.add(new FormItem<TextBox>("data", dataTextBox));
		builder.add(new GroupFormItem<Widget>("Endereço/Número", new Widget[] {FormFactory.textBox(), FormFactory.textBox()}, "", true, true));

		final ListBox combo = new ListBox();
		combo.addItem("123");
		combo.addItem("1");
		combo.addItem("2");

		Button button = new Button("123", new ClickListener() {
			public void onClick(Widget sender) {
				GWT.log("selecionado: " + listBox.getSelectedItem(), null);
//				listBox.setSelectedItem("123");
			}
		});


		FocusUtils.nextOnEnter(listBox, dataTextBox);

//		RootPanel.get("main").add(builder.toPanel());

//		RootPanel.get("main").add(combo);
//		RootPanel.get("main").add(button);
	}

}
