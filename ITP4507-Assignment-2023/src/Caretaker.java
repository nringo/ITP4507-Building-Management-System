import java.util.Stack;
import java.util.Vector;

public class Caretaker {

    Vector<Building> buildings = new Vector<>(); //Store the building for vector
    Stack<StackItem> undoStack = new Stack<>(); //Store the memento for undo
    Stack<StackItem> redoStack = new Stack<>(); //Store the memento for redo

    public void setBuildingItems(Vector<Building> buildings) {
        this.buildings = buildings;
    }

    public Memento getMemento(Building building) {
        if (building instanceof Apartment) {
            return new ApartmentMemento((Apartment) building);
        } else if (building instanceof House) {
            return new HouseMemento((House) building);
        }
        return null;
    }

    public void saveCurrentState(Building building, Command command) {
        Memento memento = getMemento(building);
        StackItem si = new StackItem(building, memento, command);
        undoStack.push(si);
        redoStack.clear();
    }

    public void undo() {
        if (undoStack.empty()) {
            System.out.println("No last undo command.");
            System.out.println("");
            return;
        }

        // get the last undo command
        StackItem stackItem = undoStack.pop();

        // check if it is the create food command then remove food item.
        // otherwise, restore the food item property.
        //System.out.println(stackItem.getCommand().getClass());
        if (stackItem.getCommand() instanceof CreateBuildingCommand) {
            Building building = stackItem.getBuildingItem();
            buildings.remove(building);
        } else {
            // save the current state of the last undo foodItem
            Memento memento = getMemento(stackItem.getBuildingItem());

            stackItem.getMemento().restore();

            // for the later redo operation,
            // we need to set the Memento to the new Memento
            // which is before the restore
            stackItem.setMemento(memento);
        }
        redoStack.push(stackItem);
        System.out.println("undo completed.");
        System.out.println("");
    }

    public void redo() {
        if (redoStack.empty()) {
            System.out.println("No last redo command.");
            System.out.println("");
            return;
        }
        StackItem stackItem = redoStack.pop();

        // Check if it is the create food command then add food item.
        // Otherwise, restore the food item property.
        if (stackItem.getCommand() instanceof CreateBuildingCommand) {
            buildings.add(stackItem.getBuildingItem());
        } else {
            // save the current state of the last redo foodItem
            Memento memento = getMemento(stackItem.getBuildingItem());

            stackItem.getMemento().restore();

            // for the later undo operation,
            // we need to set the Memento to the new Memento
            // which is before the restore
            stackItem.setMemento(memento);
        }
        undoStack.push(stackItem);
        System.out.println("redo completed.");
        System.out.println("");
    }

    public void DisplayUndoRedoList() {
        System.out.println("Undo List:");
        if (undoStack.empty()) {
            System.out.println("Nothing to Redo.");
        } else {
            for (int i = undoStack.size(); i > 0; i--) {
                System.out.println((undoStack.get(i - 1)).getCommand());
            }
            //(undoStack).forEach(stackItem -> System.out.println(stackItem.getCommand()));
        }

        System.out.println();

        System.out.println("Redo List:");
        if (redoStack.empty()) {
            System.out.println("Nothing to Redo.");
        } else {
            for (int i = redoStack.size(); i > 0; i--) {
                System.out.println((redoStack.get(i - 1)).getCommand());
            }
            //(redoStack).forEach(stackItem -> System.out.println(stackItem.getCommand()));
        }

        System.out.println("");
    }

}
