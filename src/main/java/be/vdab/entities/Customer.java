package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.valueObjects.Adres;

@Entity
@Table(name = "customers")
@NamedEntityGraph(name = Customer.MET_COUNTRY, attributeNodes = @NamedAttributeNode("country"))
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String MET_COUNTRY = "Customer.metCountry";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Embedded
	private Adres adres;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "countryId")
	private Country country;
	@Version
	private int version;
	@OneToMany(mappedBy = "customer")
	private Set<Order> orders;
	
	public Customer(String name, Adres adres, Country country, int version) {
		this.name = name;
		this.adres = adres;
		this.version = version;
		orders = new LinkedHashSet<>();
	}

	protected Customer() {
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Adres getAdres() {
		return adres;
	}

	public Country getCountry() {
		return country;
	}

	public int getVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer otherCustomer = (Customer) obj;
		return name.equalsIgnoreCase(otherCustomer.name);
	}

	public Set<Order> getOrders() {
		return Collections.unmodifiableSet(orders);
	}
	
}
