package Videojuegos;

public class Personajes {

	private int ID;
	private String Nombre_Personaje;
	private int ID_Juego;
	
	
	
	public Personajes(int id, String nombre_Personaje, int id_Juego) {
		this.ID = id;
		this.Nombre_Personaje = nombre_Personaje;
		this.ID_Juego = id_Juego;
	}
	
	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public String getNombre_Personaje() {
		return Nombre_Personaje;
	}
	public void setNombre_Personaje(String nombre_Personaje) {
		Nombre_Personaje = nombre_Personaje;
	}
	public int getID_Juego() {
		return ID_Juego;
	}
	public void setID_Juego(int id_Juego) {
		ID_Juego = id_Juego;
	}

	
}
