package org.openinsula.arena.echo2.component.model.container.vulcano;

import org.openinsula.arena.echo2.component.model.container.impl.UpdatableContainerTableModel;
import org.openinsula.vulcano.core.command.transaction.TransactionInvoker;
import org.openinsula.vulcano.orm.command.transaction.DaoCommandTransactionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public abstract class TransactionInvokerContainerTableModel<T> extends UpdatableContainerTableModel<T> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TransactionInvoker transactionInvoker;
	
	@Autowired
	private DaoCommandTransactionFacade daoCommandTransactionFacade;

	public TransactionInvoker getTransactionInvoker() {
		return transactionInvoker;
	}

	public void setTransactionInvoker(TransactionInvoker transactionInvoker) {
		this.transactionInvoker = transactionInvoker;
	}

	public DaoCommandTransactionFacade getDaoCommandTransactionFacade() {
		return daoCommandTransactionFacade;
	}

	public void setDaoCommandTransactionFacade(DaoCommandTransactionFacade daoCommandTransactionFacade) {
		this.daoCommandTransactionFacade = daoCommandTransactionFacade;
	}

}
