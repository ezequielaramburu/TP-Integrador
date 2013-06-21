package T4P;

import com.mysql.jdbc.Connection;
import java.sql.*;

public class ConnectionProvider {
	
	private static ConnectionProvider instance;
	private static java.sql.Connection connection = null;
	
	private String url = "jdbc:mysql:usuario.db";
	
	private ConnectionProvider() throws PersistenceException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(this.url);
		} catch (Exception classNotFoundException) {
			throw new PersistenceException(classNotFoundException);
		}
	
	
	}
	
	public Connection getConnection() throws PersistenceException {

		try {
			this.closeConnection();

			connection = DriverManager.getConnection(this.url);
			connection.setAutoCommit(false);
		} catch (Exception exception) {
			throw new PersistenceException(exception);
		}
		return (Connection) connection;
	}
	
	public static ConnectionProvider getInstance() throws PersistenceException {
		if (instance == null) {
			instance = new ConnectionProvider();
		}
		return instance;
	}
	public void closeConnection() throws PersistenceException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	public void rollback() throws PersistenceException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}
}
