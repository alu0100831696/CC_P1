package automataConPila;

/**
 * @author Sof�a Pizarro - alu0100831696
 * @category CC - Pr�ctica1 - Aut�mata con Pila
 * @date 22/10/2017
 * @class Transicion
 **/

public class Transicion {
	private FuncionDeTransicion ftran = null;
	private int cinta = 0;
	private Pila pila = null;

	Transicion(Pila p, int c, FuncionDeTransicion n) {
		ftran = n;
		cinta = new Integer(c);
		pila = p;
	}

	public FuncionDeTransicion getFtran() {
		return ftran;
	}

	public int getCinta() {
		return cinta;
	}

	public Pila getPila() {
		return pila;
	}
}

