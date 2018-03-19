package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

//import be.vdab.valueObjects.OrderDetail;

@Entity
@Table(name = "products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String scale;
	private String description;
	private long quantityInStock;
	private long quantityInOrder;
	private BigDecimal buyPrice;
	@Version
	private long version;
	
	public Product(String name, String scale, String description, long quantityInStock, long quantityInOrder,
			BigDecimal buyPrice) {
		this.name = name;
		this.scale = scale;
		this.description = description;
		this.quantityInStock = quantityInStock;
		this.quantityInOrder = quantityInOrder;
		this.buyPrice = buyPrice;
	}
	
	protected Product() {
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Product)) {
			return false;
		}
		Product otherProduct = (Product) obj;
		return name.equalsIgnoreCase(otherProduct.name);
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getScale() {
		return scale;
	}

	public String getDescription() {
		return description;
	}

	public long getQuantityInStock() {
		return quantityInStock;
	}
	
	public void setQuantityInStock(long quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public long getQuantityInOrder() {
		return quantityInOrder;
	}
	
	public void setQuantityInOrder(long quantityInOrder) {
		this.quantityInOrder = quantityInOrder;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	
	public void updateProductStock(long quantityOrdered, String id, StringBuilder notShipped) {
		quantityInStock = quantityInStock - quantityOrdered;
		quantityInOrder = quantityInOrder - quantityOrdered;
		if (quantityInStock < 0 || quantityInOrder < 0) {
			notShipped.append(id + ", ");
		}
	}
}
