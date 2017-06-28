package paqueteMacri;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Metro {
	private int[][] matAdy;
	private int cantTuneles;
	private int cantPuentes;
	private int cantNodos;
	private boolean[] vecVisitados;
	public Metro(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		cantNodos = sc.nextInt();
		matAdy = new int[cantNodos][cantNodos];
		cantTuneles = sc.nextInt();
		cantPuentes = sc.nextInt();
		vecVisitados = new boolean[cantNodos];
		for (int i = 0; i < matAdy.length; i++) {
			for (int j = 0; j < matAdy.length; j++) {
				matAdy[i][j] = 999;
			}
		}
		
		for (int i = 0; i < cantTuneles; i++) {
			matAdy[sc.nextInt()][sc.nextInt()] = 1;
		}
		
		
		//No ponemos los puentes porque me pide la cantidad de puentes
	}
	
	private void resolverDFS(int nodoInicio) {
		Stack<Integer> pila = new Stack<Integer>();
		int nodoActual = nodoInicio;
		pila.push(nodoActual);
		vecVisitados[nodoActual] = true;
		while(!pila.isEmpty()) {
			if(buscarAdy(nodoActual)) {
				for (int i = 0; i < vecVisitados.length; i++) {
					if(matAdy[nodoActual][i] != 999 && !vecVisitados[i]) {
						vecVisitados[i] = true;
						pila.push(i);
						nodoActual = i;
					}
				}
				
			} else {
				pila.pop();
				if(!pila.isEmpty()) {
					nodoActual = pila.peek();
				}
			}
		}
		
	}
	
	
	private boolean buscarAdy(int fila) {
		for (int i = 0; i < matAdy.length; i++) {
			if (matAdy[fila][i] != 999 && !vecVisitados[i]) {
				return true;
			}
		}
		return false;
	}
	
	public void resolver() {
		int cantidadIslas = 0;
		int faltaPuente = necesitoPuente();
		while (faltaPuente != -1) {
			resolverDFS(faltaPuente);
			cantidadIslas++;
			faltaPuente = necesitoPuente();
		}
		if(cantidadIslas-1 < cantPuentes) {
			cantPuentes = cantidadIslas-1;
		}
	}

	private int necesitoPuente() {
		for (int i = 0; i < vecVisitados.length; i++) {
			if(!vecVisitados[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public void mostrarSolucion() {
		System.out.println(cantPuentes);
	}
}
