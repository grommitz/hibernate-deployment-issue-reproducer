package com.github.grommitz;

import jakarta.persistence.*;

@Entity
public class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PersonEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
