package org.eda2.practica01;

import java.io.File;

public class EjecucionOrdenacion {
	public static void main(String[] args) {
		NbaSort.rutaArchivo=System.getProperty("user.dir") + File.separator + "src" + File.separator + "org"
				+ File.separator + "eda2" + File.separator + "practica01" + File.separator + "NbaStats.csv";
		NbaSort.numJugadores = 10;
		NbaSort.cargarArchivo(NbaSort.rutaArchivo);
		long start = System.currentTimeMillis();
		NbaSort.mejoresJugadores();
		long end = System.currentTimeMillis();
		System.out.println("---------------Mejores " + NbaSort.numJugadores + " jugadores---------------");
		for (Player jugador : NbaSort.mejoresJugadores()) {
			System.out.println("          " + jugador + "            ");
		}
		System.out.println("Tiempo: " + (end - start) + "ms");
		
	}

}
