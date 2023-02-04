import java.util.ArrayList;
import java.util.Vector;

public class DeleteRoomCommandFactory extends EditRoomsTypeCommandFactory {

    private Caretaker caretaker;
    private int roomId;
    private ArrayList<Room> rooms = new ArrayList<Room>();

    @Override
    public Command create() throws Exception {
        Building building = BuildingItemsUtil.getBuildingItemByID(buildings, id);
        caretaker = ((EditRoomsTypeCommandFactory) this).caretaker;
        System.out.print("Room No.:");
        roomId = Integer.parseInt(Assignment.sc.next());
        for (int i = 0; i < building.getRoomQty(); i++) {
            double tlength = building.rooms.get(i).length;
            double twidth = building.rooms.get(i).width;
            rooms.add(new Room(tlength, twidth));
        }
        System.out.println("Updated Building:");
        return new DeleteRoomCommand(buildings, building, caretaker, id, roomId, rooms);
    }

}

abstract class DeleteRoomsBuildingCommand implements Command {

    Vector<Building> buildings;
    Building building;
    Caretaker caretaker;
    protected int id;

    public DeleteRoomsBuildingCommand(Vector<Building> buildings, Building building, Caretaker caretaker, int id) {
        this.buildings = buildings;
        this.building = building;
        this.caretaker = caretaker;
        this.id = id;
    }

    public abstract void execute();

    @Override
    public String toString() {
        String str = "Delete Room: Building No: " + id;
        return str;
    }
}

class DeleteRoomCommand extends DeleteRoomsBuildingCommand {

    Vector<Building> buildings;
    private Building building;
    private Caretaker caretaker;
    private int id;
    private int roomId;
    private double length;
    private double width;
    private ArrayList<Room> rooms = new ArrayList<Room>();

    public DeleteRoomCommand(Vector<Building> buildings, Building building, Caretaker caretaker, int id, int roomId, ArrayList<Room> rooms) {
        super(buildings, building, caretaker, id);
        this.buildings = buildings;
        this.building = building;
        this.caretaker = caretaker;
        this.id = id;
        this.roomId = roomId;
        this.rooms = rooms;
    }

    @Override
    public void execute() {
        caretaker.saveCurrentState(building, this);
        building.rooms = rooms;
        building.noOfRooms = rooms.size();
        length = building.getRooms().get(roomId - 1).length;
        width = building.getRooms().get(roomId - 1).width;
        building.deleteRoom(roomId);
        building.pringBuilding();
    }

    @Override
    public String toString() {
        return super.toString() + ", Room No. " + roomId + ", Length: " + length + ", Width: " + width;
    }
}
