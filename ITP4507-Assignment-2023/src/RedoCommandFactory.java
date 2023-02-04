public class RedoCommandFactory extends CommandFactory {

    @Override
    public Command create() throws Exception {
        return new RedoCommand(caretaker);
    }
}

class RedoCommand implements Command {

    private Caretaker caretaker;

    public RedoCommand(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    @Override
    public void execute() {
        caretaker.redo();
    }

}
