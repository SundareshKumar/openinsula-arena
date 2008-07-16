package org.openinsula.arena.echo2.component.model.container.vulcano;

import org.openinsula.vulcano.orm.dao.query.DaoQuery;
import org.openinsula.vulcano.orm.dao.query.builder.DaoSelect;
import org.openinsula.vulcano.orm.dao.query.spec.Spec;

public class SpecContainerTableModel<T> extends DaoQueryContainerTableModel<T> {
	private static final long serialVersionUID = 1L;

	private Spec spec;

	private Class<T> klazz;

	public SpecContainerTableModel(Class<T> klazz) {
		super();
		this.klazz = klazz;
	}

	public SpecContainerTableModel(Spec spec, boolean update, Class<T> klazz) {
		super();
		this.setSpec(spec, update);
		this.klazz = klazz;
	}

	@Override
	public void updateTableItems() {
		DaoQuery<T> query = null;
		
		if (spec != null) {
			query = new DaoSelect<T>().all().from(klazz).spec(spec).toQuery();
		} else {
			query = new DaoSelect<T>().all().from(klazz).toQuery();
		}
		
		
		this.setDaoQuery(query, false);
		
		super.updateTableItems();
	}

	public void setSpec(Spec spec, boolean update) {
		this.spec = spec;

		if (update) {
			updateTableItems();
		}
	}

	public void setSpec(Spec spec) {
		this.setSpec(spec, true);
	}

	public Spec getSpec() {
		return spec;
	}

}
