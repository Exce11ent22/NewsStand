import command.*;
import items.Item;
import repository.Repository;
import repository.RepositoryInMemory;
import repository.persistence.ConnectionManager;
import repository.persistence.RepositoryInDB;
import services.CLIAdapters.RepositoryCLIAdapter;
import services.RepositoryAdapter;
import ui.CLI;
import ui.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    ConnectionManager connectionManager = ConnectionManager.getInstance();
    Repository<Item> itemRepository = new RepositoryInDB(connectionManager);
    RepositoryAdapter adapter = new RepositoryCLIAdapter(itemRepository);

    List<Command> commands = new ArrayList<>(
      Arrays.asList(
        new AcceptItemsCommand(adapter),
        new SellItemCommand(adapter),
        new ViewCatalogCommand(adapter),
        new ShutdownCommand(connectionManager)
      )
    );

    UI ui = new CLI(adapter, commands);
    ui.run();

  }
}
