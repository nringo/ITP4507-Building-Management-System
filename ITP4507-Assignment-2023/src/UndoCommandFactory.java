public class UndoCommandFactory extends CommandFactory {

    @Override
    public Command create() throws Exception {
        return new UndoCommand(caretaker);
    }
}

class UndoCommand implements Command {

    private Caretaker caretaker;

    public UndoCommand(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    @Override
    public void execute() {
        caretaker.undo();
    }

}
