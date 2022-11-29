package no.shoppifly;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class ShoppingCartController {

    @Autowired
    private final CartService cartService;

    @Autowired
    private MeterRegistry meterRegistry;



    public ShoppingCartController(CartService cartService, MeterRegistry meterRegistry) {
        this.cartService = cartService;
        this.meterRegistry = meterRegistry;
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
        meterRegistry.counter("carts.total").increment();
        return cartService.update(cart);
    }

    /**
     * return all cart IDs
     *
     * @return
     */
    @Timed
    @GetMapping(path = "/carts")
    public List<String> getAllCarts() {
        //Gauge gauge = Gauge.builder("carts.total", getAllCarts(), List::size).register(meterRegistry);
        //Gauge.builder("carts.count",
        List<String> list= cartService.getAllsCarts();
        meterRegistry.counter("carts.total").increment(list.size());

          //      e -> cartService.getAllsCarts().size()).register(meterRegistry);
        return list;
    }




    /*
    carts - antall handlekurver på et tidspunkt. kan gå opp og ned dersom de sjekker ut.
    -carts value - total sum kan gå opp og ned
    checkouts - totalt handlevoger sjekker ut
    checkout latency -. gjennomstinleig responstid på chekckoit metoden






    implements ApplicationListener<ApplicationReadyEvent>



        @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        // Verdi av total
        Gauge.builder("account_count", getAllCarts(),
                b -> b.values().size()).register(meterRegistry);

        // Denne meter-typen "Gauge" rapporterer hvor mye penger som totalt finnes i banken
        Gauge.builder("bank_sum", theBank,
                b -> b.values()
                        .stream()
                        .map(Account::getBalance)
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum())
                .register(meterRegistry);
    }
     */
}