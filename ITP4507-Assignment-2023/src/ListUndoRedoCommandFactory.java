public class ListUndoRedoCommandFactory extends CommandFactory {

    @Override
    public Command create() throws Exception {
        return new ListUndoRedoCommand(caretaker);
    }
}

class ListUndoRedoCommand implements Command {

    private Caretaker caretaker;

    public ListUndoRedoCommand(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    @Override
    public void execute() {
        caretaker.DisplayUndoRedoList();
    }

}
