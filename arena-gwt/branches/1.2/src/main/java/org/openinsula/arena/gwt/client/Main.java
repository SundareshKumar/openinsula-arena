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
