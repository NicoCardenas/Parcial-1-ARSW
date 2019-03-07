package edu.eci.arsw.GuidFinderAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GuidFinder {
    private static UUID[] guids;	
	
    public GuidFinder() throws Exception {
            getGuids();
    }

    public static UUID[] getGuids() throws Exception 
    {

        if(guids==null){
            System.out.println("es nulo");
                
            FileInputStream fi;

            fi = new FileInputStream(new File("guids.eci"));

            ObjectInputStream oi = new ObjectInputStream(fi);

            guids= (UUID[]) oi.readObject();

            oi.close();
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
