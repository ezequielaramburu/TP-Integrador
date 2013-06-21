package T4P;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
		List<Usuario> lista = new LinkedList<Usuario>();
		try {
			String query = "select * from usuario";
			java.sql.PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}
	
	private Usuario convertOne(ResultSet resultSet) throws SQLException {
		Usuario retorno = new Usuario();

		retorno.setIdUsuario(resultSet.getString("IdUsuario"));
		retorno.setContrasenia(resultSet.getString("contrasenia"));
		retorno.setNombre(resultSet.getString("nombre"));
		retorno.setApellido(resultSet.getString("apellido"));

		return retorno;
	}

	public static UsuarioDao getInstance() {
		return instance;
	}

	public static void setInstance(UsuarioDao instance) {
		UsuarioDaoJdbcImpl.instance = instance;
	}
	
	@Override
	public Usuario findById(String idUsuario) throws PersistenceException {
		if (idUsuario == null) {
			throw new IllegalArgumentException(
					"El id de persona no debe ser nulo");
		}
		Usuario usuario = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from persona where id = ?";
			java.sql.PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, idUsuario);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				usuario = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return usuario;
	}

}
