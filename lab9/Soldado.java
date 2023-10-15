public class Soldado{
	private String nombre;
	private int nivelAtaque;
	private int nivelDefensa;
	private int vidaActual;
	private int velocidad;
	private int fila;
	private int columna;
	private String actitud;
	private boolean vive;
	
	public Soldado(String n, int v, int f, int c){
		nombre = n;
		vidaActual = v;
		fila = f;
		columna = c;
		velocidad = 0;
		actitud = "Defensiva";
		vive = true;
	}
	public Soldado() {
        nombre = "Sin Nombre";
        vidaActual = 100;
        fila = 0;
        columna = 0;
        velocidad = 0;
		vive = true;
    }
    public Soldado(String n, int v) {
        nombre = n;
        vidaActual = v;
        fila = 0;
        columna = 0;
		vive = true;
        velocidad = 0;
    }
	public void atacar(Soldado[] ejercitoAmigo, Soldado[] ejercitoEnemigo){
		actitud = "Ofensiva";
		//El soldado solo puede atacar si tiene un enemigo delante, se busca a ese enemigo.
		//Esto, luego de realizar su respectivo avance
		
		avanzar(ejercitoAmigo, ejercitoEnemigo);

		for(Soldado enemigo: ejercitoEnemigo){
			if (enemigo.getFila() == this.fila + 1 && enemigo.getColumna() == columna) {
            	//Se produce el ataque, invocando al metodo ser atacado, cuyo argumento es el soldado afectado
				System.out.println("El soldado: ");
				System.out.println(this);
				System.out.println("Ataca a: : ");
				System.out.println(enemigo);
				enemigo.serAtacado(nivelAtaque);
				return;
			}
		}
		System.out.println("No se encontro enemigo");

	}
	public void defender(){
		actitud = "Defensiva";
		velocidad = 0;
		//Se muestra al usuario un mensaje sobre lo que ocurrio.
		System.out.println("El soldado " + this.nombre + " se defiende");
	}
	public void avanzar(Soldado[] ejercitoAmigo, Soldado[] ejercitoEnemigo){
		velocidad++;
		int nuevaFila = this.fila + velocidad;
		//Primero verificamos si la nuevaFila se sale del tablero
		if(nuevaFila > 9){
			nuevaFila = 9;
		}
		//Primero se verifica si en la nueva fila hay un enemigo o amigo, si ese es el caso, el soldado se queda frente a el
		//Si un soldado posee mayor velocidad que el enemigo puede pasarlo.
		for (Soldado enemigo : ejercitoEnemigo) {
         	if (enemigo.getFila() == nuevaFila && enemigo.getColumna() == columna) {
            	// El soldado ha encontrado un enemigo, se queda frente a frente.
               	velocidad = 0;
				nuevaFila--;
			}
        }
		//Segundo verificamos al amigo.
		for (Soldado amigo : ejercitoAmigo) {
         	if (amigo.getFila() == nuevaFila && amigo.getColumna() == columna) {
                // El soldado ha encontrado un amigo, se queda frente a frente.
               	velocidad = 0;
				nuevaFila--;
        	}
		}
		//Se actualiza su posicion en el tablero
		this.fila = nuevaFila;
		//Se muestra al usuario un mensaje sobre lo que ocurrio.
		System.out.println("El soldado avanzo sus nuevos datos son: ");
		System.out.println(this);
	}
	public void retroceder(Soldado[] ejercitoAmigo){
		if(velocidad > 0){
			defender();
		}else{
			velocidad=-1;
			int nuevaFila = this.fila + velocidad;
			//Verificamos si hay un soldado amigo, si ocurre entonces el soldado no puede avanzar por ahi
			for (Soldado amigo : ejercitoAmigo) {
         		if (amigo.getFila() == nuevaFila && amigo.getColumna() == columna) {
                	// El soldado ha encontrado un amigo, se queda frente a frente.
               		velocidad = 0;
					nuevaFila--;
				}
        	}
			//Verificamos si nuevaFila se sale del tablero (<0), entonces la nuevaFila es 0.
			if(nuevaFila < 0){
				nuevaFila = 0;
			}
			//Se actualiza su posicion en el tablero
			this.fila = nuevaFila;
		}
		//Se muestra al usuario un mensaje sobre lo que ocurrio.
		System.out.println("El soldado retrocedio sus nuevos datos son: ");
		System.out.println(this);
	}
	public void serAtacado(int nAtaqueEnemigo){
		//Las mecanicas del ataque funcionan de la siguiente manera
		int daño = Math.max(0, nAtaqueEnemigo - this.nivelDefensa);
		vidaActual-=daño;
		if(vidaActual <= 0){
			morir();
		}
		//Se muestra al usuario un mensaje sobre lo que ocurrio.
		System.out.println("El soldado: " + this.getNombre() + " es atacado. ");
		System.out.println(this);

	}
	public void huir(){
		actitud = "Fuga";
		//Como la velocidad, si es negativa se retrocede y si es positiva se avanza. Verificamos es
		if(velocidad > 0){
			velocidad +=2;
		}else{
			velocidad -= 2;
		}
		//Se muestra al usuario un mensaje sobre lo que ocurrio.

		System.out.println("El soldado: " + this.getNombre() + " huye");
	}
	public void morir(){
		vidaActual = 0;	
		vive = false;
		//Se muestra al usuario un mensaje sobre lo que ocurrio.
		System.out.println("El soldado: " + this.getNombre() + " muere");
	}
	public void setVidaActual(int v){
		vidaActual = v;
	}
	public int getVidaActual(){
		return vidaActual;
	}
	public void setNivelDefensa(int d){
		nivelDefensa = d;
	}
	public int getNivelDefensa(){
		return nivelDefensa;
	}
	public void setNivelAtaque(int a){
		nivelAtaque = a;
	}
	public int getNivelAtaque(){
		return nivelAtaque;
	}
	public int getFila(){
		return fila;
	}
	public int getColumna(){
		return columna;
	}
	public String getNombre(){
		return nombre;
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
