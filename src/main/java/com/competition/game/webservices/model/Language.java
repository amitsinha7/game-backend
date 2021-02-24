package com.competition.game.webservices.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "language", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Language implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "number")
	private int number;

	@OneToMany(mappedBy = "language", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TaskStatus> tasks;

	public Language() {
		super();
	}

	public Language(@NotEmpty String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}

	public Language(@NotEmpty String name, int number, Set<TaskStatus> tasks) {
		super();
		this.name = name;
		this.number = number;
		this.tasks = tasks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Set<TaskStatus> getTasks() {
		return tasks;
	}

	public void setTasks(Set<TaskStatus> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Language [name=" + name + ", number=" + number + ", tasks=" + tasks + "]";
	}

}
