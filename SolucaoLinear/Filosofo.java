package SolucaoLinear;

import java.util.concurrent.Semaphore;

public class Filosofo {

    private int idFilosofo;

    private Semaphore lFork, rFork;

    private int qtdFilosofos;

    private boolean emAcao;

    public Filosofo(int idFilosofo, Semaphore rFork, Semaphore lFork, int qtdFilosofos) {
        this.idFilosofo = idFilosofo;

        this.rFork = rFork;
        this.lFork = lFork;

        this.qtdFilosofos = qtdFilosofos;
    }

    public void pensa() {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }

    public void janta() {

        try {
            lFork.acquire();
            rFork.acquire();
        } catch (InterruptedException ie) {
        }

        PrintaAcaoUtil.printaAcao(idFilosofo, false, qtdFilosofos);
        Semaphore sEmAcao = new Semaphore(0);
        emAcao = true;

        new Thread(() -> {
            while (emAcao) {
                PrintaAcaoUtil.printFazendoAcao(idFilosofo, false, qtdFilosofos);
            }

            PrintaAcaoUtil.printFimAcao(idFilosofo, false, qtdFilosofos);
            sEmAcao.release();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                emAcao = false;
            }
            emAcao = false;
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
