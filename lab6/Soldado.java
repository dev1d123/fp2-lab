public class Soldado{
	private String nombre;
	private int vida;
	private int fila;
	private char columna;
	public Soldado(String n, int f, char c){
		nombre = n;
		fila = f;
		columna = c;
		vida = (int)(Math.random()*5 + 1);
	}
	public int getVida(){
		return vida;
	}
	public String getNombre(){
		return nombre;
	}
	public String toString(){
		return ("NOMBRE: " + nombre + "\nFILA: " + fila + "\nCOLUMNA: " + columna + "\nVIDA: " + vida);
	}
}
