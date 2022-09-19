package SolucaoLinear;

import java.util.concurrent.Semaphore;

public class Jantar {
    private static final int FIL = 5;

    private static final int QTD_JANTAS = 5;

    public static void main(String[] args) {

        Semaphore[] forks = new Semaphore[FIL];
        Filosofo[] filosofos = new Filosofo[FIL];

        for (int i = 0; i < FIL; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < FIL; i++) {
            filosofos[i] = new Filosofo(i, forks[i], forks[(i + 1) % (FIL)], FIL);
        }


        long tempoInicial = System.currentTimeMillis();

        PrintaAcaoUtil.printaCabecalho();

        for(int i = 0; i < QTD_JANTAS; i ++){
            for (int f = 0; f < FIL; f++) {
                filosofos[f].janta();                 
            }
        }
        


        long tempoFinal = System.currentTimeMillis() - tempoInicial;

        System.out.println("Tempo decorrido foi de " + tempoFinal  + " milisegundos");

    }

}
