package org.openinsula.arena.hibernate.entity;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class LifeCycleEntity<T extends Number> extends BaseEntity<T> {

	public static Comparator<LifeCycleEntity<?>> COMPARE_BY_REMOVED_DATE_DESC = new Comparator<LifeCycleEntity<?>>() {

		@Override
		public int compare(LifeCycleEntity<?> o1, LifeCycleEntity<?> o2) {
			Date exclusionDate1 = o1.getExclusionDate();
			Date exclusionDate2 = o2.getExclusionDate();

			if (exclusionDate1 == null && exclusionDate2 == null) {
				return 0;
			}

			if (exclusionDate1 == null) {

				if (exclusionDate2 == null) {
					return 0;
				}

				return -1;

			}
			else if (exclusionDate2 == null) {
				return 1;
			}

			return exclusionDate2.compareTo(exclusionDate1); // desc
		}

	};

	private final Date creationDate;

	private Date exclusionDate;

	public LifeCycleEntity() {
		creationDate = new Date();
	}

	public final Date getCreationDate() {
		return creationDate;
	}

	public final Date getExclusionDate() {
		return exclusionDate;
	}

	public final boolean isRemoved() {
		return exclusionDate != null;
	}

	public final void remove() {
		exclusionDate = new Date();
	}

}
