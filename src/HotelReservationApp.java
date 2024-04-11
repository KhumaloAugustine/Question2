import com.hotelReservationSystem.controller.HotelController;
import com.hotelReservationSystem.service.HotelService;
import com.hotelReservationSystem.service.InMemoryHotelService;
import com.hotelReservationSystem.view.HotelView;

public class HotelReservationApp {

    public static void main(String[] args) {
        // Create an object to handle interactions with the hotel reservation service
        HotelService hotelService = new InMemoryHotelService();

        // Create an object to handle user interaction and display information
        HotelView view = new HotelView();

        // Create an object to manage the overall reservation process (uses the service and view)
        HotelController controller = new HotelController(hotelService, view);

        // Start the reservation system by calling the controller's run method
        controller.run();
    }
}