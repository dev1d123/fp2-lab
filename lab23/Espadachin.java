public class Espadachin extends Soldado {
    protected int longitudEspada;
    public Espadachin(String nombre, int fila, int columna, int reino){
        super(nombre, fila, columna, reino);
        nivelAtaque = 10;
        nivelDefensa = 8;
        int numeroAleatorio = (int)(Math.random() * 3 + 8);
		setVidaActual(numeroAleatorio);
        longitudEspada = (int)(Math.random()*10 + 1);
    }
    public void crearMuroEscudos(){
        defender();
    }
    public String impresionTabla(){
        return "E"+this.getVidaActual();
    }
    public String toString(){
        return "Nombre: " + nombre + "\n" +
        "Nivel de Ataque: " + nivelAtaque + "\n" +
        "Nivel de Defensa: " + nivelDefensa + "\n" +
        "Vida Actual: " + vidaActual + "\n";
    }
}


