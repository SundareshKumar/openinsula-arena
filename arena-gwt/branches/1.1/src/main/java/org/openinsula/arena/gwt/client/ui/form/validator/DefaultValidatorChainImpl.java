package org.openinsula.arena.gwt.client.ui.form.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public class DefaultValidatorChainImpl<W extends Widget> implements ValidatorChain<W> {

	private List<FormItemValidatorNew<W>> chainList;

	private Iterator<FormItemValidatorNew<W>> chainIterator;

	private List<FormItemValidatorNew<W>> chainList() {
		if (chainList == null) {
			chainList = new ArrayList<FormItemValidatorNew<W>>();
		}
		return chainList;
	}

	private Iterator<FormItemValidatorNew<W>> chainIterator() {
		if (chainIterator == null) {
			chainIterator = chainList().iterator();
		}
		return chainIterator;
	}

	private void resetChainIterator() {
		chainIterator = null;
	}

	private FormItemValidatorNew<W> nextValidator() {
		Iterator<FormItemValidatorNew<W>> iterator = chainIterator();
		return iterator.hasNext() ? iterator.next() : null;
	}

	public <T> void doChain(W widget) {
		FormItemValidatorNew<W> nextValidator = nextValidator();
		if (nextValidator != null) {
			nextValidator.validate(widget, this);
		}
	}

	public void validate(W widget) {
		resetChainIterator();
		FormItemValidatorNew<W> nextValidator = nextValidator();
		if (nextValidator != null) {
			nextValidator.validate(widget, this);
		}
	}

	public void addValidator(FormItemValidatorNew<W> validator) {
		chainList().add(validator);
	}

	public void removeValidator(FormItemValidatorNew<W> validator) {
		chainList().remove(validator);
	}

	public int size() {
		return chainList.size();
	}

}
