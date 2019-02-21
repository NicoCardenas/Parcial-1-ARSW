package edu.eci.arsw.threads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadKey implements Runnable, KeyListener {
        
    @Override
    public void run() {
        while (true) {            
            try {
                synchronized (ThreadGF.active){
                    while (ThreadGF.active.get()) {                        
                        ThreadGF.active.wait();
                    }
                }
                Thread.sleep(10000);
                ThreadGF.active.getAndSet(false);
                ThreadGF.active.notifyAll();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadKey.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        ThreadGF.active.getAndSet(true);
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        ThreadGF.active.getAndSet(true);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        ThreadGF.active.getAndSet(true);
    }
    
}
