package Interface;

import java.sql.SQLException;
import java.util.HashMap;

import Videojuegos.Personajes;
import Videojuegos.Videojuego;

public interface Intercambio {


	public HashMap<Integer, Videojuego> EscribirTodos();
	public HashMap<Integer, Videojuego> Añadir();
	public HashMap<Integer, Videojuego>LeerTodos();
	public HashMap<Integer, Personajes> AñadirPer();
	
	
}
