public class Nave {
		private String nombre;
		private int vida;
		private int fila;
		private String columna;
		public Nave(String n, int v, int f, String c){
				nombre = n;
				vida = v;
				fila = f;
				columna = c;
		}
		public void setNombre( String n){
				nombre = n;
		}
		public void setVida(int v){
				vida = v;
		}
		public void setFila(int f){
				fila = f;
		}
		public void setColumna(String c){
				columna = c;
		}
		public String getNombre(){
				return nombre;
		}
		public int getVida(){
				return vida;
		}
		public int getFila(){
				return fila;
		}
		public String getColumna(){
				return columna;
		}
		public String toString(){
				return ("Nombre: " + nombre + "\nVida: " + vida + "\nFila: " + (fila+1) + "\nColumna: " + columna);
		}
}
