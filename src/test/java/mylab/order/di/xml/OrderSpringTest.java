package mylab.order.di.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private OrderService orderService;

    @Test
    public void testShoppingCart() {
        assertNotNull("ShoppingCart 빈(Bean)은 Null이 아니어야 합니다.", shoppingCart);

        assertEquals("장바구니에는 총 2개의 상품이 있어야 합니다.", 2, shoppingCart.getProducts().size());
        assertEquals("첫 번째 상품은 '노트북'이어야 합니다.", "노트북", shoppingCart.getProducts().get(0).getName());
        assertEquals("두 번째 상품은 '스마트폰'이어야 합니다.", "스마트폰", shoppingCart.getProducts().get(1).getName());
    }

    @Test
    public void testOrderService() {
        assertNotNull("OrderService 빈(Bean)은 Null이 아니어야 합니다.", orderService);
        
        ShoppingCart injectedCart = orderService.getShoppingCart();
        assertNotNull("OrderService에 ShoppingCart가 올바르게 주입되어야 합니다.", injectedCart);

        double totalPrice = orderService.calculateOrderTotal();
        assertEquals("총 주문 가격은 950,000원이어야 합니다.", 950000, totalPrice, 0.0);
    }
}