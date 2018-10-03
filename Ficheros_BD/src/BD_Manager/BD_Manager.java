package BD_Manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.Properties;
import java.util.Scanner;
import java.util.Map.Entry;
import Modelo.Modelo;
import Videojuegos.Videojuego;
import Interface.Intercambio;
import Vistas.Inicio;
import Controlador.Controlador;

public class BD_Manager implements Intercambio {
	private String archivo = "src/Modelo/fichero.txt";
	HashMap<Integer, Videojuego> ListaVideojuegos = new HashMap<Integer, Videojuego>();
	Modelo mModelo = new Modelo();
	Inicio mVista = new Inicio();


	@Override
	public HashMap<Integer, Videojuego> EscribirTodos() {

		Controlador mControlador = new Controlador();
		// cargamos el archivo de propiedades
		  FileReader fr = null;
	      BufferedReader br = null;
		try {
			
			Properties propiedades = new Properties();
			InputStream entrada = new FileInputStream(archivo);
			
			
				
			fr = new FileReader (archivo);
			 br = new BufferedReader(fr);
				
			     
			propiedades.load(entrada);
			
			String idtxt = br.readLine().substring(4);
			int id = Integer.parseInt(idtxt);
			String nametxt = br.readLine().substring(8);
			String fechatxt =  br.readLine().substring(22);
			String desarrolladortxt =   br.readLine().substring(15);
			String plataformatxt =  br.readLine().substring(12);
			Videojuego mVideojuego = new Videojuego(nametxt, fechatxt, desarrolladortxt, plataformatxt);
			ListaVideojuegos.put(id, mVideojuego);
			PreparedStatement pstm;
			for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
				String cargar = "INSERT INTO `videojuegos`(`ID`, `Nombre`, `Fecha_Lanzamiento`, `Desarrollador`, `Plataforma`) VALUES ("
						+entry.getKey()+ "," + "'" + entry.getValue().getNombre() + "'" + "," + "'" + entry.getValue().getFecha_Lanzamiento() + "'" + "," + "'" + entry.getValue().getDesarrollador() + "'" + ","
						+ "'" + entry.getValue().getPlataforma() + "'" + ")";
				pstm = mModelo.conexion.prepareStatement(cargar);
				int rset = pstm.executeUpdate();
			
			}
			
			     
			    
			
			 br.close();
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mControlador.Cargar_Inicio();

		return ListaVideojuegos;

	}
	

	@Override
	public HashMap<Integer, Videojuego> Añadir() {
		try {
		Controlador mControlador = new Controlador();
		PreparedStatement pstm;
		Scanner id = new Scanner(System.in);
		System.out.println("ID:");
		String idtxt = id.nextLine();
		mVista.PedirDatos(ListaVideojuegos);
		
		
		for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
			String cargar = "INSERT INTO `videojuegos`(`ID`, `Nombre`, `Fecha_Lanzamiento`, `Desarrollador`, `Plataforma`) VALUES ("
					+idtxt+ "," + "'" + entry.getValue().getNombre() + "'" + "," + "'" + entry.getValue().getFecha_Lanzamiento() + "'" + "," + "'" + entry.getValue().getDesarrollador() + "'" + ","
					+ "'" + entry.getValue().getPlataforma() + "'" + ")";
			pstm = mModelo.conexion.prepareStatement(cargar);
			int rset = pstm.executeUpdate();
		}
		
		
		
		
			
			mControlador.Cargar_Inicio();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ListaVideojuegos;
	}

		
		

	

	@Override
	public HashMap<Integer, Videojuego> LeerTodos() {
		Controlador mControlador = new Controlador();
		// TODO Auto-generated method stub
		PreparedStatement pstm;
		String cargar = "Select * from videojuegos";
		try {
			pstm = mModelo.conexion.prepareStatement(cargar);
			ResultSet rset = pstm.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("ID");
				String Nombre = rset.getString("Nombre");
				String Fecha = rset.getString("Fecha_Lanzamiento");
				String Desarrollador = rset.getString("Desarrollador");
				String Plataforma = rset.getString("Plataforma");
				Videojuego mVideojuego = new Videojuego(Nombre, Fecha, Desarrollador, Plataforma);

				ListaVideojuegos.put(id, mVideojuego);
			}
			mVista.sacarPantalla(ListaVideojuegos);
			mControlador.Cargar_Inicio();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ListaVideojuegos;
	}

}
