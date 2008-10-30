package org.openinsula.arena.gwt.client;

import org.openinsula.arena.gwt.client.ui.form.GroupFormItem;
import org.openinsula.arena.gwt.client.ui.form.validator.RegexpFormItemValidator;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorActionAdapter;

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


//		TextBox textBox = new TextBox();
//		final ClienteSearchForm clienteSearchForm = new ClienteSearchForm(textBox);
//		clienteSearchForm.setInsertionAllowed(true);
//		clienteSearchForm.setEditionAllowed(true);
//
//		final FormItem<ClienteSearchForm> clienteSearchFormItem = new FormItem<ClienteSearchForm>("cliente", clienteSearchForm);
//		final FormItem<TextBox> textBoxFormItem = new FormItem<TextBox>("textBox", textBox);
//
//		clienteSearchFormItem.addFormItemValidator(new NotNullFormItemValidator());
//		clienteSearchFormItem.addFormItemValidator(new AsyncFormItemValidator<ClienteSearchForm, Cliente>() {
//			@Override
//			protected void evaluateResult(Cliente result, EvaluateCallback callback) {
//				callback.success();
//			}
//
//			@Override
//			protected void validate(ClienteSearchForm widget, AsyncCallback<Cliente> callback) {
//				callback.onSuccess(null);
//			}
//
//			public String getInvalidValueMessage() {
//				return "erro";
//			}
//		});
//
//		Button button = new Button("teste");
//		button.addClickListener(new ClickListener() {
//			public void onClick(Widget arg0) {
//				clienteSearchFormItem.validate(new ValidatorAction() {
//					public void onFail() {
//						GWT.log("invalido!!!", null);
//					}
//
//					public void onSuccess() {
//						GWT.log("valido!!!", null);
//						clienteSearchForm.getEditInstance(new GetValueAction<Cliente>() {
//							public void processValue(Cliente value) {
//								GWT.log("cliente: " + value, null);
//								GWT.log("pessoa: " + value.getPessoa(), null);
//								GWT.log("nome: " + value.getPessoa().getNome(), null);
//								GWT.log("cpf: " + value.getPessoa().getCpf(), null);
//							}
//						});
//					}
//				});
//			}
//		});
//
//		RootPanel.get("main").add(clienteSearchFormItem);
//		RootPanel.get("main").add(textBoxFormItem);
//		RootPanel.get("main").add(button);

//		RootPanel.get("main").add(button);



		final TextBox letras = new TextBox();
		final TextBox numeros = new TextBox();

		final GroupFormItem<TextBox> group = new GroupFormItem<TextBox>("letras/numeros", new TextBox[] {letras, numeros}, "", false, true);
		group.addFormItemValidator(letras, new RegexpFormItemValidator("[a,b,c,d]", "digite somente letras"));
		group.addFormItemValidator(numeros, new RegexpFormItemValidator(RegexpFormItemValidator.SOMENTE_NUMEROS, "somente numeros"));

		Button button = new Button("teste");
		button.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				group.validate(new ValidatorActionAdapter() {
					public void onSuccess() {
						GWT.log("conteudo do group valido!", null);
					}
				});
			}
		});

		RootPanel.get("main").add(group);
		RootPanel.get("main").add(button);
	}

}
