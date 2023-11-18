public class Arquero extends Soldado{
    private int numFlechas;

    public Arquero(String nombre, int fila, int columna){
        super(nombre, fila, columna);
        int numeroAleatorio = (int)(Math.random() * 3 + 1);
		setVidaActual(numeroAleatorio);
        numFlechas = (int)(Math.random()* 1000);
    }
    public void disparar(){
        numFlechas--;
        if(numFlechas == 0){
            System.out.println("No se puede atacar");
        }else{
            atacar();
        }
        
    }
}
