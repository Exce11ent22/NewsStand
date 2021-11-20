import command.*;
import items.Item;
import repository.Repository;
import repository.persistence.BasicConnectionPool;
import repository.persistence.ConnectionPool;
import repository.persistence.RepositoryInDB;
import services.CLIAdapters.RepositoryCLIAdapter;
import services.RepositoryAdapter;
import ui.CLI;
import ui.UI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) throws SQLException {

    ConnectionPool connectionPool = BasicConnectionPool.create(
      "jdbc:h2:file:C:\\Users\\Ivan Krikunov\\IdeaProjects\\newsStand;MV_STORE=false;AUTO_SERVER=TRUE",
      "IvanKrikunov",
      ""
    );
    Repository<Item> itemRepository = new RepositoryInDB(connectionPool);
    RepositoryAdapter adapter = new RepositoryCLIAdapter(itemRepository);

    List<Command> commands = new ArrayList<>(
      Arrays.asList(
        new AcceptItemsCommand(adapter),
        new SellItemCommand(adapter),
        new ViewCatalogCommand(adapter),
        new ShutdownCommand((Shutdownable) connectionPool)
      )
    );

    UI ui = new CLI(adapter, commands);
    ui.run();

  }
}
