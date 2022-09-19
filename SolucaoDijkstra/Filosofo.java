package SolucaoDijkstra;

import java.util.concurrent.Semaphore;

public class Filosofo {

    private int idFilosofo;

    private Semaphore mutex;

    private Semaphore lFork, rFork;

    private int qtdFilosofos;

    private Boolean emAcao;

    public Filosofo(int idFilosofo, Semaphore mutex, Semaphore rFork, Semaphore lFork, int qtdFilosofos) {
        this.idFilosofo = idFilosofo;

        this.mutex = mutex;

        this.rFork = rFork;
        this.lFork = lFork;

        this.qtdFilosofos = qtdFilosofos;
    }

    public void pensa() {

        Semaphore sEmAcao = new Semaphore(0);

        PrintaAcaoUtil.printaAcao(idFilosofo, true, qtdFilosofos);

        emAcao = true;

        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                emAcao = false;                
            }
            emAcao = false;

            sEmAcao.release();
        }).start();

        new Thread(() -> {
            while (emAcao) {
                PrintaAcaoUtil.printFazendoAcao(idFilosofo, true, qtdFilosofos);
            }
            PrintaAcaoUtil.printFimAcao(idFilosofo, true, qtdFilosofos);
        }).start();

        try {
            sEmAcao.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void janta() {

        try {
            mutex.acquire();
            rFork.acquire();
            lFork.acquire();
            mutex.release(1);
        } catch (InterruptedException ie) {
        }

        Semaphore sEmAcao = new Semaphore(0);
        emAcao = true;

        PrintaAcaoUtil.printaAcao(idFilosofo, false, qtdFilosofos);

        new Thread(() -> {
            while (emAcao) {
                PrintaAcaoUtil.printFazendoAcao(idFilosofo, false, qtdFilosofos);
            }

            PrintaAcaoUtil.printFimAcao(idFilosofo, false, qtdFilosofos);            
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                emAcao = false;
            }
            emAcao = false;

            sEmAcao.release();
        }).start();



        try {
            sEmAcao.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rFork.release();
        lFork.release();
    }

}
