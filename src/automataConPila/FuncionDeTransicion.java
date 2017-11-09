package automataConPila;

/**
 * @author Sofía Pizarro - alu0100831696
 * @category CC - Práctica1 - Autómata con Pila
 * @date 22/10/2017
 * @class FunTranscn
 **/

import java.util.ArrayList;

public class FuncionDeTransicion {
	private String cinta;
	private String pila;
	private String siguiente;
	public ArrayList<String> escPila = new ArrayList<String>();

	//Constructor
	//Tiene como parámetro la función de transición
	FuncionDeTransicion(ArrayList<String> func) {
		cinta = func.get(1);
		pila = func.get(2);
		siguiente = func.get(3);
		for (int i = 4; i < func.size(); i++) {
			if (func.get(i) != ".") {
				escPila.add(func.get(i));
			}
		}
	}

	public String getCinta() {
		return cinta;
	}

	public String getPila() {
		return pila;
	}

	public String getSiguiente() {
		return siguiente;
	}

}
