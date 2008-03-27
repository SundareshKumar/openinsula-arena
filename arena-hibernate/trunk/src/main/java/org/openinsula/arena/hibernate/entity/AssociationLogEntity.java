package org.openinsula.arena.hibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AssociationLogEntity<S, D> extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "source_fk", nullable = false)
	private S source;

	@ManyToOne(optional = false)
	@JoinColumn(name = "association_fk", nullable = false)
	private D association;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date exclusionDate;

	/**
	 * Hibernate only
	 */
	public AssociationLogEntity() {
		super();
	}

	public AssociationLogEntity(final S source, final D association) {
		super();
		this.source = source;
		this.association = association;
		this.exclusionDate = new Date();
	}

	public S getSource() {
		return source;
	}

	public D getAssociation() {
		return association;
	}

	public Date getExclusionDate() {
		return exclusionDate;
	}

}
