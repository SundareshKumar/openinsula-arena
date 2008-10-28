package org.openinsula.arena.gwt.client.components.test.search.pessoa;

import org.openinsula.arena.gwt.client.components.test.search.AbstractDetailsSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.AbstractSearchFormTemplate;
import org.openinsula.arena.gwt.client.ui.form.FormBuilder;
import org.openinsula.arena.gwt.client.ui.form.FormFactory;
import org.openinsula.arena.gwt.client.ui.form.FormItem;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorAction;

import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class PessoaDetailSearchForm extends AbstractDetailsSearchFormTemplate<Pessoa> {

	private TextBox nomeTextBox;

	private TextBox cpfTextBox;

	private TextBox cidadeTextBox;

	public PessoaDetailSearchForm(AbstractSearchFormTemplate<Pessoa> parent, HasFocus nextFocusableComponent) {
		super(parent, nextFocusableComponent);
	}

	@Override
	protected Widget buildForm() {
		nomeTextBox = FormFactory.textBox();
		cpfTextBox = FormFactory.textBox();
		cidadeTextBox = FormFactory.textBox();

		FormBuilder builder = new FormBuilder();
		builder.add(new FormItem<TextBox>("Nome", nomeTextBox));
		builder.add(new FormItem<TextBox>("Cpf", cpfTextBox));
		builder.add(new FormItem<TextBox>("Cidade", cidadeTextBox));

		return builder.toPanel();
	}

	@Override
	protected void clear() {
		nomeTextBox.setText("");
		cpfTextBox.setText("");
		cidadeTextBox.setText("");
	}

	@Override
	protected HasFocus[] createFocusSequence() {
		return new HasFocus[] {
				nomeTextBox,
				cpfTextBox,
				cidadeTextBox
		};
	}

	@Override
	protected void modelToView(Pessoa bean, boolean editionMode) {
		nomeTextBox.setText(bean.getNome());
		cpfTextBox.setText(bean.getCpf());
		cidadeTextBox.setText(bean.getCidade());

		focus(nomeTextBox);
	}

	@Override
	protected Pessoa viewToModel(Pessoa editInstance) {
		editInstance.setNome(nomeTextBox.getText());
		editInstance.setCpf(cpfTextBox.getText());
		editInstance.setCidade(cidadeTextBox.getText());
		return editInstance;
	}

	@Override
	protected void validateView(ValidatorAction action) {
		action.onSuccess();
	}

	@Override
	protected void initValidators() {
	}

}
