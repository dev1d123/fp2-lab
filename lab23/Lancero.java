public class Lancero extends Soldado{
    private int longitudLanza;
    public Lancero(String nombre, int fila, int columna, int reino){
        super(nombre, fila, columna, reino);
        nivelAtaque = 5;
        nivelDefensa = 10;
        int numeroAleatorio = (int)(Math.random() * 4 + 5);
		setVidaActual(numeroAleatorio);
        longitudLanza = (int)(Math.random()*10 + 1);
    }
    public void schiltrom(){
        setNivelDefensa(getNivelDefensa()+1);
    }
    public String impresionTabla(){
        return "L"+this.getVidaActual();
    }
    public String toString(){
        return "Nombre: " + nombre + "\n" +
        "Nivel de Ataque: " + nivelAtaque + "\n" +
        "Nivel de Defensa: " + nivelDefensa + "\n" +
        "Vida Actual: " + vidaActual + "\n";
    }
}
    
