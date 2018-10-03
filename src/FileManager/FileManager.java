package FileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Map.Entry;

import Controlador.Controlador;

import java.util.Scanner;

import Interface.Intercambio;
import Modelo.Modelo;
import Videojuegos.Videojuego;
import Vistas.Inicio;
public class FileManager implements Intercambio {
	private String archivo = "src/Modelo/fichero.txt";
	HashMap <Integer, Videojuego> ListaVideojuegos = new HashMap <Integer, Videojuego>();
	Modelo	mModelo = new Modelo();
	Inicio	mVista = new Inicio(); 
	@Override
	public HashMap<Integer, Videojuego> LeerTodos() {
	      FileReader fr = null;
	      BufferedReader br = null;
	      Controlador mControlador = new Controlador();
		try {
			fr = new FileReader (archivo);
			 br = new BufferedReader(fr);
				
				String linea;
			     while((linea=br.readLine())!=null)
			        System.out.println(linea);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		mControlador.Cargar_Inicio();
		
	    return ListaVideojuegos;
		// TODO Auto-generated method stub
		
	}


	@Override
	public HashMap<Integer, Videojuego> EscribirTodos()  {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		Controlador mControlador = new Controlador();
		
		try {
			   mVista.PedirDatos(ListaVideojuegos);
				BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
				for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
					bw.write("Nombre: "+entry.getValue().getNombre()+"\n"+"Plataforma: "+entry.getValue().getPlataforma()+"\n"+"Fecha de Lanzamiento: "+entry.getValue().getFecha_Lanzamiento()+"\n"+"Desarrollador: "+entry.getValue().getDesarrollador()+"\n");
				}
			
			 bw.close();
			 

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mControlador.Cargar_Inicio();
		return ListaVideojuegos;

		
	}
	@Override
	public HashMap<Integer, Videojuego> Añadir() {
		Controlador mControlador = new Controlador();
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
			// salida = new FileOutputStream(miConfig);
			// cargamos el archivo de propiedades
			PreparedStatement pstm;
			String cargar = "Select * from videojuegos";
			ResultSet rset;
				pstm = mModelo.conexion.prepareStatement(cargar);
				rset = pstm.executeQuery();
				while (rset.next()) {
				 int id1= rset.getInt(1);
					String Nombre = rset.getString("Nombre");
					String Fecha = rset.getString("Fecha_Lanzamiento");
					String Desarrollador = rset.getString("Desarrollador");
					String Plataforma = rset.getString("Plataforma");
					Videojuego mVideojuego = new Videojuego(Nombre, Fecha, Desarrollador, Plataforma);
					
					
					ListaVideojuegos.put(id1, mVideojuego);
					
				}
				
				for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
					
					bw.write("ID: "+ entry.getKey() +"\n"+"Nombre: "+entry.getValue().getNombre()+"\n"+"Fecha de Lanzamiento: "+entry.getValue().getFecha_Lanzamiento()+"\n"+"Desarrollador: "+entry.getValue().getDesarrollador()+"\n"+"Plataforma: "+entry.getValue().getPlataforma()+"\n");
				}
				bw.close();
				mControlador.Cargar_Inicio();
			} catch (IOException|SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return ListaVideojuegos;
	}
		
	}


