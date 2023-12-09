public class CaballeroMoro extends Caballero implements UnidadEspecial{
    private int flechas = 10;
    private int nivelEvolucion = 1; 
    public CaballeroMoro(String nombre, int fila, int columna, int reino){
        super(nombre, fila, columna, reino);
		setVidaActual(13);
    }
    public String impresionTabla(){
        return "CM"+this.getVidaActual();
    }
    public void lanzar(){
        if(flechas > 0){
            atacar();
            flechas--;
        }else{
            System.out.println("No se puede atacar");
        }
    }
    public void evolucionar(){
        if(nivelEvolucion <= MAX_LEVEL){
            nivelEvolucion++;
            flechas+=nivelEvolucion*10;

        }else{
            System.out.println("Maximo nivel");
        }
    }
    public void envestir(){
        if(montar){
            for(int i = 0 ; i < nivelEvolucion + 2; i++){
                atacar();
            }
        }else{
            for(int i = 0 ; i < nivelEvolucion + 4; i++){
                atacar();
            }
        }
    }
}

