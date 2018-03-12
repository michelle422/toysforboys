package be.vdab.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.enums.Status;
import be.vdab.valueObjects.OrderDetail;

@Entity
@Table(name = "orders")
@NamedEntityGraph(name = Order.MET_CUSTOMER, attributeNodes = @NamedAttributeNode("customer"))
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String MET_CUSTOMER = "Order.metCustomer";
	public static final String MET_PRODUCTS = "Order.metProducts";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate orderDate;
	private LocalDate requiredDate;
	private LocalDate shippedDate;
	private String comments;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customerId")
	private Customer customer;
	@Enumerated(EnumType.STRING)
	private Status status;
	@ElementCollection
	@CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderId"))
	private Set<OrderDetail> orderDetails;
	@Version
	private int version;
//	@OneToMany(mappedBy = "order")
//	private Set<OrderDetail> orderDetails;
//	@ManyToMany
//	@JoinTable(name = "orderdetails", joinColumns = @JoinColumn(name="orderId"), 
//	inverseJoinColumns =  @JoinColumn(name = "productId"))
//	private Set<Product> products;
	
	public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String comments) {
		setOrderDate(orderDate);
		setRequiredDate(requiredDate);
		setShippedDate(shippedDate);
		this.comments = comments;
//		products = new LinkedHashSet<>();
		orderDetails = new LinkedHashSet<>();
//		orderDetails = new LinkedHashSet<>();
	}

	protected Order() {
	}

	public long getId() {
		return id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(LocalDate requiredDate) {
		this.requiredDate = requiredDate;
	}

	public LocalDate getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = LocalDate.now();
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

//	public Set<Product> getProducts() {
//		return Collections.unmodifiableSet(products);
//	}
//	
//	public void add(Product product) {
//		products.add(product);
//		if (!product.getOrders().contains(this)) {
//			product.add(this);
//		}
//	}
//	
//	public void remove(Product product) {
//		products.remove(product);
//		if (product.getOrders().contains(this)) {
//			product.remove(this);
//		}
//	}

	public Set<OrderDetail> getOrderDetail() {
		return Collections.unmodifiableSet(orderDetails);
	}
	
	public void add(OrderDetail orderDetail) {
		orderDetails.add(orderDetail);
	}
	
	public void remove(OrderDetail orderDetail) {
		orderDetails.remove(orderDetail);
	}
}
