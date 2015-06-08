
public class Pila {
	
	private Nodo top; //CIMA DE LA PILA
	private Nodo top_c;
	
	public Pila(){
		top = null;
		top_c = null;
	}
	
	/**
	 * METODO PARA METER UN NUEVO ELEMENTO EN LA PILA
	 * @param e RECIBE UNA CADENA DE CARACTERES
	 */
	public void push(int e){
		Nodo nuevo = new Nodo(e);
		nuevo.siguiente = top;
		top = nuevo;
	}
	
	public void push_c(String e){
		Nodo nuevo = new Nodo(e);
		nuevo.siguientec = top_c;
		top_c = nuevo;
	}
	
	/**
	 * METODO PARA SACAR EL DATO QUE ESTA EN LA CIMA
	 * @return RETORNA EL DATO
	 */
	public int pop(){
		int temp = top.dato;
		top = top.siguiente;
		return temp;
	}
	
	public String pop_c(){
		String temp = top_c.caracter;
		top_c = top_c.siguientec;
		return temp;
	}

}
