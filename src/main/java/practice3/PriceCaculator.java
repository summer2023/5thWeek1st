package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {
    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public PriceCaculator(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(0.1);
    }

    public BigDecimal getTotal(List<OrderLineItem> orderLineItemList) {
        BigDecimal subTotal = new BigDecimal(0);
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
        return subTotal;
    }
    public BigDecimal SubtractDiscounts(BigDecimal subTotal,List<BigDecimal> discounts) {
        for (BigDecimal discount : discounts) {
            subTotal = subTotal.subtract(discount);
        }
        return subTotal;
    }
    public BigDecimal calculateTax(BigDecimal subTotal,BigDecimal tax) {
        BigDecimal taxsubTotal = subTotal.multiply(this.tax);
        return taxsubTotal;
    }
    public BigDecimal grandTotal(BigDecimal subTotal,BigDecimal tax) {
        BigDecimal grandTotal = subTotal.add(tax);
        return grandTotal;
    }
}
