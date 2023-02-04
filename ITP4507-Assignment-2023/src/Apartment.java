public class Apartment extends Building {

    private double monthlyRental;
    private String suportStaff;

    public Apartment(int id, int noOfRooms, double monthlyRental, String suportStaff) {
        super(id, noOfRooms);
        this.monthlyRental = monthlyRental;
        this.suportStaff = suportStaff;
    }

    public void setMonthlyRental(double monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    public double getMonthlyRental() {
        return monthlyRental;
    }

    public void setSuportStaff(String suportStaff) {
        this.suportStaff = suportStaff;
    }

    public String getSuportStaff() {
        return suportStaff;
    }

    @Override
    public void modifyBuilding() {
        System.out.println("Building is modified:");
        System.out.println(this.toString());
    }

    @Override
    public void pringBuilding() {
        System.out.println(""
                + "Building No: " + getId()
                + "\nSupport Staff: " + getSuportStaff()
                + "\nMonthly Rental: " + getMonthlyRental());
        super.pringRooms();
    }

    @Override
    public String toString() {
        String str = "";
        str = "Building No: " + getId() + ", Support Staff: " + getSuportStaff() + ", Monthly Rental: " + getMonthlyRental();
        return str;
    }

}
