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

import BD_Manager.BD_Manager;

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
			System.out.println("   1: Crear Fichero Videojuegos");
			System.out.println("   2: Crear Fichero Personajes");
			Scanner opt1 = new Scanner(System.in);
			System.out.print("     Elija una opción:  ");
			int eleccion1 = opt1.nextInt();
			switch (eleccion1) {
			case 1:
				
				mControlador.Escribir_Fichero();
				break;
			case 2:
				mControlador.Escribir_FicheroPer();
			default:
				break;
			}

			break;
		case 2:
			System.out.println("   1: Leer Videojuegos");
			System.out.println("   2: Leer Personajes");
			Scanner opt2 = new Scanner(System.in);
			System.out.print("     Elija una opción:  ");
			int eleccion2 = opt2.nextInt();
			switch (eleccion2) {
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
			System.out.println("  1: Pasar tabla Videojuegos a Fichero");
			System.out.println("  2: Pasar tabla Personajes a Fichero");
			Scanner opt3 = new Scanner(System.in);
			System.out.print("    Elija una opción:  ");
			int eleccion3 = opt3.nextInt();
			switch (eleccion3) {
			case 1:
				mControlador.BBDD2TXT();
				break;
			case 2:
				mControlador.BBDD2TXTPer();
			default:
				break;

			}
			break;

		case 4:
			mControlador.TXT2BBDD();
			
			break;
		case 5:
			System.out.println("  1: Leer Tabla Videojuegos");
			System.out.println("  2: Leer Tabla Personajes");
			Scanner opt5 = new Scanner(System.in);
			System.out.print("    Elija una opción:  ");
			int eleccion5 = opt5.nextInt();
			switch (eleccion5) {
			case 1:
				mControlador.ImprimirDatos();
				break;
			case 2:
				mControlador.ImprimirDatosPer();
			default:
				break;
			}

			break;
		case 6:
			System.out.println("   1: Insertar un Videojuego");
			System.out.println("   2: Insertar un Personajes");
			Scanner opt6 = new Scanner(System.in);
			System.out.print("     Elija una opción:  ");
			int eleccion6 = opt6.nextInt();
			switch (eleccion6) {
			case 1:
				mControlador.InsertarBBDD();
				break;
			case 2:
				mControlador.InsertarBBDDPer();
			default:
				break;
			}

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

	public void sacarPantallaPer(HashMap<Integer, Personajes> listaPersonajes) {
		for (Entry<Integer, Personajes> videojuego : listaPersonajes.entrySet()) {
			System.out.println("ID: " + videojuego.getKey().toString());
			System.out.println("Nombre: " + videojuego.getValue().getNombre_Personaje());
			System.out.println("ID_Juego: " + videojuego.getValue().getID_Juego());
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

					mControlador.Escribir_Fichero();
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
				if (id == (rset.getInt(1))) {

					System.err.println("Este ID ya existe, por favor introduzca otro\n");

					mControlador.InsertarBBDD();
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

					mControlador.Escribir_FicheroPer();
				}

			}
			System.out.println("Nombre del Personaje: ");
			String namePtxt = scanner.nextLine();

			System.out.println("ID_Juego: ");
			String id_juegotxt = scanner.nextLine();
			int id_juego = Integer.parseInt(id_juegotxt);
		

			Personajes mPersonaje = new Personajes(namePtxt, id_juego);

			listaPersonajes.put(idper, mPersonaje);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void PedirDatoPerDB(HashMap<Integer, Personajes> listaPersonajes) {
		// TODO Auto-generated method stub
		BD_Manager mBD = new BD_Manager();
		Modelo mModelo = new Modelo();
		Controlador mControlador = new Controlador();
		PreparedStatement pstm, pstm1;
		String cargar = "Select * from personajes";
		String cargar1 = "Select * from videojuegos";
		ResultSet rsetper, rset;
		try {
			pstm = mModelo.conexion.prepareStatement(cargar);
			pstm1 = mModelo.conexion.prepareStatement(cargar1);
			rsetper = pstm.executeQuery();
			rset = pstm1.executeQuery();

			if (rset.next()) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("ID_Personaje: ");
				String idtxt = scanner.nextLine();
				int idper = Integer.parseInt(idtxt);

				while (rsetper.next()) {
					if (idper == (rsetper.getInt(1))) {
						System.err.println("Este ID ya existe, por favor introduzca otro\n");
						idper = 0;
						mControlador.InsertarBBDDPer();
						;
					}

				}
				System.out.println("Nombre del Personaje: ");
				String namePtxt = scanner.nextLine();

				mControlador.ImprimirVideojuegos();
				System.out.println("----Elige el juego al que pertenece este Personaje");
				Scanner scanner1 = new Scanner(System.in);
				System.out.println("ID_Juego: ");
				String idj = scanner1.nextLine();
				int id = Integer.parseInt(idj);
				Personajes mPersonaje = new Personajes(namePtxt, id);

				listaPersonajes.put(idper, mPersonaje);

			} else {
				System.out.print("¡¡¡¡¡No hay ningún juego añadido por favor añade al menos uno!!!!!\n");
				System.out.print("¿Quieres añadirlo ahora?\n");
				System.out.print("   1: SI\n");
				System.out.print("   2: NO\n");
				Scanner opt6 = new Scanner(System.in);
				System.out.print("     Elija una opción:  \n");
				int eleccion4 = opt6.nextInt();
				switch (eleccion4) {
				case 1:
					mControlador.InsertarBBDD();
					break;
				case 2:

					break;
				default:
					break;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
