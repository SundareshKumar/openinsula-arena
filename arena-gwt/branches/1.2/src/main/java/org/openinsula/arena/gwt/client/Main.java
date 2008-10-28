package org.openinsula.arena.gwt.client;

import org.openinsula.arena.gwt.client.components.test.search.cliente.ClienteSearchForm;
import org.openinsula.arena.gwt.client.ui.form.FormBuilder;
import org.openinsula.arena.gwt.client.ui.form.FormItem;
import org.openinsula.arena.gwt.client.ui.form.validator.NotNullFormItemValidator;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorAction;
import org.openinsula.arena.gwt.client.ui.list.BeanListBox;
import org.openinsula.arena.gwt.client.ui.list.DefaultListBoxModel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RootPanel;
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
//		clienteSearchForm.addTransitionListener(new SearchFormTransitionListener<Cliente>() {
//			public boolean beforeDetailFormShow(AbstractDetailsSearchFormTemplate<Cliente> detailForm, boolean editionMode) {
//				return "123".equals(listBox.getModel().getSelectedItem());
//			}
//
//			public boolean beforeSearchFormShow(AbstractSearchFormTemplate<Cliente> searchForm) {
//				return true;
//			}
//		});
//
//
		final FormItem<ClienteSearchForm> clienteSearchFormItem = new FormItem<ClienteSearchForm>("CLiente", clienteSearchForm);
//		clienteSearchFormItem.addFormItemValidator(new NotNullFormItemValidator());
		final FormItem<BeanListBox<String>> listBoxFormItem = new FormItem<BeanListBox<String>>("strings", listBox);
		listBoxFormItem.addFormItemValidator(new NotNullFormItemValidator());
//		listBoxFormItem.addFormItemValidator(new BeanListBoxNotNullFormItemValidator<String>());
//
//		final FormItem<TextBox> dataFormItem = new FormItem<TextBox>("Data", dataTextBox);
//		dataFormItem.addFormItemValidator(new RegexpFormItemValidator<TextBox>(RegexpFormItemValidator.DATA, "data invalida"));
//
		FormBuilder builder = new FormBuilder();
		builder.add(clienteSearchFormItem);
		builder.add(listBoxFormItem);
//		builder.add(new FormItem<TextBox>("data", dataTextBox));
//		builder.add(new GroupFormItem<Widget>("Endereço/Número", new Widget[] {FormFactory.textBox(), FormFactory.textBox()}, "", true, true));
//
//
//		Button button = new Button("123", new ClickListener() {
//			public void onClick(Widget sender) {
////				GWT.log("selecionado: " + listBox.getSelectedItem(), null);
//				GWT.log("valid: " + dataFormItem.isValid(), null);
//			}
//		});
//
//
//		FocusUtils.nextOnEnter(listBox, dataTextBox);
//
//		RootPanel.get("main").add(builder.toPanel());
////		RootPanel.get("main").add(combo);
//
//		TextBox txt = new TextBox();
//		txt.addFocusListener(new FocusListener() {
//			public void onFocus(Widget sender) {
//				GWT.log("ganhou foco", null);
//			}
//
//			public void onLostFocus(Widget sender) {
//				GWT.log("perdeu o foco", null);
//			}
//		});

//		TextBox data = new TextBox();
//		FormItem<TextBox> formItem = new FormItem<TextBox>("data:", data);
//		formItem.addFormItemValidator(new RegexpFormItemValidator(RegexpFormItemValidator.DATA, "data invalida"));
//
//		BeanListBox<String> list = new BeanListBox<String>(new DefaultListBoxModel<String>("1", "2", "3"));
//		FormItem<BeanListBox<String>> listFormItem = new FormItem<BeanListBox<String>>("list", list);
//
//		TextBox data2 = new TextBox();
//		FormItem<TextBox> formItem2 = new FormItem<TextBox>("data:", data2);
//		formItem2.addFormItemValidator(new RegexpFormItemValidator(RegexpFormItemValidator.DATA, "data invalida"));
//
//
//		FormBuilder builder = new FormBuilder();
//		builder.add(formItem);
//		builder.add(listFormItem);
//		builder.add(formItem2);
//
//		FocusUtils.nextOnEnter(data, list);
//		FocusUtils.nextOnEnter(list, data2);

		final FormItem<TextBox> textBoxFormItem = new FormItem<TextBox>("Teste", new TextBox());
		textBoxFormItem.addFormItemValidator(new NotNullFormItemValidator());
//		textBoxFormItem.addFormItemValidator(new RegexpFormItemValidator(RegexpFormItemValidator.SOMENTE_NUMEROS, "somente numeros"));
//		textBoxFormItem.addFormItemValidator(new RegexpFormItemValidator("[123]", "valor monetario"));

		Button button = new Button("teste");
		button.addClickListener(new ClickListener() {
			public void onClick(Widget arg0) {
				textBoxFormItem.validate(new ValidatorAction() {
					public void onFail() {
						GWT.log("invalido!!!", null);
					}

					public void onSuccess() {
						GWT.log("valido!!!", null);
					}
				});
			}
		});

//		RootPanel.get("main").add(builder.toPanel());
		RootPanel.get("main").add(textBoxFormItem);
		RootPanel.get("main").add(button);

//		RootPanel.get("main").add(button);
	}

}
