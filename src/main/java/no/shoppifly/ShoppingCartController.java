package no.shoppifly;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


import java.util.List;

@RestController()
public class ShoppingCartController {

    @Autowired
    private final CartService cartService;
    
    private MeterRegistry meterRegistry;
    
    private Map<String, Item> cartDatabase = new HashMap<>();
    
    public ShoppingCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "/cart/{id}")
    public Cart getCart(@PathVariable String id) {
        return cartService.getCart(id);
    }

    /**
     * Checks out a shopping cart. Removes the cart, and returns an order ID
     *
     * @return an order ID
     */
    @Timed
    @PostMapping(path = "/cart/checkout")
    public String checkout(@RequestBody Cart cart) {
        meterRegistry.counter("checkouts").increment();
        return cartService.checkout(cart);
    }

    /**
     * Updates a shopping cart, replacing it's contents if it already exists. If no cart exists (id is null)
     * a new cart is created.
     *
     * @return the updated cart
     */
    @Timed
    @PostMapping(path = "/cart")
    public Cart updateCart(@RequestBody Cart cart) {
        meterRegistry.counter("carts.count").increment(1);
        //fikk ikke til å få summen
        //meterRegistry.counter("carts.sum", checkout(cart)).increment(1);
            Gauge.builder("cart.value", cartDatabase,
            e -> e.values()
            .stream()
            .map(Item::getUnitPrice)
            .mapToDouble(Float::floatValue)
            .sum())
            .register(meterRegistry);

        return cartService.update(cart);
    }

    /**
     * return all cart IDs
     *
     * @return
     */
    @GetMapping(path = "/carts")
    public List<String> getAllCarts() {
        return cartService.getAllsCarts();
    }

    /*
        @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        // Verdi av total
        Gauge.builder("cart.count", cartDatabase,
                b -> b.values().size()).register(meterRegistry);

        Gauge.builder("cart.value", cartDatabase,
                b -> b.values()
                        .stream()
                        .map(Item::getUnitPrice)
                        .mapToDouble(Float::floatValue)
                        .sum())
                .register(meterRegistry);
    }
     */

}