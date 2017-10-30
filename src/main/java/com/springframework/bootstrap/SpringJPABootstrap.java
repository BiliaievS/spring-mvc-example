package com.springframework.bootstrap;

import com.springframework.domain.*;
import com.springframework.enums.OrderStatus;
import com.springframework.services.ProductService;
import com.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sbiliaiev on 01/09/17.
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;
    private UserService userService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadUsersAndCustomers();
        loadCarts();
        loadOrderHistory();
    }

    private void loadProducts() {
        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("15.99"));
        product1.setImageUrl("http://example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("5.99"));
        product2.setImageUrl("http://example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setId(3);
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("19.99"));
        product3.setImageUrl("http://example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setId(4);
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://example.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setId(5);
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("6.99"));
        product5.setImageUrl("http://example.com/product5");
        productService.saveOrUpdate(product5);

        Product product6 = new Product();
        product6.setId(6);
        product6.setDescription("Product 6");
        product6.setPrice(new BigDecimal("6.99"));
        product6.setImageUrl("http://example.com/product5");
        productService.saveOrUpdate(product6);
    }

    private void loadUsersAndCustomers() {
        User user1 = new User();
        user1.setUserName("nberson");
        user1.setPassword("password");

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Nick");
        customer1.setLastName("Berson");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddress1("1 Main St");
        customer1.getBillingAddress().setAddress2("23 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZipCode("33101");
        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhoneNumber("305.333.0101");
        user1.setCustomer(customer1);
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUserName("mkern");
        user2.setPassword("pas2word");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Mark");
        customer2.setLastName("Kern");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddress1("1 Main St");
        customer2.getBillingAddress().setCity("Warsaw");
        customer2.getBillingAddress().setState("Poland");
        customer2.getBillingAddress().setZipCode("28996");
        customer2.setEmail("kern.m@gmail.com");
        customer2.setPhoneNumber("305.883.0101");
        user2.setCustomer(customer2);
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUserName("sblack");
        user3.setPassword("pas7word");

        Customer customer3 = new Customer();
        customer3.setFirstName("Sanny");
        customer3.setLastName("Black");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddress1("2 Summer beach");
        customer3.getBillingAddress().setCity("Miami");
        customer3.getBillingAddress().setState("Florida");
        customer3.getBillingAddress().setZipCode("33896");
        customer3.setEmail("sanbk@spring.com");
        customer3.setPhoneNumber("305.426.9832");

        user3.setCustomer(customer3);
        userService.saveOrUpdate(user3);
    }

    private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user ->{
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });
        });
    }

    private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
    }
}
