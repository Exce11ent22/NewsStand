package repository.persistence;

import command.Shutdownable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager implements Shutdownable {

  private final static String DB_URL = "jdbc:h2:file:C:\\Users\\Ivan Krikunov\\IdeaProjects\\newsStand;MV_STORE=false;AUTO_SERVER=TRUE";
  private final static String DB_USER = "IvanKrikunov";
  private final static String DB_PASS = "";

  private static ConnectionManager instance;
  private Connection connection;
  {
    try {
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private ConnectionManager() {
    try {
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Unable to find db driver: " + e.getMessage());
    }
  }

  public static ConnectionManager getInstance() {
    if (instance == null) {
      instance = new ConnectionManager();
    }
    return instance;
  }

  public Connection getConnection(){
    return connection;
  }

  @Override
  public void shutdown() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
