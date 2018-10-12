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

	public void sacarPantalla(HashMap<Integer, Videojuego> ListaVideojuegos) {
		for (Entry<Integer, Videojuego> videojuego : ListaVideojuegos.entrySet()) {
			System.out.println("ID: " + videojuego.getKey().toString());
			System.out.println("Nombre: " + videojuego.getValue().getNombre());
			System.out.println("Plataforma: " + videojuego.getValue().getPlataforma());
			System.out.println("Fecha de Lanzamiento: " + videojuego.getValue().getFecha_Lanzamiento());
			System.out.println("Desarrollador: " + videojuego.getValue().getDesarrollador());
		}
	}

	public void PedirDatos(HashMap<Integer, Videojuego> ListaVideojuegos) {
		Controlador mControlador = new Controlador();
		 try {
			BufferedReader br = new BufferedReader( new FileReader (archivo_videojuegos));
			Scanner scanner = new Scanner(System.in);
			System.out.println("ID_Videojuego: ");
			String idtxt = scanner.nextLine();
			 id=Integer.parseInt(idtxt);
			 String linea;
			while ((linea=br.readLine())!=null) {
					if(idtxt.equals(linea.substring(4))) {
						System.err.println("Este ID ya existe, por favor introduzca otro");
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

	public void PedirDatoPer(HashMap<Integer, Personajes> listaPersonajes) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nombre del Personaje: ");
		String namePtxt = scanner.nextLine();
	
		int idtxt=0;
		Personajes mPersonaje = new Personajes(namePtxt, id);

		listaPersonajes.put(idtxt, mPersonaje);
		idtxt++;
	}

}
