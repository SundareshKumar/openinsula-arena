package org.openinsula.arena.echo2.component.model.container.vulcano;

import java.util.List;

import org.openinsula.arena.echo2.component.model.SortableTableModel;
import org.openinsula.vulcano.orm.dao.query.DaoQuery;

public class DaoQueryContainerTableModel<T> extends TransactionInvokerContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

	private DaoQuery daoQuery;

	private String sortedProperty;

	private boolean order;

	@Override
	public void updateTableItems() {
		if (daoQuery == null) {
			logger.warn("Cannot update a DaoQueryContainerTableModel with a null DaoQuery.");
			return;
		}

		if (this instanceof SortableTableModel && sortedProperty != null && sortedProperty.isEmpty()) {
			StringBuilder parameter = new StringBuilder(sortedProperty);

			if (order) {
				parameter.append(" asc");
			}
			else {
				parameter.append(" desc");
			}

			daoQuery.setOrderParameters(new String[] { parameter.toString() });
		}

		int firstResult = getCurrentPage() * getPageCount();

		daoQuery.setFirstResult(firstResult);
		daoQuery.setLimit(getPageSize());

		List<T> list = getDaoCommandTransactionFacade().<T> find(daoQuery);
		this.setItems(list);
	}

	public DaoQuery getDaoQuery() {
		return daoQuery;
	}

	public void setDaoQuery(DaoQuery daoQuery) {
		this.daoQuery = daoQuery;
	}

	public String getSortedProperty() {
		return sortedProperty;
	}

	public void setSortedProperty(String sortedProperty) {
		this.sortedProperty = sortedProperty;
	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

}
