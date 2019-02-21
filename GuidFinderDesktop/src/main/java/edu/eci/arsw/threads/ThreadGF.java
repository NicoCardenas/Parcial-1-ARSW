package edu.eci.arsw.threads;

import edu.eci.arsw.GuidFinderDesktop.GuidFinder;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadGF implements Runnable{
    
    private Thread thread;
    private String name;
    private FileInputStream fi;
    private ObjectInputStream oi;
    private UUID[] guids;
    public final static AtomicBoolean active = new AtomicBoolean(true);
    

    public ThreadGF(String name, FileInputStream file) throws FileNotFoundException, IOException {
        fi = file;
        oi = new ObjectInputStream(fi);
        this.name = name;
        thread = new Thread(this, this.name);
    }        

    @Override
    public void run() {
        while (true) {            
            try {
                synchronized (active){
                    while (active.get()) {                        
                        guids = (UUID[]) oi.readObject();
                        active.wait();
                    }
                }
                Thread.sleep(10000);
                active.notifyAll();
            } catch (IOException | ClassNotFoundException | InterruptedException ex) {
                Logger.getLogger(ThreadGF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }        

    public UUID[] getGuids() {
        return guids;
    }

    public ObjectInputStream getOi() {
        return oi;
    }        
    
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
}
