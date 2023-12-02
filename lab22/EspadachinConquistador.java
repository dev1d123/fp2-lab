public class EspadachinConquistador extends Espadachin implements UnidadEspecial{
    private int hachas = 10;
    private int nivelEvolucion = 1; 
    public EspadachinConquistador(String nombre, int fila, int columna, int reino){
        super(nombre, fila, columna, reino);
		setVidaActual(14);
    }

    public String impresionTabla(){
        return "EC"+this.getVidaActual();
    }

    public void lanzar(){
        if(hachas > 0){
            atacar();
            hachas--;
        }else{
            System.out.println("No se puede atacar");
        }
    }
    public void evolucionar(){
        if(nivelEvolucion <= MAX_LEVEL){
            nivelEvolucion++;
            hachas+=nivelEvolucion*10;

        }else{
            System.out.println("Maximo nivel");
        }
    }
}
