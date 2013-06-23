package Test;

import T4P.DaoFactory;
import T4P.Usuario;
import T4P.UsuarioDao;

public class usuarioDaoTest {

	UsuarioDao dao = DaoFactory.getUsuarioDao();

	Usuario cosmeFulanito;
	Usuario chuckNorris;
	
	@Before
	public void setUp() throws PersistenceException {
		// se borran todas las personas, para iniciar con base vacía
		for (Usuario cadaPersona : dao.findAll()) {
			dao.delete(cadaPersona);
		}

		// se inserta a Cosme Fulanito
		cosmeFulanito = buildUsuario("Cosme", "Cosme", "Fulanito", "Cosme");
		dao.insert(cosmeFulanito);

		// Chuck Norris decide insertarse
		chuckNorris = buildUsuario("Chuck", "Chuck", "Norris", "Chuck");
		dao.insert(chuckNorris);
	}
	
	private Usuario buildUsuario(String IdUsuario, String contrasenia, String nombre, String apellido) {
		Persona usuario = new Usuario();
		persona.setIdUsuario(IdUsuario);
		persona.setEdad(contrasenia);
		persona.setNombre(nombre);
		persona.setApellido(apellido);
		
		return usuario;
	}
	
	@After
	public void tearDown() throws PersistenceException {
		// se borran todas las personas

		dao.delete(cosmeFulanito);
		dao.delete(chuckNorris);

	}
	
	@Test
	public void testQueSePuedeBuscarUnaPersona() throws PersistenceException {

		Usuario personaEncontrada = dao.findById(cosmeFulanito.getIdUsuario());

		assertNotNull("la persona con id 1 debe existir", personaEncontrada);
		assertEquals("la persona 1 tiene nombre: Cosme", "Cosme", personaEncontrada.getApellido());
		assertEquals("la persona 1 tiene apellido: Fulanito", "Fulanito", personaEncontrada.getNombre());
		

	
}
