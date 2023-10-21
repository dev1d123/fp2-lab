public class Soldado{
	private String nombre;
	private int nivelAtaque;
	private int nivelDefensa;
	private int nivelVida;
	private int vidaActual;
	private int velocidad;
	private int fila;
	private int columna;
	private String actitud;
	private boolean vive;

	public Soldado(String nombre, int fila, int columna){
		this.nombre = nombre;
		this.fila = fila;
		this.columna = columna;
		int vAleat = (int)(Math.random() * 5 + 1);
		vidaActual = vAleat;
		vAleat = (int)(Math.random() * 5 + 1);
		nivelAtaque = vAleat;
		vAleat = (int)(Math.random() * 5 + 1);
		nivelDefensa = vAleat;
		actitud = "defensiva";
		vive = true;
	}
	public void atacar(){
		actitud = "ofensiva";
		avanzar();
		velocidad++;
	}
	public void defender(){
		actitud = "defensiva";
		velocidad = 0;
	}
	public void avanzar(){
	
	}
	public void retroceder(){
		if(velocidad > 0){
			defender();
		}else{
			velocidad--;
		}
	}
	public void serAtacado(){
		vidaActual--;
		if(vidaActual <= 0){
			morir();
		}
	}
	public void huir(){
		actitud = "fuga";
	}
	public void morir(){
		vive = false;
	}
	public void setVidaActual(int n){
		vidaActual = n;
	}
	public int getVidaActual(){
		return vidaActual;
	}
	public boolean estaVivo(){
		return vive;
	}
	public String getNombre(){
		return nombre;
	}
	public int getFila(){
		return fila;
	}
	public int getColumna(){
		return columna;
	}
	public void setFila(int f){
		fila = f;
	}
	public void setColumna(int c){
		columna = c;
	}
	public String toString() {
		char[] aux = {'A','B','C','D','E','F','G','H','I','J'};
    	return "Nombre: " + nombre + "\n" +
          	 	"Nivel de Ataque: " + nivelAtaque + "\n" +
           		"Nivel de Defensa: " + nivelDefensa + "\n" +
           		"Vida Actual: " + vidaActual + "\n" +
           		"Velocidad: " + velocidad + "\n" +
          		"Fila: " + (fila+1) + "\n" +
           		"Columna: " + aux[columna] + "\n" +
           		"Actitud: " + actitud + "\n" +
           		"Vive: " + vive + "\n";
	}
}
