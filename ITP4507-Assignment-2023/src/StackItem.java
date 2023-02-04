public class StackItem {

    private Building building; // for redo create food item
    private Memento   memento; // for nemento restore
    private Command   command; // for display command list

    public StackItem(Building building, Memento memento, Command command) {
        this.building = building;
        this.memento = memento;
        this.command = command;
    }

    public Building getBuildingItem() {
        return building;
    }

    public void setBuildingItem(Building building) {
        this.building = building;
    }

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
