package thread01;

public class Fune {

    private int posizione;
    private int vittorieTp0;
    private int vittorieTp1;

    private static Fune partita;

    private Fune() {
        posizione = 0;
        vittorieTp0 = 0;
        vittorieTp1 = 0;
    }

    public static Fune getInstance() {
        if (partita == null) {
            partita = new Fune();
        }
        return partita;
    }

    public synchronized void tira(int forza) {

        posizione += forza;
    }

    public synchronized void turno(int tp, int forza) {
        if (tp == 0) {
            System.out.println("Turno di tp0");
            if (posizione >= 10) {
                hasWin(1);
                posizione = 0;
                notifyAll();
            } else {
                tira(forza * -1);
                if (posizione <= -10) {
                    try {
                        System.out.println("Tp0 in wait, aspettando che Tp1 lo svegli al prossimo turno.");
                        wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }
        if (tp == 1) {
            System.out.println("\t\tTurno di tp1");

            if (posizione <= -10) {
                hasWin(0);
                posizione = 0;
                notifyAll();
            } else {
                tira(forza);
                if (posizione >= 10) {
                    try {
                        System.out.println("Tp1 in wait, aspettando che Tp0 lo svegli al prossimo turno.");
                        wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }
    }

    public synchronized void hasWin(int who) {
        if (who == 1) {
            System.out.println("\t\t\t\tHa vinto tp1");
            vittorieTp1++;
        }

        if (who == 0) {
            System.out.println("\t\t\t\tHa vinto tp0");
            vittorieTp0++;
        }
    }

    public synchronized int getVittorieTp0() {
        return vittorieTp0;
    }

    public synchronized int getVittorieTp1() {
        return vittorieTp1;
    }

}
