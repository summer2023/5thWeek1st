package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public BigDecimal getTotal(List<Product> products, List<OrderItem> items,BigDecimal subTotal) {
        for (Product product : products) {
            OrderItem curItem = findOrderItemByProduct(items, product);
            BigDecimal reducedPrice = product.getPrice()
                    .multiply(product.getDiscountRate())
                    .multiply(new BigDecimal(curItem.getCount()));

            subTotal = subTotal.subtract(reducedPrice);
        }
        return subTotal;
    }

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items);
        BigDecimal taxTotal = getTotal(products,items,subTotal).multiply(tax);
        BigDecimal grandTotal =getTotal(products,items,subTotal).add(taxTotal);

        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

//根据编码寻找某产品及其数量
    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }
//计算产品总价
    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);
            //计算每个产品的总价=价钱*数量
            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }
}
