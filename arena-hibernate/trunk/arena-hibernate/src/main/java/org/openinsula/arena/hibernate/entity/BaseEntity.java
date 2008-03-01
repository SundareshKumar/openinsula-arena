package org.openinsula.arena.hibernate.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.util.ObjectUtils;

/**
 * JPA/Hibernate Entity using field (not getter) annotations
 * @param <T> Id type. Must be a Number.
 * @author Eduardo R Danielli
 */
@MappedSuperclass
public abstract class BaseEntity<T extends Number> implements Serializable {

	@Id
	@GeneratedValue
	private T id;
	
	@Version
	private Integer version;
	
	public T getId() {
		return id;
	}
	
	public void setId(final T id) {
		this.id = id;
	}
	
	public void setVersion(final Integer version) {
		this.version = version;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	@Override
	public String toString() {
		return String.format("[%s: id= %s]", getClass().getName(), ObjectUtils.nullSafeToString(id));
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || !getClass().isAssignableFrom(obj.getClass())) {
			return false;
		}
		
		return ObjectUtils.nullSafeEquals(id, ((BaseEntity<?>) obj).id);
	}
	
	@Override
	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(id);
	}

}
