public class Soldado{
	private String nombre;
	private int nivelAtaque;
	private int nivelDefensa;
	private int vidaActual;
	private int velocidad;
	private String actitud = "defensiva";
	private boolean vive = true; 

	private int fila;
	private int columna;
	
	public Soldado(String n, int f, int c){
		this.nombre = n;
		this.fila = f;
		this.columna = c; 
		int numeroAleatorio = (int)(Math.random() * 5 + 1);
		nivelAtaque = numeroAleatorio;
		numeroAleatorio = (int)(Math.random() * 5 + 1);
		nivelDefensa = numeroAleatorio;
		velocidad = 0;
	}
	public Soldado(String n, int f, int c, int v, int a, int d){
		this(n,f,c);
		vidaActual = v;
		nivelAtaque = a;
		nivelDefensa = d;
	}

	public Soldado(){}

	public void atacar(){
		avanzar();
	}
	public void defender(){
		actitud = "defensiva";
	}
	public void avanzar(){
		velocidad++;
	}
	public void retroceder(){
		if(velocidad > 0){
			velocidad = 0;
			actitud = "defensiva";
		}else{
			velocidad--;
		}
	}
	public void serAtacado(int daño){
		vidaActual-=daño;
		if(vidaActual <= 0){
			morir();
		}
	}
	public void huir(){
		velocidad+=2;
	}
	public void morir(){
			vive = false;
	}
	public void setVidaActual(int v){
		this.vidaActual = v;	
	}
	public int getVidaActual(){
		return vidaActual;
	}

	public void setFila(int f){
		this.fila = f;	
	}
	public int getFila(){
		return fila;
	}
	public void setColumna(int c){
		this.columna = c;	
	}
	public int getColumna(){
		return columna;
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
	public boolean estaVivo(){
		return vive;
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

