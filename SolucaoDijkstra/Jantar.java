package SolucaoDijkstra;

import java.util.concurrent.Semaphore;

public class Jantar {
    private static final int FIL = 5;

    private static final int QTD_JANTAS = 5;

    public static void main(String[] args) {

        Semaphore mutex = new Semaphore(1);

        Semaphore[] forks = new Semaphore[FIL];
        Filosofo[] filosofos = new Filosofo[FIL];

        for (int i = 0; i < FIL; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < FIL; i++) {
            filosofos[i] = new Filosofo(i, mutex, forks[i], forks[(i + 1) % (FIL)], QTD_JANTAS, FIL);
        }


        long tempoInicial = System.currentTimeMillis();

        PrintaAcaoUtil.printaCabecalho();

        for (int i = 0; i < FIL; i++) {
            filosofos[i].start();
        }

        for (int i = 0; i < FIL; i++) {
            try {
                filosofos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        


        long tempoFinal = System.currentTimeMillis() - tempoInicial;

        System.out.println("Tempo decorrido foi de " + tempoFinal  + " milisegundos");

    }

}
