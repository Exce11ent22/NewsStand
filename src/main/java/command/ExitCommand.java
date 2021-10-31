package command;

public class ExitCommand implements Command {

  @Override
  public void execute() {
    System.exit(0);
  }

  @Override
  public String toString() {
    return "Exit";
  }

}
