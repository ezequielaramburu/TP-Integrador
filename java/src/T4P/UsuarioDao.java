package T4P;

import java.util.List;

public interface UsuarioDao {
	
	 public void insert(Usuario usuario) throws PersistenceException;
	    
	    public void delete(Usuario usuario) throws PersistenceException;
	    
	    public void update(Usuario usuario) throws PersistenceException;
	    
	    public Usuario findById(String idUsuario) throws PersistenceException;
	        
	    public List<Usuario> findAll() throws PersistenceException;

}
