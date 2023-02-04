import java.util.ArrayList;

abstract class Building {

    int id;
    int noOfRooms;
    ArrayList<Room> rooms = new ArrayList<Room>();

    public Building(int id, int noOfRooms) {
        this.id = id;
        this.noOfRooms = noOfRooms;
    }

    public int getId() {
        return id;
    }

    public int getRoomQty() {
        return noOfRooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Room addRooms(double length, double width) {
        noOfRooms++;
        return new Room(length, width);
    }

    public void modifyRoom(int roomNo, double length, double width) {
        for (int i = 0; i < rooms.size(); i++) {
            if (i == roomNo) {
                rooms.get(i).setLength(length);
                rooms.get(i).setWidth(width);
            }
        }
    }

    public void deleteRoom(int roomNo) {
        noOfRooms--;
        rooms.remove(roomNo - 1);
    }

    public void pringRooms() {
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("Room No.: " + (i + 1) + ", Length: " + getRooms().get(i).getLength() + " , Width: " + getRooms().get(i).width);
        }
        System.out.println("");
    }

    public abstract void modifyBuilding();

    public abstract void pringBuilding();

}
