package unitTesting;

import com.example.project4.Pepperoni;
import com.example.project4.Size;
import com.example.project4.Topping;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for Pepperoni class
 * @author Wesam Saleh, Najibullah Assadullah
 */

class PepperoniTest {
    @Test
    void price() {
        ArrayList<Topping> topping = new ArrayList<>();

        Pepperoni defaultPizza = new Pepperoni();
        assertTrue(defaultPizza.price() == 8.99);

        topping.remove(Topping.Pepperoni);
        Pepperoni pizzaWithoutToppings = new Pepperoni(topping, Size.Small);
        assertTrue(pizzaWithoutToppings.price() == 8.99);
        topping.add(Topping.Pepperoni);


        topping.add(Topping.Beef);
        Pepperoni pizzaSmall = new Pepperoni(topping, Size.Small);
        assertTrue(pizzaSmall.price() == 10.48);

        Pepperoni pizzaMedium = new Pepperoni(topping, Size.Medium);
        assertTrue(pizzaMedium.price() == 12.48);

        Pepperoni pizzaLarge = new Pepperoni(topping, Size.Large);
        assertTrue(pizzaLarge.price() == 14.48);

        topping.add(Topping.Cheese);
        topping.add(Topping.Pineapple);
        topping.add(Topping.BlackOlives);
        topping.add(Topping.Chicken);
        topping.add(Topping.GreenPepper);
        Pepperoni pizzaMaxToppings = new Pepperoni(topping, Size.Small);
        assertTrue(pizzaMaxToppings.price() == 17.93);
    }
}
