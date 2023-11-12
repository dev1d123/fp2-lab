public class Soldado{
	private String nombre;
	private int nivelAtaque;
	private int nivelDefensa;
	private int vidaActual;

	public Soldado(String n, int v, int a, int d){
		this.nombre = n;
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
