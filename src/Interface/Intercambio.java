package Interface;

import java.sql.SQLException;
import java.util.HashMap;

import Videojuegos.Videojuego;

public interface Intercambio {


	public HashMap<Integer, Videojuego> EscribirTodos();
	public HashMap<Integer, Videojuego> A�adir();
	public HashMap<Integer, Videojuego>LeerTodos();
	
	
}