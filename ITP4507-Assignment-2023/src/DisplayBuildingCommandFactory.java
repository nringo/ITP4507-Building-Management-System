  import java.util.Vector;

public class DisplayBuildingCommandFactory extends CommandFactory {

    @Override
    public Command create() throws Exception {
        System.out.println("Enter ID (* to show all):");
        String inputID = Assignment.sc.next();
        return new DisplayBuildingCommand(buildings, inputID);
    }
}

class DisplayBuildingCommand implements Command {

    private Vector<Building> buildings;
    private String inputID;

    public DisplayBuildingCommand(Vector<Building> buildings, String inputID) {
        this.buildings = buildings;
        this.inputID = inputID;
    }

    @Override
    public void execute() {
        if (buildings.size() > 0) {
            if (inputID.equals("*")) {
                for (Building building : buildings) {
                    System.out.println(building);
                }
                System.out.println("");
            } else {
                for (Building building : buildings) {
                    if ((building.getId() + "").equalsIgnoreCase(inputID)) {
                        building.pringBuilding();
                    }
                }
            }
        } else {
            System.out.println("Building item with specified ID not found !");
        }
    }
}
