package org.openinsula.arena.echo2.component.model.container.vulcano;

import java.util.List;

import org.openinsula.arena.echo2.component.model.container.impl.UpdatableContainerTableModel;
import org.openinsula.vulcano.core.command.invoker.CommandInvoker;
import org.openinsula.vulcano.orm.command.factory.CommandFactory;
import org.openinsula.vulcano.orm.dao.query.DaoQuery;
import org.springframework.beans.factory.annotation.Autowired;

public class DaoQueryContainerTableModel<T> extends UpdatableContainerTableModel<T> {

	private static final long serialVersionUID = 1L;

	@Autowired
	protected CommandInvoker commandInvoker;

	private DaoQuery<T> daoQuery;

	/**
	 * This method will run the daoQuery in the CommandInvoker specified and
	 * update the tableModel items.
	 */
	@Override
	public void updateTableItems() {
		if (commandInvoker == null) {
			logger.warn("Cannot update a DaoQueryContainerTableModel with a null CommandInvoker.");
			return;
		}

		if (daoQuery == null) {
			logger.warn("Cannot update a DaoQueryContainerTableModel with a null DaoQuery.");
			return;
		}

		final List<T> list = (List<T>) commandInvoker.invoke(CommandFactory.<T> newFind(daoQuery));
		setItems(list);
	}

	/**
	 * @param daoQuery
	 * @param update If true runs the method updateTableItems()
	 */
	public void setDaoQuery(DaoQuery<T> daoQuery, boolean update) {
		this.daoQuery = daoQuery;
		if (update) {
			updateTableItems();
		}
	}

	public DaoQuery<T> getDaoQuery() {
		return daoQuery;
	}

	public void setCommandInvoker(CommandInvoker commandInvoker) {
		this.commandInvoker = commandInvoker;
	}

	public CommandInvoker getCommandInvoker() {
		return commandInvoker;
	}

}
