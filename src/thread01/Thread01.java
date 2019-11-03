package thread01;
/**
 * @author Pierpaolo Pecoraio X81000501
 */
public class Thread01 {
    
    static final int VITTORIE = 10;
    static final int TIME_SLEEP = (VITTORIE*1000)/2; // Per esempio se le vittorie per terminare sono settate a 10, lui controllerà ogni 5 secondi
    
    public static void main(String[] args) {
        
        Thread tp0 = new Giocatore("tp0");
        Thread tp1= new Giocatore("tp1");
        
        tp0.start();
        tp1.start();
        
        while (true) {
            try {
                if (Fune.getInstance().getVittorieTp0() >= VITTORIE) {
                    System.out.println("Ha vinto Tp0 totalizzando "+Fune.getInstance().getVittorieTp0() +" vittorie contro le "+
                            Fune.getInstance().getVittorieTp1() +" di Tp1");
                    break;
                }
                if (Fune.getInstance().getVittorieTp1() >= VITTORIE) {
                    System.out.println("Ha vinto Tp1 totalizzando "+Fune.getInstance().getVittorieTp1() +" vittorie contro le "+
                            Fune.getInstance().getVittorieTp0() +" di Tp0");
                    break;
                }
                Thread.sleep(TIME_SLEEP); 
            } catch (InterruptedException ex) {
                ex.printStackTrace();    
            }
        }
        
        tp0.stop();
        tp1.stop();
        
    }
    
}