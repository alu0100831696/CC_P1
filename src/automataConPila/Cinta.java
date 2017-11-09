package automataConPila;

/**
 * @author Sofía Pizarro - alu0100831696
 * @category CC - Práctica1 - Autómata con Pila
 * @date 22/10/2017
 * @class Cinta
 **/

import java.util.ArrayList;


public class Cinta {
	private ArrayList<String> alfabeto = new ArrayList<String>();
	private ArrayList<String> valores = new ArrayList<String>();
	private int puntero = 0;

	// Constructor
	Cinta(ArrayList<String> alp) {
		alfabeto = alp;
	}

	// Constructor copia
	@SuppressWarnings("unchecked")
	Cinta(Cinta original) {
		valores = (ArrayList<String>) original.valores.clone();
		alfabeto = (ArrayList<String>) original.alfabeto.clone();
		puntero = original.puntero;
	}

	public String read() {
		return valores.get(puntero);
	}

	public int getPuntero() {
		return puntero;
	}

	public void setCinta(String cadena) {
		for (int i = cadena.length() - 1; i >= 0; i--) {

			if (alfabeto.contains("" + cadena.charAt(i))) {
				valores.add("" + cadena.charAt(i));
			} else {
				System.err.println("Este símbolo no pertenece al alfabeto de la cinta: " + cadena.charAt(i));
				System.exit(1);
			}

		}
		puntero = cadena.length() - 1;
	}

	public int puntero() {
		return puntero;
	}

	public void setPuntero(int value) {
		puntero = value;
	}

	public void punteroDec() {
		puntero--;
	}

	public String getCinta() {
		String aux = "";
		if (puntero >= 0) {
			for (int i = puntero; i >= 0; i--) {
				aux += " " + valores.get(i);
			}
		}
		return aux;
	}
}
