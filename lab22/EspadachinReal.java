public class EspadachinReal extends Espadachin implements UnidadEspecial{
    private int cuchillos = 10;
    private int nivelEvolucion = 1; 
    public EspadachinReal(String nombre, int fila, int columna, int reino){
        super(nombre, fila, columna, reino);
		setVidaActual(12);
    }

    public String impresionTabla(){
        return "ER"+this.getVidaActual();
    }

    public void lanzar(){
        if(cuchillos > 0){
            atacar();
            cuchillos--;
        }else{
            System.out.println("No se puede atacar");
        }
    }
    public void evolucionar(){
        if(nivelEvolucion <= MAX_LEVEL){
            nivelEvolucion++;
            cuchillos+=nivelEvolucion*10;
        }else{
            System.out.println("Maximo nivel");
        }
    }
}