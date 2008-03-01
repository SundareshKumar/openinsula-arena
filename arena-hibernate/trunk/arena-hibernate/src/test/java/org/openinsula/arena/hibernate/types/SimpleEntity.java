package org.openinsula.arena.hibernate.types;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.openinsula.arena.hibernate.entity.BaseEntity;
import org.openinsula.arena.lang.numbers.Money;

@Entity
public class SimpleEntity extends BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	private String name;

	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Type(type = MoneyTypes.BRAZIL)
	private Money salaryBrazil;
	
	@Type(type = MoneyTypes.US)
	private Money salaryUs;
	
	@Type(type = MoneyTypes.DEFAULT)
	private Money salary;

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Money getSalaryBrazil() {
		return salaryBrazil;
	}
	
	public void setSalaryBrazil(final Money salary) {
		this.salaryBrazil = salary;
	}

	public Money getSalaryUs() {
		return salaryUs;
	}

	public void setSalaryUs(final Money salaryUs) {
		this.salaryUs = salaryUs;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(final Money salary) {
		this.salary = salary;
	}
}
