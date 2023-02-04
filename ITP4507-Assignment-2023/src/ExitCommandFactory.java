public class ExitCommandFactory extends CommandFactory {

    public Command create() throws Exception {
        return (Command) new ExitCommand();
    }

}

class ExitCommand extends ExitCommandFactory {

    public ExitCommand() {
        System.out.println("Building exit system...");
        System.exit(0);
    }

}
