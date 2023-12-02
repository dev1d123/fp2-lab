public class EspadachinTeutonico extends Espadachin implements UnidadEspecial{
    private int jabalina = 10;
    private int nivelEvolucion = 1; 
    public EspadachinTeutonico(String nombre, int fila, int columna, int reino){
        super(nombre, fila, columna, reino);
        //se les modifica el nombre aqui
		setVidaActual(13);
    }
    public String impresionTabla(){
        return "ET"+this.getVidaActual();
    }

    public void lanzar(){
        if(jabalina > 0){
            atacar();
            jabalina--;
        }else{
            System.out.println("No se puede atacar");
        }
    }
    public void evolucionar(){
        if(nivelEvolucion <= MAX_LEVEL){
            nivelEvolucion++;
            jabalina+=nivelEvolucion*10;

        }else{
            System.out.println("Maximo nivel");
        }
    }
}
