import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        restaurant = createMockRestaurant(LocalTime.parse("09:30:00"), LocalTime.parse("23:00:00"));
        boolean isRestaurantOpen = restaurant.isRestaurantOpen(LocalTime.parse("12:00:00"));
        assertTrue(isRestaurantOpen, "Restaurant should be open at 12:00:00 PM");
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        restaurant = createMockRestaurant(LocalTime.parse("09:30:00"), LocalTime.parse("23:00:00"));
        boolean isRestaurantOpen = restaurant.isRestaurantOpen(LocalTime.parse("00:00:00"));
        assertFalse(isRestaurantOpen, "Restaurant should be closed at 00:00:00 AM");
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_at_opening_time(){
        restaurant = createMockRestaurant(LocalTime.parse("10:00:00"), LocalTime.parse("23:00:00"));
        boolean isRestaurantOpen = restaurant.isRestaurantOpen(LocalTime.parse("10:00:00"));
        assertTrue(isRestaurantOpen, "Restaurant should be open at 10:00:00 AM");
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_at_closing_time(){
        restaurant = createMockRestaurant(LocalTime.parse("09:30:00"), LocalTime.parse("22:30:00"));
        boolean isRestaurantOpen = restaurant.isRestaurantOpen(LocalTime.parse("22:30:00"));
        assertFalse(isRestaurantOpen, "Restaurant should be closed at 10:30:00 PM");
    }

    //Mocking the Restaurant class to create a restaurant with custom opening and closing time
    private Restaurant createMockRestaurant(LocalTime openingTime, LocalTime closingTime) {
        Restaurant mockRestaurant = new Restaurant("Mock Restaurant", "Mock Location", openingTime, closingTime);
        mockRestaurant.addToMenu("Item 1", 100);
        mockRestaurant.addToMenu("Item 2", 200);
        mockRestaurant.addToMenu("Item 3", 300);
        return mockRestaurant;
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}