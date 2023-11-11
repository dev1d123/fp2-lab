public class Soldado{
	private String nombre;
	private int nivelAtaque;
	private int nivelDefensa;
	private int vidaActual;
	private int fila;
	private int columna;
	public Soldado(String n, int f, int c, int v, int a, int d){
		this.nombre = n;
		this.fila = f;
		this.columna = c; 
		vidaActual = v;
		nivelAtaque = a;
		nivelDefensa = d;
	}

	public void setVidaActual(int v){
		this.vidaActual = v;	
	}
	public int getVidaActual(){
		return vidaActual;
	}
	public String getNombre(){
		return nombre;
	}
	public int getNivelAtaque(){
		return nivelAtaque;
	}
	public int getNivelDefensa(){
		return nivelDefensa;
	}
	public void setNivelAtaque(int n){
		nivelAtaque = n;
	}
	public void setNivelDefensa(int n){
		nivelDefensa = n;
	}
	public void setNombre(String n){
		nombre = n;
	}
}
