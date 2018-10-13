package Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import BD_Manager.BD_Manager;
import Videojuegos.Videojuego;
import Vistas.*;
import Interface.Intercambio;
import FileManager.FileManager;

public class Controlador {
	BD_Manager mBD = new BD_Manager();
	FileManager mFM = new FileManager();
	Inicio mVista = new Inicio();
	public void ImprimirDatos() {
		mBD.LeerTodos();
	}
	public void ImprimirVideojuegos() {
		mBD.LeerTodosAux();
	}
	public void ImprimirDatosPer() {
		mBD.LeerTodosPer();
	}
	public void InsertarBBDD() {
		mBD.A�adir();
	}
	public void InsertarBBDDPer() {
		mBD.A�adirPer();
	}
	public void Leer_Fichero() {
		mFM.LeerTodos();
	}
	public void Leer_FicheroPer() {
		mFM.LeerTodosPer();
	}
	public void Escribir_Fichero() {
		mFM.EscribirTodos();
	}
	public void Escribir_FicheroPer() {
		mFM.EscribirTodosPer();
	}
	
	public void TXT2BBDD() {
		mBD.EscribirTodos();
	}
	public void BBDD2TXT(){
		mFM.A�adir();
	}
	public void BBDD2TXTPer(){
		mFM.A�adirPer();
	}
	public void Cargar_Inicio() {
		// TODO Auto-generated method stub
		try {
			mVista.CargarMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
