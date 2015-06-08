
public class Nodo {
	
	int dato;
	String caracter;
	
	Nodo siguiente;
	Nodo siguientec;
	
	/**
	 * NODO PARA ALMACENAR NUMEROS
	 * @param d RECIBE NUMERO
	 */
	public Nodo(int d){
		dato=d;
		siguiente=null;
	}
	
	/**
	 * NODO PARA ALMACENAR LOS OPERADORES
	 * @param c RECIBE OPERADOR
	 */
	public Nodo(String c){
		caracter = c;
		siguientec = null;
	}

}
