package command;

import services.RepositoryAdapter;

public class ChangeItemsDataCommand implements Command {

  private final RepositoryAdapter adapter;

  public ChangeItemsDataCommand(RepositoryAdapter adapter) {
    this.adapter = adapter;
  }

  @Override
  public void execute() {
    adapter.changeItemsData();
  }

  @Override
  public String toString() {
    return "Change items data";
  }
}
