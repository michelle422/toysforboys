package be.vdab.valueObjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Order;
import be.vdab.entities.Product;

@Embeddable
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String MET_PRODUCT = "OrderDetail.metProduct";
	private long quantityOrdered;
	private BigDecimal priceEach;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "orderId")
	private Order order;
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

	public void setPriceEach(BigDecimal priceEach) {
		if (!isPriceValid(priceEach)) {
			throw new IllegalArgumentException();
		}
		this.priceEach = priceEach;
	}
	
	public boolean isQuantityValid(long quantityOrdered) {
		return quantityOrdered > 0;
	}
	
	public boolean isPriceValid(BigDecimal priceEach) {
		return priceEach != null && priceEach.compareTo(BigDecimal.ZERO) > 0;
	}
}
