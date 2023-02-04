import java.util.ArrayList;
import java.util.Vector;

public class AddRoomCommandFactory extends EditRoomsTypeCommandFactory {

    private Caretaker caretaker;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private double length;
    private double width;

    @Override
    public Command create() throws Exception {
        Building building = BuildingItemsUtil.getBuildingItemByID(buildings, id);
        caretaker = ((EditRoomsTypeCommandFactory) this).caretaker;

        buildings.remove(building);

        System.out.print("Length: ");
        length = Assignment.sc.nextInt();
        
        System.out.print("Width: ");
        width = Assignment.sc.nextInt();

        System.out.println("Updated Building:");
        for (int i = 0; i < building.getRoomQty(); i++) {
            double tlength = building.rooms.get(i).length;
            double twidth = building.rooms.get(i).width;
            rooms.add(new Room(tlength, twidth));
        }
        rooms.add(new Room(length, width));
        return new AddRoomCommand(buildings, building, caretaker, id, rooms, length, width);
    }

}

abstract class AddRoomsBuildingCommand implements Command {

    Vector<Building> buildings;
    Building building;
    Caretaker caretaker;
    protected int id;
    protected int noOfRooms;
    protected ArrayList<Room> rooms = new ArrayList<Room>();

    public AddRoomsBuildingCommand(Vector<Building> buildings, Building building, Caretaker caretaker, int id, int noOfRooms, ArrayList<Room> rooms) {
        this.buildings = buildings;
        this.building = building;
        this.caretaker = caretaker;
        this.id = id;
        this.noOfRooms = noOfRooms;
        this.rooms = rooms;
    }

    public abstract void execute();

    @Override
    public String toString() {
        String str = "Add Room: Building No:" + id;
        return str;
    }
}

class AddRoomCommand extends AddRoomsBuildingCommand {

    private Caretaker caretaker;
    private Building building1;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private double length;
    private double width;

    public AddRoomCommand(Vector<Building> buildings, Building building, Caretaker caretaker, int id, ArrayList<Room> rooms, double length, double width) {
        super(buildings, building, caretaker, id, building.getRoomQty(), building.getRooms());
        this.building1 = building1;
        this.caretaker = caretaker;
        this.rooms = rooms;
        this.length = length;
        this.width = width;
    }

    @Override
    public void execute() {
        caretaker.saveCurrentState(building, this);
        building.rooms = rooms;
        building.noOfRooms = rooms.size();
        buildings.add(building);
        building.pringBuilding();
    }

    @Override
    public String toString() {
        return super.toString() + ", Room No. " + building.getRoomQty() + ", Length: " + length + ", Width: " + width;
    }

}
