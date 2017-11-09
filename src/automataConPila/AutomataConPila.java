package automataConPila;

/**
 * @author Sofía Pizarro - alu0100831696
 * @category CC - Práctica1 - Autómata con Pila
 * @date 22/10/2017
 * @class AutomataConPila
 **/
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class AutomataConPila {
	
	private Hashtable<String, EstadosQ> estados = new Hashtable<String, EstadosQ>();
	private EstadosQ estadoAct;
	private Cinta cinta;
	private Pila pila;
	private Stack<Transicion> transcn = new Stack<Transicion>();
	private int estadoFin = 0;

	//Constructor
	//simula contendrá el programa que definirá al autómata
	AutomataConPila(ArrayList<ArrayList<String>> simula) {
		
		for (String i : simula.get(0)) {						//estados
			estados.put(i, new EstadosQ(i));
		}
		
		cinta = new Cinta(simula.get(1));						//cinta
		pila = new Pila(simula.get(2));							//pila
		estadoAct = estados.get(simula.get(3).get(0));
		pila.insertar(simula.get(4));							//programa
		
		for (String estado : simula.get(5)) {
			if (!estado.equals(".")) {
				estados.get(estado).setFinal();
				estadoFin++;
			}
		}
		
		for (int i = 6; i < simula.size(); i++) {
			
			estados.get(simula.get(i).get(0)).insertarTranscn(simula.get(i));
		}
	}

	//Ejecuta el programa, le pasamos como primer argumento el fichero y como segundo argumento si queremos o no realizar la traza del programa
	public boolean ejecutar(String cadena, boolean traza) {
		
		cinta.setCinta(cadena); //Añadir cadena a la cinta
		boolean ejecutando = true;
		boolean aceptada = false;
		
		while (ejecutando) {
			ArrayList<String> transcnPos = new ArrayList<String>();
			// Comprobamos condiciones de aceptación
			if ((estadoAct.esFinal() && cinta.puntero() < 0) || (cinta.puntero() < 0 && pila.siVacia() && estadoFin == 0)) {
				ejecutando = false;
				aceptada = true;
				if (traza) {
					traza(transcnPos);
				}
				break;
			} else {
				// Exploramos las posibles transiciones
				if (cinta.puntero() >= 0 && !pila.siVacia()) {
					for (FuncionDeTransicion t : estadoAct.explorar(cinta.read(), pila.consultar())) {
						transcnPos.add(t.getSiguiente());
						transcn.push(new Transicion(new Pila(pila), cinta.puntero(), t));
					}
				} else {
					if (!pila.siVacia()) {
						for (FuncionDeTransicion t : estadoAct.explorar(pila.consultar())) {
							transcnPos.add(t.getSiguiente());
							transcn.push(new Transicion(new Pila(pila), cinta.puntero(), t));
						}
					}
				}
			}
			if (traza) {
				traza(transcnPos);
			}
			// Si no hay transiciones posibles cambiamos de estado y si no hay posibilidades rechazamos la cadena. 
			if (!transcn.isEmpty()) {
				Transicion transcnAct = transcn.pop();
				pila = transcnAct.getPila();
				FuncionDeTransicion act = transcnAct.getFtran();
				pila.extraer();
				pila.insertar(act.escPila);
				cinta.setPuntero(transcnAct.getCinta());
				if (!act.getCinta().equals(".")) {
					cinta.punteroDec();
				}
				estadoAct = estados.get(act.getSiguiente());
			} else {
				ejecutando = false;
				aceptada = false;
				break;
			}
		}
		return aceptada;

	}
	
	// Traza
	private void traza(ArrayList<String> transcnPos) {
		String aux = "";
		for (String item : transcnPos) {
			aux += " " + item;
		}
		System.out.println(estadoAct.getId() + " ||" + cinta.getCinta() + " ||" + pila.getPila() + " ||" + aux);
		System.out.println("----------------------");
	}
}
