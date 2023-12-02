public class CaballeroFranco extends Caballero implements UnidadEspecial {
    private int lanzas = 10;
    private int nivelEvolucion = 1; 
    private boolean defTor=false;
    public CaballeroFranco(String nombre, int fila, int columna, int reino){
        super(nombre, fila, columna, reino);
		setVidaActual(15);
    }
    public String impresionTabla(){
        return "CF"+this.getVidaActual();
    }
    public void defensaTortuga(){
        defTor=!defTor;
        if(defTor = true){
            setNivelDefensa(getNivelDefensa() + nivelEvolucion);
        }else{
            setNivelDefensa(getNivelDefensa() - nivelEvolucion);
        }
    }
    public void lanzar(){
        if(lanzas > 0){
            atacar();
            lanzas--;
        }else{
            System.out.println("No se puede atacar");
        }
    }
    public void evolucionar(){
        if(nivelEvolucion <= MAX_LEVEL){
            nivelEvolucion++;
            lanzas+=nivelEvolucion*10;

        }else{
            System.out.println("Maximo nivel");
        }
    }
}


