package org.openinsula.arena.gwt.client.ui.form.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class DefaultValidatorChainImpl<W extends Widget> implements ValidatorChain<W> {

	private List<FormItemValidator<W>> chainList;

	private Iterator<FormItemValidator<W>> chainIterator;

	private List<FormItemValidator<W>> chainList() {
		if (chainList == null) {
			chainList = new ArrayList<FormItemValidator<W>>();
		}
		return chainList;
	}

	private Iterator<FormItemValidator<W>> chainIterator() {
		if (chainIterator == null) {
			chainIterator = chainList().iterator();
		}
		return chainIterator;
	}

	private void resetChainIterator() {
		chainIterator = null;
	}

	private FormItemValidator<W> nextValidator() {
		Iterator<FormItemValidator<W>> iterator = chainIterator();
		return iterator.hasNext() ? iterator.next() : null;
	}

	public void doChain(W widget, ValidatorAction action) {
		FormItemValidator<W> nextValidator = nextValidator();
		if (nextValidator != null) {
			nextValidator.validate(widget, this, action);
		} else {
			GWT.log("nao foi encontrado um novo elemento para o doChain()", null);
		}
	}

	public boolean isLastNode() {
		return !chainIterator().hasNext();
	}

	public void validate(W widget, ValidatorAction action) {
		resetChainIterator();
		if (chainList().isEmpty()) {
			GWT.log("chainList Vazio... executando o action, tipo do action: " + action.getClass().getName(), null);
			action.onSuccess();
		} else {
			GWT.log("delegando a execucao para o proximo node", null);
			FormItemValidator<W> nextValidator = nextValidator();
			if (nextValidator != null) {
				nextValidator.validate(widget, this, action);
			}
		}
	}

	public void addValidator(W widget, FormItemValidator<W> validator) {
		chainList().add(validator);
	}

	public void clearValidator(W widget) {
		chainList().clear();
	}

	public void removeValidator(FormItemValidator<W> validator) {
		chainList().remove(validator);
	}

	public int size() {
		return chainList.size();
	}

}
