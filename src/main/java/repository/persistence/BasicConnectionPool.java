package repository.persistence;

import command.Shutdownable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool, Shutdownable {

  private final List<Connection> connectionPool;
  private final List<Connection> usedConnections = new ArrayList<>();
  private final static int INITIAL_POOL_SIZE = 2;

  public static BasicConnectionPool create(String url, String user, String password) throws SQLException {
    List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
    for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
      pool.add(createConnection(url, user, password));
    }
    return new BasicConnectionPool(pool);
  }

  private BasicConnectionPool(List<Connection> connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public Connection getConnection() {
    Connection connection = connectionPool.remove(connectionPool.size() - 1);
    usedConnections.add(connection);
    return connection;
  }

  @Override
  public boolean releaseConnection(Connection connection) {
    connectionPool.add(connection);
    return usedConnections.remove(connection);
  }

  private static Connection createConnection(String url, String user, String password) throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }

  @Override
  public void shutdown(){
    usedConnections.forEach(this::releaseConnection);
    for (Connection c : connectionPool) {
      try {
        c.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    connectionPool.clear();
  }

}
