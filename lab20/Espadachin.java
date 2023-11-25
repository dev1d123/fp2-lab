public class Espadachin extends Soldado {
    private int longitudEspada;
    public Espadachin(String nombre, int fila, int columna){

        super(nombre, fila, columna);

        int numeroAleatorio = (int)(Math.random() * 2 + 3);

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


