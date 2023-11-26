public class Caballero extends Soldado {
    private boolean modoLanza = true;
    private boolean modoEspada = false;
    private boolean montar = true;
    public Caballero(String nombre, int fila, int columna){
        super(nombre, fila, columna);
        nivelAtaque = 13;
        nivelDefensa = 7;
        int numeroAleatorio = (int)(Math.random() * 3 + 10);
		setVidaActual(numeroAleatorio);
    }
    public void desmontar(){
        if(montar){
            defender();
            modoEspada = true;
            modoLanza = false;
        }else{
            System.out.println("El caballero ya esta desmontado");
        }
    }
    public void montar(){
        if(!montar){
            montar = true;
            modoEspada = false;
            modoLanza = true;
            envestir();
        }else{
            System.out.println("El caballero ya esta desmontado");
        }
    }
    public void envestir(){
        if(montar){
            atacar();
            atacar();
        }else{
            atacar();
            atacar();
            atacar();
        }
    }
    public String impresionTabla(){
        return "C"+this.getVidaActual();
    }
    public String toString(){
        return "Nombre: " + nombre + "\n" +
        "Nivel de Ataque: " + nivelAtaque + "\n" +
        "Nivel de Defensa: " + nivelDefensa + "\n" +
        "Vida Actual: " + vidaActual + "\n";
    }
}


