import com.hotelReservationSystem.controller.HotelController;
import com.hotelReservationSystem.service.HotelService;
import com.hotelReservationSystem.service.InMemoryHotelService;
import com.hotelReservationSystem.view.HotelView;

public class HotelReservationApp {

    public static void main(String[] args) {
        HotelService hotelService = new InMemoryHotelService();

        HotelView view = new HotelView();
        HotelController controller = new HotelController(hotelService, view);

        controller.run();
    }
}