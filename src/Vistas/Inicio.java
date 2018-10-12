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
import Videojuegos.Personajes;
import Videojuegos.Videojuego;

public class Inicio {
	int id = 0;
	private String archivo_videojuegos = "src/Modelo/videojuegos.txt";
	private String archivo_personajes = "src/Modelo/personajes.txt";
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
		System.out.print("Elija una opción:  ");
		int eleccion = opt.nextInt();
		switch (eleccion) {
		case 1:
			mControlador.Escribir_Fichero();
			break;
		case 2:
			System.out.println("1: Leer Videojuegos");
			System.out.println("2: Leer Personajes");
			Scanner opt1 = new Scanner(System.in);
			System.out.print("Elija una opción:  ");
			int eleccion1 = opt1.nextInt();
			switch (eleccion1) {
			case 1:
				mControlador.Leer_Fichero();
				break;
			case 2:
				mControlador.Leer_FicheroPer();
			default:
				break;
			}
			
			break;
		case 3:
			System.out.println("1: Leer Videojuegos");
			System.out.println("2: Leer Personajes");
			Scanner opt2 = new Scanner(System.in);
			System.out.print("Elija una opción:  ");
			int eleccion2 = opt.nextInt();
			switch (eleccion2) {
			case 1:
				mControlador.Leer_Fichero();
				mControlador.BBDD2TXT();
				break;
			case 2:
				mControlador.Leer_FicheroPer();
			default:
				break;
			
			}
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

	public void sacarPantalla(HashMap<Integer, Videojuego> ListaVideojuegos) {
		for (Entry<Integer, Videojuego> videojuego : ListaVideojuegos.entrySet()) {
			System.out.println("ID: " + videojuego.getKey().toString());
			System.out.println("Nombre: " + videojuego.getValue().getNombre());
			System.out.println("Plataforma: " + videojuego.getValue().getPlataforma());
			System.out.println("Fecha de Lanzamiento: " + videojuego.getValue().getFecha_Lanzamiento());
			System.out.println("Desarrollador: " + videojuego.getValue().getDesarrollador());
		}
	}

	public void PedirDatosF(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Controlador mControlador = new Controlador();
		try {

			BufferedReader br = new BufferedReader(new FileReader(archivo_videojuegos));
			Scanner scanner = new Scanner(System.in);
			System.out.println("ID_Videojuego: ");
			String idtxt = scanner.nextLine();
			 id = Integer.parseInt(idtxt);
			String linea;
			while ((linea = br.readLine()) != null) {
				if (idtxt.equals(linea.substring(4))) {

					System.err.print("Este ID ya existe, por favor introduzca otro\n");
				
					mControlador.Cargar_Inicio();
				}
				
				
			}
			System.out.println("Nombre del Videojuego: ");
			String nametxt = scanner.nextLine();
			System.out.println("Plataforma (DS, GBA, N64): ");
			String plataformatxt = scanner.nextLine();
			System.out.println("Fecha de Lanzamiento: ");
			String fechatxt = scanner.nextLine();
			System.out.println("Desarrollador: ");
			String desarrolladortxt = scanner.nextLine();
			Videojuego mVideojuego = new Videojuego(nametxt, fechatxt, desarrolladortxt, plataformatxt);

			ListaVideojuegos.put(id, mVideojuego);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void PedirDatosDB(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Controlador mControlador = new Controlador();
		Modelo mModelo = new Modelo();
		PreparedStatement pstm;
		String cargar = "Select * from videojuegos";
		ResultSet rset;
	
		try {
			pstm = mModelo.conexion.prepareStatement(cargar);
			rset = pstm.executeQuery();
			BufferedReader br = new BufferedReader(new FileReader(archivo_videojuegos));
			Scanner scanner = new Scanner(System.in);
			System.out.println("ID_Videojuego: ");
			String idtxt = scanner.nextLine();
			 id = Integer.parseInt(idtxt);
			
			while (rset.next()) {
				if (idtxt.equals(rset.getInt(1))) {

					System.err.print("Este ID ya existe, por favor introduzca otro\n");
				
					mControlador.Cargar_Inicio();
				}
				
				
			}
			System.out.println("Nombre del Videojuego: ");
			String nametxt = scanner.nextLine();
			System.out.println("Plataforma (DS, GBA, N64): ");
			String plataformatxt = scanner.nextLine();
			System.out.println("Fecha de Lanzamiento: ");
			String fechatxt = scanner.nextLine();
			System.out.println("Desarrollador: ");
			String desarrolladortxt = scanner.nextLine();
			Videojuego mVideojuego = new Videojuego(nametxt, fechatxt, desarrolladortxt, plataformatxt);

			ListaVideojuegos.put(id, mVideojuego);

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void PedirDatoPerF(HashMap<Integer, Personajes> listaPersonajes) {
		// TODO Auto-generated method stub
		Controlador mControlador = new Controlador();

		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo_personajes));
			Scanner scanner = new Scanner(System.in);
			System.out.println("ID_Personaje: ");
			String idtxt = scanner.nextLine();
			int idper = Integer.parseInt(idtxt);
			String linea;
			while ((linea = br.readLine()) != null) {
				if (idtxt.equals(linea.substring(4))) {
					System.err.println("Este ID ya existe, por favor introduzca otro\n");
					
				mControlador.Cargar_Inicio();
				}
				
				
			}
			System.out.println("Nombre del Personaje: ");
			String namePtxt = scanner.nextLine();

			idper = 0;
			Personajes mPersonaje = new Personajes(namePtxt, id);

			listaPersonajes.put(idper, mPersonaje);
			idper++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	public void PedirDatoPerDB(HashMap<Integer, Personajes> listaPersonajes) {
		// TODO Auto-generated method stub
		Modelo mModelo = new Modelo();
		Controlador mControlador = new Controlador();
		PreparedStatement pstm;
		String cargar = "Select * from personajes";
		ResultSet rset;
		try {
			pstm = mModelo.conexion.prepareStatement(cargar);
			rset = pstm.executeQuery();
			BufferedReader br = new BufferedReader(new FileReader(archivo_personajes));
			Scanner scanner = new Scanner(System.in);
			System.out.println("ID_Personaje: ");
			String idtxt = scanner.nextLine();
			int idper = Integer.parseInt(idtxt);
		
			while (rset.next()) {
				if (idtxt.equals(rset.getInt(1))) {
					System.err.println("Este ID ya existe, por favor introduzca otro\n");
					
				mControlador.Cargar_Inicio();
				}
				
				
			}
			System.out.println("Nombre del Personaje: ");
			String namePtxt = scanner.nextLine();

			idper = 0;
			Personajes mPersonaje = new Personajes(namePtxt, id);

			listaPersonajes.put(idper, mPersonaje);
			idper++;
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
