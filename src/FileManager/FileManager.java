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
import Videojuegos.Personajes;
import Videojuegos.Videojuego;
import Vistas.Inicio;
public class FileManager implements Intercambio {
	private String archivo_videojuegos = "src/Modelo/videojuegos.txt";
	private String archivo_personajes = "src/Modelo/personajes.txt";
	HashMap <Integer, Videojuego> ListaVideojuegos = new HashMap <Integer, Videojuego>();
	
	Inicio	mVista = new Inicio();
	HashMap<Integer, Personajes> listaPersonajes  = new HashMap <Integer, Personajes>(); 
	@Override
	public HashMap<Integer, Videojuego> LeerTodos() {
	      FileReader fr = null;
	      BufferedReader br = null;
	      Controlador mControlador = new Controlador();
		try {
			fr = new FileReader (archivo_videojuegos);
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
				BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_videojuegos, true));
				for (Entry<Integer, Videojuego> entry : ListaVideojuegos.entrySet()) {
					bw.write("ID: "+entry.getValue().getID()+"\n"+"Nombre: "+entry.getValue().getNombre()+"\n"+"Plataforma: "+entry.getValue().getPlataforma()+"\n"+"Fecha de Lanzamiento: "+entry.getValue().getFecha_Lanzamiento()+"\n"+"Desarrollador: "+entry.getValue().getDesarrollador()+"\n");
				}
				 
				 
			 bw.close();
			 

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EscribirTodosPersonajes();
		return ListaVideojuegos;

		
	}
	
	public HashMap<Integer, Personajes> EscribirTodosPersonajes()  {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		Controlador mControlador = new Controlador();
		
		try {
	 mVista.PedirDatoPer(listaPersonajes);
	 BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_personajes, true));
	 for (Entry<Integer, Personajes> entry : listaPersonajes.entrySet()) {
			bw.write("ID: "+entry.getKey()+"\n"+"Nombre: "+entry.getValue().getNombre_Personaje()+"\n"+"id_juego: "+entry.getValue().getID_Juego()+"\n");
		}
	 bw.close();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		mControlador.Cargar_Inicio();
		return listaPersonajes;
	}
	
	
	@Override
	public HashMap<Integer, Videojuego> A�adir() {
		Modelo	mModelo = new Modelo();
		Controlador mControlador = new Controlador();
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo_videojuegos, true));
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


	@Override
	public HashMap<Integer, Personajes> A�adirPer() {
		// TODO Auto-generated method stub
		return null;
	}
		
	}


