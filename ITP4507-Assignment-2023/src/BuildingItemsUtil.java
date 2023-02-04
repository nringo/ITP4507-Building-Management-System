import java.util.Vector;


public class BuildingItemsUtil {
    public static Building getBuildingItemByID(Vector<Building> buildingItems, int id){
        for (Building building : buildingItems) {
            if (building.getId() == id) {
                return building;
            }
        }
        return null;
    }
}
