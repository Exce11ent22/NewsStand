package command;

import services.RepositoryAdapter;

public class ViewCatalogCommand implements Command {

  private final RepositoryAdapter adapter;

  public ViewCatalogCommand(RepositoryAdapter adapter) {
    this.adapter = adapter;
  }

  @Override
  public void execute() {
    adapter.viewCatalog();
  }

  @Override
  public String toString() {
    return "View catalog";
  }
}
