package SolucaoLinear;

import java.util.concurrent.Semaphore;

public class Filosofo {

    private Semaphore lFork, rFork;

    public Filosofo(Semaphore rFork, Semaphore lFork) {
        this.rFork = rFork;
        this.lFork = lFork;
    }

    public void pensa() {        
        try { Thread.sleep(50); } 
        catch (InterruptedException e) { }
    }

    public void janta(){
        try{ rFork.acquire(); }
        catch(InterruptedException ie){}    

        try{ lFork.acquire(); }
        catch(InterruptedException ie){}

        try { Thread.sleep(100); } 
        catch (InterruptedException e) { }

        rFork.release();
        lFork.release();
    }

}
