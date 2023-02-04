import java.util.ArrayList;

interface Memento {

    public void restore();

    public int getID();
}

abstract class BuildingMemento implements Memento {

    Building building;
    int mId;
    int mNoOfRooms;
    ArrayList<Room> mRooms = new ArrayList<Room>();

    public BuildingMemento(Building building) {
        this.building = building;
        this.mId = building.getId();
        this.mNoOfRooms = building.getRoomQty();
        this.mRooms = building.getRooms();
    }

    public abstract void restore();

    public int getID() {
        return mId;
    }
}

class ApartmentMemento extends BuildingMemento implements Memento {

    private Apartment apartment;

    private double mMonthlyRental;
    private String mSuportStaff;

    public ApartmentMemento(Apartment apartment) {
        super(apartment);
        this.apartment = apartment;
        this.mId = apartment.getId();
        this.mNoOfRooms = apartment.getRoomQty();
        this.mRooms = apartment.getRooms();
        this.mMonthlyRental = apartment.getMonthlyRental();
        this.mSuportStaff = apartment.getSuportStaff();
    }

    public void restore() {
        apartment.setMonthlyRental(mMonthlyRental);
        apartment.setSuportStaff(mSuportStaff);
        apartment.noOfRooms = mNoOfRooms;
        building.rooms = mRooms;
    }

    public int getID() {
        return mId;
    }
}

class HouseMemento extends BuildingMemento implements Memento {

    private House house;
    private int mFloors;

    public HouseMemento(House house) {
        super(house);
        this.house = house;
        this.mFloors = house.getFloors();
    }

    public void restore() {
        house.setFloors(mFloors);
        building.rooms = mRooms;
        house.noOfRooms = mNoOfRooms;
    }

    public int getID() {
        return mId;
    }
}
