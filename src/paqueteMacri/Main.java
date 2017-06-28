package paqueteMacri;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Metro m1 = new Metro("metro1.in");
		m1.resolver();
		m1.mostrarSolucion();
	}

}
