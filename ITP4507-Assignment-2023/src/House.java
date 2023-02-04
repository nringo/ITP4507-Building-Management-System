public class House extends Building {

    private int noOfFloors;

    public House(int id, int noOfRooms, int noOfFloors) {
        super(id, noOfRooms);
        this.noOfFloors = noOfFloors;
    }

    public void setFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public int getFloors() {
        return noOfFloors;
    }

    @Override
    public void modifyBuilding() {
        System.out.println("Building is modified:");
        System.out.println(this.toString());
    }

    @Override
    public void pringBuilding() {
        System.out.println(
                "Building No: " + getId()
                + "\nNo. of Floors: " + getFloors());
        super.pringRooms();
    }

    @Override
    public String toString() {
        String str = "";
        str = "Building No: " + getId() + ", No. of Floors: " + getFloors();
        return str;
    }

}
