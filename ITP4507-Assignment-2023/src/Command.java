import java.util.Vector;

interface Command {

    public void execute();

}

abstract class CommandFactory {

    Vector<Building> buildings;
    Caretaker caretaker;
    int id;

    public abstract Command create() throws Exception;

    public void setBuildingItems(Vector<Building> buildings) {
        this.buildings = buildings;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    public int get() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
