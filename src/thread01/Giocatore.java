package thread01;

import java.util.Random;

public class Giocatore extends Thread {

    private final Random random;

    public Giocatore(String name) {
        super(name);
        random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                int recupero = (random.nextInt()&Integer.MAX_VALUE) % 3;
                int forza = (random.nextInt()&Integer.MAX_VALUE) % 5;
                Thread.sleep(recupero * 1000);
                if (super.getName().equals("tp0")) {
                    Fune.getInstance().turno(0, forza);
                } else {
                    Fune.getInstance().turno(1, forza);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}

