package T4P;

import com.mysql.jdbc.Connection;

public interface Transaction {

	 public Connection getConnection() throws PersistenceException;
	    
	    public void begin() throws PersistenceException;

	    public void commit() throws PersistenceException;

	    public void rollback() throws PersistenceException;
}
