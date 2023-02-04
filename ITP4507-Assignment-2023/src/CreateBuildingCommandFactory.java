import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class CreateBuildingCommandFactory extends CommandFactory {

    @Override
    public Command create() throws Exception {

        HashMap<String, String> buildingFactoryMap = new HashMap<String, String>() {
            {
                put("a", "CreateApartmentCommandFactory");
                put("h", "CreateHouseCommandFactory");
            }
        };

        System.out.println("Enter Building Type (a=Apartment/h=House): ");

        String type = Assignment.sc.next();
        // get the factoryName from the HashMap
        String factoryName = buildingFactoryMap.get(type);

        // error checking
        // if the option is not in the HashMap
        if (factoryName == null) {
            System.out.println("Invalid Option");
            return null;
        }

        // then call the corresponding factory
        CreateBuildingCommandFactory bcf = (CreateBuildingCommandFactory) Class.forName(factoryName).newInstance();
        bcf.setBuildingItems(buildings);
        bcf.setCaretaker(caretaker);

        return bcf.create();
    }
}

abstract class CreateBuildingCommand implements Command {

    Vector<Building> buildings;
    Building building;
    Caretaker caretaker;
    protected int id;
    protected int noOfRooms;
    protected ArrayList<Room> rooms;

    public CreateBuildingCommand(Vector<Building> buildings, Caretaker caretaker, int id, int noOfRooms, ArrayList<Room> rooms) {
        this.buildings = buildings;
        this.caretaker = caretaker;
        this.id = id;
        this.noOfRooms = noOfRooms;
        this.rooms = rooms;
    }

    public abstract void execute();

    @Override
    public String toString() {
        String str = "Add Building: Building No.: " + id;
        return str;
    }
}

class CreateApartmentCommand extends CreateBuildingCommand {

    private double monthlyRental;
    private String suportStaff;

    public CreateApartmentCommand(Vector<Building> buildings, Caretaker caretaker, int id, int noOfRooms, ArrayList<Room> rooms, double monthlyRental, String suportStaff) {
        super(buildings, caretaker, id, noOfRooms, rooms);
        this.monthlyRental = monthlyRental;
        this.suportStaff = suportStaff;
    }

    @Override
    public void execute() {
        building = new Apartment(id, noOfRooms, monthlyRental, suportStaff);
        for (int i = 0; i < building.getRoomQty(); i++) {
            System.out.println("Room No. " + (i + 1) + " :");

            System.out.print("Length: ");
            int length = Assignment.sc.nextInt();

            System.out.print("Width: ");
            int width = Assignment.sc.nextInt();
            building.getRooms().add(new Room(length, width));
        }
        buildings.add(building);
        caretaker.saveCurrentState(building, this);
        System.out.print("New Building Added:\n");
        building.pringBuilding();
    }

    @Override
    public String toString() {
        return super.toString() + ", Support Staff: " + suportStaff + ", Monthly Rental: " + monthlyRental;
    }

}

class CreateHouseCommand extends CreateBuildingCommand {

    int noOfFloors;

    public CreateHouseCommand(Vector<Building> buildings, Caretaker caretaker, int id, int noOfRooms, ArrayList<Room> rooms, int noOfFloors) {
        super(buildings, caretaker, id, noOfRooms, rooms);
        this.noOfFloors = noOfFloors;
    }

    @Override
    public void execute() {
        building = new House(id, noOfRooms, noOfFloors);
        for (int i = 0; i < building.getRoomQty(); i++) {
            System.out.println("Room No. " + (i + 1) + " :");

            System.out.print("Length: ");
            int length = Assignment.sc.nextInt();

            System.out.print("Width: ");
            int width = Assignment.sc.nextInt();

            building.getRooms().add(new Room(length, width));
        }
        buildings.add(building);
        caretaker.saveCurrentState(building, this);
        System.out.println("New Building Added:");
        building.pringBuilding();
    }

    @Override
    public String toString() {
        return super.toString() + ", No. of Floors: " + noOfFloors;
    }

}

class CreateApartmentCommandFactory extends CreateBuildingCommandFactory {

    @Override
    public Command create() throws Exception {

        System.out.print("Building No.: ");
        int id = 0;
        id = Integer.parseInt(Assignment.sc.next());

        if (BuildingItemsUtil.getBuildingItemByID(buildings, id) != null) {
            System.out.println("Building ID must not duplicate !");
            System.out.println("");
            return null;
        }

        System.out.print("Monthly Rental: ");
        double monthly_rental = 0.0;
        monthly_rental = Double.parseDouble(Assignment.sc.next());

        System.out.print("Support Staff: ");
        String support_staff = "";
        Assignment.sc.nextLine();
        support_staff = Assignment.sc.nextLine();

        System.out.print("Number of rooms: ");
        int number_of_rooms = Integer.parseInt(Assignment.sc.next());

        return new CreateApartmentCommand(buildings, caretaker, id, number_of_rooms, null, monthly_rental, support_staff);
    }

}

class CreateHouseCommandFactory extends CreateBuildingCommandFactory {

    @Override
    public Command create() throws Exception {
        System.out.print("Building No.: ");
        int id = Assignment.sc.nextInt();

        if (BuildingItemsUtil.getBuildingItemByID(buildings, id) != null) {
            System.out.println("Building ID must not duplicate !");
            System.out.println("");
            return null;
        }

        System.out.print("No. of Floors: ");
        int no_of_floors = Assignment.sc.nextInt();

        System.out.print("Number of rooms: ");
        int number_of_rooms = Assignment.sc.nextInt();

        return new CreateHouseCommand(buildings, caretaker, id, number_of_rooms, null, no_of_floors);
    }
}
