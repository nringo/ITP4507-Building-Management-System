 import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Assignment {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Vector<Building> buildings = new Vector<Building>();
        Caretaker caretaker = new Caretaker();
        caretaker.setBuildingItems(buildings);
        Command c;
        String command = "";
        HashMap<String, String> factoryMap = new HashMap<String, String>() {
            {
                put("a", "CreateBuildingCommandFactory");
                put("d", "DisplayBuildingCommandFactory");
                put("m", "ModifyBuildingCommandFactory");
                put("e", "EditRoomsTypeCommandFactory");
                put("l", "ListUndoRedoCommandFactory");
                put("u", "UndoCommandFactory");
                put("r", "RedoCommandFactory");
                put("x", "ExitCommandFactory");
            }
        };

        while (true) {
            System.out.println(
                    "Building Management System (BMS)\n"
                    + "Please enter command: [a|d|m|e|u|r|l|x]\n"
                    + "a = add building, d = display buildings, m = modify building, e = edit rooms\n"
                    + "u = undo, r = redo, l = list undo/redo, x = exit system");
            command = sc.next();
            String factoryName = factoryMap.get(command);
            try {
                CommandFactory commandFactory = (CommandFactory) Class.forName(factoryName).newInstance();
                commandFactory.setBuildingItems(buildings);
                commandFactory.setCaretaker(caretaker);
                c = commandFactory.create();
                if (c != null) {
                    c.execute();
                }
            } catch (Exception e) {
                //System.out.println(e);
            }
        }

    }

}
