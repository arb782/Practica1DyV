package org.eda2.practica01;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SortJUnitTest {
	
	@Test
	void testMejoresJugadores() {
		NbaSort.rutaArchivo=System.getProperty("user.dir") + File.separator + "src" + File.separator + "org"
				+ File.separator + "eda2" + File.separator + "practica01" + File.separator + "NbaStats.csv";
		NbaSort.cargarArchivo(NbaSort.rutaArchivo);
		NbaSort.numJugadores=10;
		long start = System.currentTimeMillis();
		ArrayList<Player> jugadores=NbaSort.mejoresJugadores();
		long end = System.currentTimeMillis();
		System.out.println("Tiempo: " + (end - start) + "ms");
		assertEquals("[Wilt Chamberlain*= 1153puntos, Kareem Abdul-Jabbar*= 1076puntos, Michael Jordan*= 1075puntos, George Gervin*= 1059puntos, LeBron James= 1034puntos, Karl Malone*= 1005puntos, Karl-Anthony Towns= 965puntos, Kevin Durant= 935puntos, Oscar Robertson*= 925puntos, Jerry West*= 854puntos]", jugadores.toString());
	}
	

}
