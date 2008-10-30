package org.openinsula.arena.gwt.client.components.test.search.cliente;

import org.openinsula.arena.gwt.client.components.test.search.AbstractDetailsSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.AbstractSearchFormTemplate;
import org.openinsula.arena.gwt.client.components.test.search.GetValueAction;
import org.openinsula.arena.gwt.client.components.test.search.ViewToModelCallback;
import org.openinsula.arena.gwt.client.components.test.search.pessoa.Pessoa;
import org.openinsula.arena.gwt.client.components.test.search.pessoa.PessoaSearchForm;
import org.openinsula.arena.gwt.client.ui.form.FormBuilder;
import org.openinsula.arena.gwt.client.ui.form.FormFactory;
import org.openinsula.arena.gwt.client.ui.form.FormItem;
import org.openinsula.arena.gwt.client.ui.form.validator.NotNullFormItemValidator;
import org.openinsula.arena.gwt.client.ui.form.validator.RegexpFormItemValidator;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorAction;
import org.openinsula.arena.gwt.client.ui.form.validator.ValidatorActionAdapter;

import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ClienteDetailSearchForm extends AbstractDetailsSearchFormTemplate<Cliente> {

	private FormItem<PessoaSearchForm> pessoaSearchFormItem;

	private FormItem<TextBox> dataCadastroFormItem;

	private PessoaSearchForm pessoaSearchForm;

	private TextBox dataCadastroTextBox;

	public ClienteDetailSearchForm(AbstractSearchFormTemplate<Cliente> parent, HasFocus nextFocusableComponent) {
		super(parent, nextFocusableComponent);
	}

	@Override
	protected Widget buildForm() {
		dataCadastroTextBox = FormFactory.textBox();
		pessoaSearchForm = new PessoaSearchForm(dataCadastroTextBox);
		pessoaSearchForm.setEditionAllowed(true);
		pessoaSearchForm.setInsertionAllowed(true);

		pessoaSearchFormItem = new FormItem<PessoaSearchForm>("Nome", pessoaSearchForm);
		dataCadastroFormItem = new FormItem<TextBox>("Data do cadastro", dataCadastroTextBox);

		FormBuilder builder = new FormBuilder();
		builder.add(pessoaSearchFormItem);
		builder.add(dataCadastroFormItem);

		return builder.toPanel();
	}

	@Override
	protected void clear() {
		pessoaSearchForm.setText("");
		dataCadastroTextBox.setText("");
	}

	@Override
	protected HasFocus[] createFocusSequence() {
		return new HasFocus[] {
				pessoaSearchForm.getSearchForm(),
				dataCadastroTextBox
		};
	}

	@Override
	protected void modelToView(Cliente bean, boolean editionMode) {
		pessoaSearchForm.setText(bean.getPessoa().getNome());
		dataCadastroTextBox.setText(bean.getDataCadastro());

		focus(pessoaSearchForm.getSearchForm());
	}

	@Override
	protected void viewToModel(final Cliente editInstance, final ViewToModelCallback<Cliente> callback) {
		pessoaSearchForm.getEditInstance(new GetValueAction<Pessoa>() {
			public void processValue(Pessoa pessoa) {
				editInstance.setPessoa(pessoa);
				editInstance.setDataCadastro(dataCadastroTextBox.getText());
				callback.processValue(editInstance);
			}
		});
	}

	@Override
	protected void validateView(final ValidatorAction action) {
		pessoaSearchFormItem.validate(new ValidatorActionAdapter() {
			public void onSuccess() {
				dataCadastroFormItem.validate(action);
			}
		});
	}

	@Override
	protected void initValidators() {
		pessoaSearchFormItem.addFormItemValidator(new NotNullFormItemValidator());

		dataCadastroFormItem.addFormItemValidator(new NotNullFormItemValidator());
		dataCadastroFormItem.addFormItemValidator(new RegexpFormItemValidator(RegexpFormItemValidator.DATA, "data invalida"));

	}

}
