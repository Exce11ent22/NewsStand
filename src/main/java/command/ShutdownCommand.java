package command;

public class ShutdownCommand implements Command {

  private final Shutdownable[] toShutdown;

  public ShutdownCommand(Shutdownable... toShutdown) {
    this.toShutdown = toShutdown;
  }

  @Override
  public void execute() {
    for (Shutdownable shutdownable : toShutdown) {
      shutdownable.shutdown();
    }
    System.exit(0);
  }

  @Override
  public String toString() {
    return "Exit";
  }

}
