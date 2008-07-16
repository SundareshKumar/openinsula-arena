package org.openinsula.arena.echo2.component.model.container.vulcano;

import java.util.Comparator;

import org.apache.commons.beanutils.BeanUtils;
import org.openinsula.arena.echo2.component.model.SortableTableModel;
import org.openinsula.vulcano.orm.dao.query.spec.Spec;

public abstract class SortableSpecContainerTableModel<T> extends SpecContainerTableModel<T> implements
		SortableTableModel {
	private static final long serialVersionUID = 1L;

	public SortableSpecContainerTableModel(Class<T> klazz) {
		super(klazz);
	}

	public SortableSpecContainerTableModel(Spec spec, boolean update, Class<T> klazz) {
		super(spec, update, klazz);
	}

	public abstract String getSortablePropertyName(int column);

	public void sortTable(int column, boolean order) {
		final String value = getSortablePropertyName(column);

		sortItems(column, new Comparator<T>() {
			public int compare(T o1, T o2) {

				try {
					String indexedProperty1 = BeanUtils.getIndexedProperty(o1, value);
					String indexedProperty2 = BeanUtils.getIndexedProperty(o2, value);

					return indexedProperty1.compareTo(indexedProperty2);
				}
				catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
	}

}
