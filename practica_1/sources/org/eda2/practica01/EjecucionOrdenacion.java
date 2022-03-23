package org.eda2.practica01;

public class EjecucionOrdenacion {
	public static void main(String[] args) {
		NbaSort.numJugadores = 10;
		NbaSort.cargarArchivo();
		long start = System.currentTimeMillis();
		NbaSort.mejoresJugadores();
		long end = System.currentTimeMillis();
		System.out.println("Tiempo: " + (end - start) + "ms");
	}

}
