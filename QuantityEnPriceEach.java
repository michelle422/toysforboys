package be.vdab.valueObjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class QuantityEnPriceEach implements Serializable {
	private static final long serialVersionUID = 1L;
	private long quantityOrdered;
	private BigDecimal priceEach;
	
	public QuantityEnPriceEach(long quantityOrdered, BigDecimal priceEach) {
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
	}

	protected QuantityEnPriceEach() {
	}

	public long getQuantityOrdered() {
		return quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}
}
