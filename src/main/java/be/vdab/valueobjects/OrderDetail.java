package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Product;

@Embeddable
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private long quantityOrdered;
	private BigDecimal priceEach;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "productId")
	private Product product;
	
	public OrderDetail(long quantityOrdered, BigDecimal priceEach) {
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
	}

	protected OrderDetail() {
	}

	public long getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(long quantityOrdered) {
		if (!isQuantityValid(quantityOrdered)) {
			throw new IllegalArgumentException();
		}
		this.quantityOrdered = quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}
	
	public boolean isQuantityValid(long quantityOrdered) {
		return quantityOrdered > 0;
	}
	
	public boolean isPriceValid(BigDecimal priceEach) {
		return priceEach != null && priceEach.compareTo(BigDecimal.ZERO) > 0;
	}

	public Product getProduct() {
		return product;
	}
	
	public void updateProduct(String id, StringBuilder notShipped) {
		product.updateProductStock(quantityOrdered, id, notShipped);
	}
	
	public BigDecimal getValue() {
		return priceEach.multiply(BigDecimal.valueOf(quantityOrdered));
	}
	
	@Override
	public int hashCode() {
		return product.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof OrderDetail)) {
			return false;
		}
		OrderDetail other = (OrderDetail) obj;
		if (product == null) {
			if (other.product != null) {
				return false;
			}
		} else if (!product.equals(other.product)) {
			return false;
		}
		return true;
	}
	
}
