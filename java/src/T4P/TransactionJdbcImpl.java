package T4P;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class TransactionJdbcImpl implements Transaction {
	
	 private static TransactionJdbcImpl instance = null;
	    
	    private Connection connection = null;
	    
	    private TransactionJdbcImpl() {
	        
	    }
	    
	    public static TransactionJdbcImpl getInstance() {
	        if(TransactionJdbcImpl.instance == null) {
	            TransactionJdbcImpl.instance = new TransactionJdbcImpl();
	        }
	        return TransactionJdbcImpl.instance;
	    }
	    
	    public Connection getConnection() throws PersistenceException {
	        try {
	            if(this.connection == null || this.connection.isClosed()) {
	                this.connection = ConnectionProvider.getInstance().getConnection();
	            } 
	        } catch(SQLException sqlException) {
	            throw new PersistenceException(sqlException);
	        }
	        return this.connection;
	    }
	    
	    @Override
	    public void begin() throws PersistenceException {
	        try {
	            this.getConnection().setAutoCommit(false);
	        } catch (SQLException sqlException) {
	            throw new PersistenceException(sqlException);
	        }
	    }

	    @Override
	    public void commit() throws PersistenceException {
	        try {
	            this.connection.commit();
	        } catch (SQLException sqlCommitException) {
	            throw new PersistenceException(sqlCommitException);
	        } finally {
	            this.close();
	        }
	    }

	    @Override
	    public void rollback() throws PersistenceException {
	        try {
	            this.connection.rollback();
	        } catch (SQLException sqlRollbackException) {
	            throw new PersistenceException(sqlRollbackException);
	        } finally {
	            this.close();
	        }
	    }
	    
	    private void close() throws PersistenceException {
	        try {
	            if(this.connection != null && 
	               !this.connection.isClosed()) {
	                ConnectionProvider.getInstance().closeConnection();
	                this.connection = null;
	            }
	        } catch (SQLException sqlException) {
	            throw new PersistenceException(sqlException);
	        }
	    }


}
