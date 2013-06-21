package T4P;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDaoJdbcImpl implements UsuarioDao  {
	
	private static UsuarioDao instance = new UsuarioDaoJdbcImpl();

	@Override
	public void insert(Usuario usuario) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into usuario (IdUsuario, contrasenia, nombre, apellido) values (?, ?, ?, ?)";
			java.sql.PreparedStatement statement = TransactionJdbcImpl.getInstance().getConnection().prepareStatement(query);
			statement.setString(1, usuario.getIdUsuario());
			statement.setString(2, usuario.getContrasenia());
			statement.setString(3, usuario.getNombre());
			statement.setString(4, usuario.getApellido());

			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
		
	}

	@Override
	public void delete(Usuario usuario) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from usuario where IdUsuario = ?";
			java.sql.PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, usuario.getIdUsuario());
			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
		
	}

	@Override
	public void update(Usuario usuario) throws PersistenceException {
		try {
			String query = "update usuario set contrasenia = ?, nombre = ?, apellido = ?,  where IdUsuario = ?";

			java.sql.PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, usuario.getIdUsuario());
			statement.setString(2, usuario.getContrasenia());
			statement.setString(3, usuario.getNombre());
			statement.setString(4, usuario.getApellido());

			statement.executeUpdate();
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
	}
	
	@Override
	public List<Usuario> findAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	public static UsuarioDao getInstance() {
		return instance;
	}

	public static void setInstance(UsuarioDao instance) {
		UsuarioDaoJdbcImpl.instance = instance;
	}

}
