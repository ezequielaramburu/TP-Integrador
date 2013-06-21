package T4P;

public class DaoFactory {

	public static UsuarioDao getUsuarioDao(){
		return UsuarioDaoJdbcImpl.getInstance();
	}
}
