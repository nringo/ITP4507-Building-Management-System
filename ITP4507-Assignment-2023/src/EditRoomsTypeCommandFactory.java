import java.util.ArrayList;
import java.util.HashMap;

public class EditRoomsTypeCommandFactory extends CommandFactory {

    @Override
    public Command create() throws Exception {

        System.out.print("Building No.:");
        String id = Assignment.sc.next();

        Building building = BuildingItemsUtil.getBuildingItemByID(buildings, Integer.parseInt(id));
        building.pringBuilding();
        System.out.println("");

        System.out.println("Please enter command: [a|d|m]\n"
                + "a = add room, d = delete room, m = modify room");
        String type = Assignment.sc.next();

        EditRoomsTypeCommand erac = new EditRoomsTypeCommand(caretaker, building, Integer.parseInt(id), type);
        erac.setBuildingItems(buildings);
        erac.setCaretaker(caretaker);
        return erac.create();
    }

}

class EditRoomsTypeCommand extends EditRoomsTypeCommandFactory {

    private Caretaker caretaker;
    private Building building;
    private int id;
    private String type;

    public EditRoomsTypeCommand(Caretaker caretaker, Building building, int id, String type) {
        this.caretaker = caretaker;
        this.building = building;
        this.id = id;
        this.type = type;
    }

    public Command create() throws Exception {
        HashMap<String, String> editRoomsFactoryMap = new HashMap<String, String>() {
            {
                put("a", "AddRoomCommandFactory");
                put("d", "DeleteRoomCommandFactory");
                put("m", "ModifyRoomCommandFactory");
            }
        };
        String factoryName = editRoomsFactoryMap.get(type);
        EditRoomsTypeCommandFactory ertc = (EditRoomsTypeCommandFactory) Class.forName(factoryName).newInstance();
        ertc.setBuildingItems(buildings);
        ertc.setCaretaker(caretaker);
        ertc.setId(id);
        return ertc.create();
    }

}

abstract class EditRoomsBuildingCommand implements Command {

    Building building;
    Caretaker caretaker;
    protected int id;
    protected int noOfRooms;
    protected ArrayList<Room> rooms;

    public EditRoomsBuildingCommand(Building building, Caretaker caretaker) {
        this.building = building;
        this.caretaker = caretaker;
        this.id = building.getId();
        this.noOfRooms = building.getRoomQty();
        this.rooms = building.getRooms();
    }

    public abstract void execute();

    @Override
    public String toString() {
        String str = "Edit Rooms Building:  Building No.: " + id;
        return str;
    }
}

class EditRoomsApartmentCommand extends EditRoomsBuildingCommand {

    private Caretaker caretaker;
    private Apartment apartment;
    private int noOfRooms;
    private ArrayList<Room> rooms = new ArrayList<Room>();

    public EditRoomsApartmentCommand(Caretaker caretaker, Apartment apartment, int noOfRooms, ArrayList<Room> rooms) {
        super(apartment, caretaker);
        this.caretaker = caretaker;
        this.apartment = apartment;
        this.noOfRooms = noOfRooms;
        this.rooms = rooms;
    }

    @Override
    public void execute() {
        caretaker.saveCurrentState(building, this);
        System.out.println(apartment);
        System.out.println("");
    }

    @Override
    public String toString() {
        return super.toString() + ", Support Staff: " + apartment.getSuportStaff() + ", Monthly Rental: " + apartment.getMonthlyRental();
    }

}

class EditRoomsHouseCommand extends EditRoomsBuildingCommand {

    private Caretaker caretaker;
    private House house;
    private int noOfRooms;
    private ArrayList<Room> rooms = new ArrayList<Room>();

    public EditRoomsHouseCommand(Caretaker caretaker, House house, int noOfRooms, ArrayList<Room> rooms) {
        super(house, caretaker);
        this.caretaker = caretaker;
        this.house = house;
        this.noOfRooms = noOfRooms;
        this.rooms = rooms;
    }

    @Override
    public void execute() {
        caretaker.saveCurrentState(building, this);
        System.out.println(house);
        System.out.println("");
    }

    @Override
    public String toString() {
        return super.toString() + ", No. of Floor: " + house.getFloors();
    }

}
