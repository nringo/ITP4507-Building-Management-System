import java.util.ArrayList;

public class ModifyBuildingCommandFactory extends CommandFactory {

    @Override
    public Command create() throws Exception {

        ModifyBuildingCommand mbc = null;
        if (buildings.size() > 0) {

            System.out.print("Building No.:");
            String id = Assignment.sc.next();

            Building building = BuildingItemsUtil.getBuildingItemByID(buildings, Integer.parseInt(id));

            if (building == null) {
                System.out.println("Food item with specified ID not found!");
                return null;
            }

            if (building instanceof Apartment) {
                System.out.println(building);

                System.out.print("Modify Monthly Rental: ");
                double monthly_rental = Assignment.sc.nextDouble();

                System.out.print("Modify Support Staff: ");
                String support_staff = "";
                Assignment.sc.nextLine();
                support_staff = Assignment.sc.nextLine();

                System.out.println("Building is modified: ");
                Apartment apartment = (Apartment) building;
                mbc = new ModifyApartmentCommand(caretaker, apartment, monthly_rental, support_staff);
            }

            if (building instanceof House) {
                System.out.println("Modify No. of Floor: ");
                int no_of_floor = Assignment.sc.nextInt();
                House house = (House) building;
                mbc = new ModifyHouseCommand(caretaker, house, no_of_floor);
            }

        } else {
            System.out.println("Building is not define\n\n");
        }
        return mbc;
    }

}

abstract class ModifyBuildingCommand implements Command {

    Building building;
    Caretaker caretaker;
    protected int id;
    protected int noOfRooms;
    protected ArrayList<Room> rooms;

    public ModifyBuildingCommand(Building building, Caretaker caretaker) {
        this.building = building;
        this.caretaker = caretaker;
        this.id = building.getId();
        this.noOfRooms = building.getRoomQty();
        this.rooms = building.getRooms();
    }

    public abstract void execute();

    @Override
    public String toString() {
        String str = "Modify Building: Building No.: " + id;
        return str;
    }
}

class ModifyApartmentCommand extends ModifyBuildingCommand {

    private Caretaker caretaker;
    private Apartment apartment;
    private double monthlyRental;
    private String suportStaff;

    public ModifyApartmentCommand(Caretaker caretaker, Apartment apartment, double monthlyRental, String suportStaff) {
        super(apartment, caretaker);
        this.apartment = apartment;
        this.caretaker = caretaker;
        this.monthlyRental = monthlyRental;
        this.suportStaff = suportStaff;
    }

    @Override
    public void execute() {
        caretaker.saveCurrentState(building, this);
        apartment.setMonthlyRental(monthlyRental);
        apartment.setSuportStaff(suportStaff);
        System.out.println(apartment);
        System.out.println("");
    }

    @Override
    public String toString() {
        return super.toString() + ", Support Staff: " + suportStaff + ", Monthly Rental: " + monthlyRental;
    }

}

class ModifyHouseCommand extends ModifyBuildingCommand {

    private Caretaker caretaker;
    private House house;
    private int noOfFloors;

    public ModifyHouseCommand(Caretaker caretaker, House house, int noOfFloors) {
        super(house, caretaker);
        this.house = house;
        this.caretaker = caretaker;
        this.noOfFloors = noOfFloors;
    }

    @Override
    public void execute() {
        caretaker.saveCurrentState(building, this);
        house.setFloors(noOfFloors);
        System.out.println(house);
        System.out.println("");
    }

    @Override
    public String toString() {
        return super.toString() + ", No. of Floor: " + noOfFloors;
    }

}
