package edu.eci.arsw.GuidFinderDesktop;

import edu.eci.arsw.threads.ThreadGF;
import edu.eci.arsw.threads.ThreadKey;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

public class GuidFinder {
	
	private static UUID[] guids; 
	
	
	public GuidFinder() throws Exception {
		getGuids();
	}
	
	public static UUID[] getGuids() throws Exception 
	{
	
            ThreadKey listener = new ThreadKey();
            listener.run();
                    
		if(guids==null){
			System.out.println("es nulo");
		FileInputStream fi;
                
                    fi = new FileInputStream(new File("guids.eci"));
                    
                byte[] temp = ThreadGF.serialize(fi);
                    
                ThreadGF t1 = new ThreadGF("1", (FileInputStream) ThreadGF.deserialize(temp));
                ThreadGF t2 = new ThreadGF("2", (FileInputStream) ThreadGF.deserialize(temp));
                ThreadGF t3 = new ThreadGF("3", (FileInputStream) ThreadGF.deserialize(temp));
                ThreadGF t4 = new ThreadGF("4", (FileInputStream) ThreadGF.deserialize(temp));
                
                if (fi.available() != 0){
                    t1.run();
                    t2.run();
                    t3.run();
                    t4.run();
                }
		
		//ObjectInputStream oi = new ObjectInputStream(fi);
                UUID[] res = new UUID[t1.getGuids().length+t2.getGuids().length+t3.getGuids().length+t4.getGuids().length];
                int count = 0;
                
                for (int i = 0; i < t1.getGuids().length; i++) {
                    res[count] = t1.getGuids()[i];
                    count++;
                }
                for (int i = 0; i < t2.getGuids().length; i++) {
                    res[count] = t1.getGuids()[i];
                    count++;
                }
                for (int i = 0; i < t3.getGuids().length; i++) {
                    res[count] = t1.getGuids()[i];
                    count++;
                }
                for (int i = 0; i < t4.getGuids().length; i++) {
                    res[count] = t1.getGuids()[i];
                    count++;
                }
		
		guids = res;
	
		//oi.close();
                t1.getOi().close();
                t2.getOi().close();
                t3.getOi().close();
                t4.getOi().close();
		fi.close();
		}
		return guids;
		
	}
	
	public int countGuids(UUID guidToFind) 
	{
		
		int count=0;
		for (UUID uuid : guids) {
			if(uuid.equals(guidToFind))
					{
				count++;
					}
			
		}
		return count;
		
	}


}
