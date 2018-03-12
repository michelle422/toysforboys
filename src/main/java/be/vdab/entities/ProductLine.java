package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "productlines")
public class ProductLine implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	@Version
	private int version;
	@OneToMany(mappedBy = "productLine")
	private Set<Product> products;
	
	public ProductLine(String name, String description) {
		this.name = name;
		this.description = description;
		products = new LinkedHashSet<>();
	}

	protected ProductLine() {
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Set<Product> getProducts() {
		return Collections.unmodifiableSet(products);
	}
	
}
