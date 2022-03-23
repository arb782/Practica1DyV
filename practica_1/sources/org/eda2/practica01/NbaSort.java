package org.eda2.practica01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Esta clase carga un archivo de jugadores con cabecera
 * "#;SeasonStart;PlayerName;PlayerSalary;Pos;Age;Tm;FG%;PTS" y trabaja con el
 * para extraer los n mejores jugadores.
 * 
 * @author: Alex Ruiz
 * 
 * @version 1.0
 * 
 */
public class NbaSort {

	// Campos de la clase
	private static String rutaArchivo = System.getProperty("user.dir") + File.separator + "src" + File.separator + "org"
			+ File.separator + "eda2" + File.separator + "practica01" + File.separator + "NbaStats.csv";
	private static ArrayList<Player> nba;
	public static int numJugadores = 10;

	/**
	 * Este metodo comprueba si la informacion guardada del archivo estaba vacia y
	 * si no lo esta crea un ArrayList y llama al metodo
	 * {@link #mejoresJugadores(int, int)} para que se encargue de su contenido y
	 * finalmente los muestra por pantalla.
	 * 
	 */
	public static void mejoresJugadores() {
		if (nba.size() == 0) {
			System.out.println("No data");
		} else {
			ArrayList<Player> mejoresJugadores = mejoresJugadores(0, nba.size() - 1);
			System.out.println("---------------Mejores " + numJugadores + " jugadores---------------");
			for (Player jugador : mejoresJugadores) {
				System.out.println("          " + jugador + "            ");
			}
		}
	}

	/**
	 * Metodo para cargar el archivo
	 * 
	 */
	public static void cargarArchivo(String rutaArchivo) {
		nba = new ArrayList<Player>();
		try {
			Scanner sc = new Scanner(new File(rutaArchivo));
			String linea = "";
			String[] tokens;
			Player jugadorActual = null;
			String nombreJugador = "";
			while (sc.hasNextLine()) {
				linea = sc.nextLine().trim();
				if (linea.isEmpty() || linea.startsWith("﻿#;"))
					continue;
				tokens = linea.split(";");
				// 8035;1986;A.C. Green;;PF;22;LAL;53,9;521
				double fg = stringToDouble(tokens[7]);
				double puntos = stringToDouble(tokens[8]);
				if (!tokens[2].equals(nombreJugador)) {
					jugadorActual = new Player(tokens[2], tokens[6], tokens[4], (int) ((fg / 100) * puntos));
					nombreJugador = tokens[2];
					nba.add(jugadorActual);
				} else {
					jugadorActual.add(tokens[6], tokens[4], (int) (fg * puntos / 100));
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error loading data file: file not found");
		}
	}

	/**
	 * Metodo que devuelve el numero convertido de String a Double.(usado para la
	 * score y FG%)
	 * 
	 * @param value Un numero en String
	 * @return El numero String convertido en Double si es posible, si no, devuelve
	 *         0
	 */
	private static double stringToDouble(String value) {
		if (value.isEmpty()) {
			return 0;
		}
		try {
			double d = Double.parseDouble(value.replace(",", "."));
			return d;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Metodo de ordenacion del ArrayList de jugadores segun sus puntos y FG% basado
	 * en MergeSort mejorado para este caso en particular. La mejora consiste en que
	 * en el momento que encontremos los n mejores jugadores que necesitamos pare de
	 * ordenar.
	 * 
	 * @param principio Principio del ArrayList que queremos ordenar
	 * @param fin       Final del ArrayList que queremos ordenar
	 * @return ArrayList ordenado
	 */
	private static ArrayList<Player> mejoresJugadores(int principio, int fin) {
		ArrayList<Player> mejoresJugadores = new ArrayList<Player>(numJugadores);
		if (principio == fin) {
			mejoresJugadores.add(nba.get(principio));
		} else {
			int mitad = (principio + fin) / 2;
			ArrayList<Player> jugadores1 = mejoresJugadores(principio, mitad);
			ArrayList<Player> jugadores2 = mejoresJugadores(mitad + 1, fin);

			int i = 0;
			int j = 0;
			while (mejoresJugadores.size() < numJugadores && i <= jugadores1.size() - 1 && j <= jugadores2.size() - 1) {
				if (jugadores1.get(i).getScore() > jugadores2.get(j).getScore()) {
					mejoresJugadores.add(jugadores1.get(i));
					i++;
				} else {
					mejoresJugadores.add(jugadores2.get(j));
					j++;
				}
			}
			while (mejoresJugadores.size() < numJugadores && i <= jugadores1.size() - 1) {
				mejoresJugadores.add(jugadores1.get(i));
				i++;
			}
			while (mejoresJugadores.size() < numJugadores && j <= jugadores2.size() - 1) {
				mejoresJugadores.add(jugadores2.get(j));
				j++;
			}
		}
		return mejoresJugadores;
	}

}
