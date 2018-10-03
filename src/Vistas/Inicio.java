package Vistas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Map.Entry;
import Controlador.Controlador;
import Interface.Intercambio;
import Main.Main;
import Modelo.Modelo;
import Videojuegos.Videojuego;

public class Inicio {
	
	public void CargarMenu() throws SQLException, IOException {
		Controlador mControlador = new Controlador();
		
			// TODO Auto-generated method stub
			System.out.println("1: Crear fichero");
			System.out.println("2: Leer fichero");
			System.out.println("3: Copiar BBDD a fichero");
			System.out.println("4: Copiar fichero a BBDD");
			System.out.println("5: Leer BBDD");
			System.out.println("6: Añadir BBDD");
		
			Scanner opt = new Scanner(System.in);
			System.out.print("Eliga una opción:  ");
			 int eleccion = opt.nextInt();
			 
			switch (eleccion) {
			case 1:
				mControlador.Escribir_Fichero();
				break;
	    	case 2:
	    		mControlador.Leer_Fichero();
	 			break;
	    	case 3:
				mControlador.BBDD2TXT();
				break;
			case 4:
				mControlador.TXT2BBDD();
			    break;
			case 5:
				mControlador.ImprimirDatos();
			    break;
			case 6:
				mControlador.InsertarBBDD();
				break;
			default:
				System.out.println("Dato mal introducido");
				break;
			}
		}
	
	public void sacarPantalla(HashMap<Integer, Videojuego> ListaVideojuegos ) {
		for( Entry <Integer, Videojuego> videojuego: ListaVideojuegos.entrySet() ) {
			System.out.println("ID: "+videojuego.getKey().toString());
			System.out.println("Nombre: " +videojuego.getValue().getNombre());
			System.out.println("Plataforma: "+videojuego.getValue().getPlataforma());
			System.out.println("Fecha de Lanzamiento: "+videojuego.getValue().getFecha_Lanzamiento());
			System.out.println("Desarrollador: "+videojuego.getValue().getDesarrollador());
		}
	}

	public void PedirDatos(HashMap<Integer, Videojuego> ListaVideojuegos) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nombre del Videojuego: ");
		String nametxt = scanner.nextLine();
		System.out.println("Plataforma (DS, GBA, N64): ");
		String plataformatxt = scanner.nextLine();
		System.out.println("Fecha de Lanzamiento: ");
		String fechatxt = scanner.nextLine();
		System.out.println("Desarrollador: ");
		String desarrolladortxt = scanner.nextLine();
		Videojuego mVideojuego = new Videojuego(nametxt, fechatxt, desarrolladortxt, plataformatxt);
		
		int id = 0;
			ListaVideojuegos.put(id, mVideojuego);
		
		}

	 
	 		
	 		
		
		 
		 
	
	
}
