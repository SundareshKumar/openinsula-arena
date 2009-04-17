package org.openinsula.arena.gwt.client.ui.form.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class DefaultGroupValidatorChainImpl<W extends Widget> implements ValidatorChain<W> {

	private List<FormItemValidator<W>> chainList;

	private Iterator<FormItemValidator<W>> chainIterator;

	private Iterator<W> widgetIterator;

	private List<W> widgetList;

	private Iterator<W> widgetIterator() {
		if (widgetIterator == null) {
			widgetIterator = widgetList().iterator();
		}
		return widgetIterator;
	}

	private List<W> widgetList() {
		if (widgetList == null) {
			widgetList = new ArrayList<W>();
		}
		return widgetList;
	}

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

	private void resetWidgetIterator() {
		widgetIterator = null;
	}

	private FormItemValidator<W> nextValidator() {
		Iterator<FormItemValidator<W>> iterator = chainIterator();
		return iterator.hasNext() ? iterator.next() : null;
	}

	private W nextWidget() {
		Iterator<W> iterator = widgetIterator();
		return iterator.hasNext() ? iterator.next() : null;
	}

	public void doChain(W widget, ValidatorAction action) {
		FormItemValidator<W> nextValidator = nextValidator();
		W nextWidget = nextWidget();
		if (nextValidator != null && nextWidget != null) {
			nextValidator.validate(nextWidget, this, action);
		} else {
			GWT.log("nao foi encontrado um novo elemento para o doChain()", null);
		}
	}

	public boolean isLastNode() {
		return !chainIterator().hasNext();
	}

	public void validate(W widget, ValidatorAction action) {
		resetChainIterator();
		resetWidgetIterator();
		if (chainList().isEmpty()) {
			GWT.log("chainList Vazio... executando o action, tipo do action: " + action.getClass().getName(), null);
			action.onSuccess();
		} else {
			GWT.log("delegando a execucao para o proximo node", null);
			doChain(widget, action);
		}
	}

	public void addValidator(W widget, FormItemValidator<W> validator) {
		chainList().add(validator);
		widgetList().add(widget);
	}

	public void clearValidator(W widget) {
		chainList().clear();
		widgetList().clear();
	}

	public void removeValidator(FormItemValidator<W> validator) {
		chainList().remove(validator);
	}

	public int size() {
		return chainList.size();
	}

}
