package command;

import services.RepositoryAdapter;

public class SellItemCommand implements Command {

  private final RepositoryAdapter adapter;

  public SellItemCommand(RepositoryAdapter adapter) {
    this.adapter = adapter;
  }

  @Override
  public void execute() {
    adapter.sellItem();
  }

  @Override
  public String toString() {
    return "Sell item";
  }
}
