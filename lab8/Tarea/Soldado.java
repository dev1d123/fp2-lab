public class Soldado{
	private String nombre;
	private int vida;
	private int fila;
	private char columna;
	public Soldado(String n, int v, int f, char c){
		nombre = n;
		vida = v;
		fila = f;
		columna = c;
	}
	public int getVida(){
		return vida;
	}
	public int getFila(){
		return fila;
	}
	public char getColumna(){
		return columna;
	}
	public String toString() {
        return "Nombre: " + nombre + "\n" +
               "Vida: " + vida + "\n" +
               "Fila: " + fila + "\n" +
               "Columna: " + columna;
    }

}
