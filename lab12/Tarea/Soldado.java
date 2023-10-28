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
	public Soldado(){
		nombre = "Todos";
		nivelAtaque = 0;
		nivelDefensa = 0;
		nivelVida = 0;
		fila = -1;
		columna = -1;
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

	public Soldado clonarSoldado() {
    		Soldado nuevoSoldado = new Soldado(nombre, fila, columna);
    		nuevoSoldado.nivelAtaque = nivelAtaque;
   		nuevoSoldado.nivelDefensa = nivelDefensa;
    		nuevoSoldado.nivelVida = nivelVida;
    		nuevoSoldado.vidaActual = vidaActual;
    		nuevoSoldado.velocidad = velocidad;
    		nuevoSoldado.actitud = actitud;
    		nuevoSoldado.vive = vive;
    		return nuevoSoldado;
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
	public void setNivelAtaque(int n){
		nivelAtaque = n;
	}
	public void setNivelDefensa(int n){
		nivelDefensa = n;
	}
	public int getNivelAtaque(){
		return nivelAtaque;
	}
	public int getNivelDefensa(){
		return nivelDefensa;
	}
	public int getNivelVida(){
		return nivelVida;
	}
	public void comparar(Soldado otroSoldado) {
	    System.out.println("Comparación de atributos con " + otroSoldado.getNombre() + ":");

	    if (!this.nombre.equals(otroSoldado.getNombre())) {
		System.out.println("Nombre: Diferente.");
	    } else {
		System.out.println("Nombre: Igual.");
	    }

	    if (this.nivelAtaque == otroSoldado.getNivelAtaque()) {
		System.out.println("Nivel de Ataque: Igual.");
	    } else {
		System.out.println("Nivel de Ataque: Diferente.");
	    }

	    if (this.nivelDefensa == otroSoldado.getNivelDefensa()) {
		System.out.println("Nivel de Defensa: Igual.");
	    } else {
		System.out.println("Nivel de Defensa: Diferente.");
	    }

	    if (this.vidaActual == otroSoldado.getVidaActual()) {
		System.out.println("Vida Actual: Igual.");
	    } else {
		System.out.println("Vida Actual: Diferente.");
	    }

	    if (this.vive == otroSoldado.estaVivo()) {
		System.out.println("Vive: Igual.");
	    } else {
		System.out.println("Vive: Diferente.");
	    }
	}
	public Soldado sumar(Soldado otroSoldado) {
		this.nivelAtaque += otroSoldado.nivelAtaque;
		this.nivelDefensa += otroSoldado.nivelDefensa;
		this.vidaActual += otroSoldado.vidaActual;
		this.velocidad += otroSoldado.velocidad;
		return this;
    	}

}
