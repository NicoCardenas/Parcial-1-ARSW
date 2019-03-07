package edu.eci.arsw.Service;

import edu.eci.arsw.Entities.Data;
import edu.eci.arsw.GuidFinderAPI.GuidFinder;
import edu.eci.arsw.Persistance.InMemory;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuidFinderService {
    
    @Autowired
    GuidFinder gf;
    
    @Autowired
    InMemory im;
    
    public UUID[] getGuid() throws Exception{
        return GuidFinder.getGuids();
    }
    
    public int countGuidFinder(String guid){
        UUID guidToFind = UUID.fromString(guid);
        int count = gf.countGuids(guidToFind);        
        return count;
    }    
    
    public Data getData(String guid){
        return im.getData(guid);
    }
    
    public List<Data> getData(){
        return im.getData();
    }
    
    public void setSearch(String guid, int count){
        Data temp = new Data(LocalDate.now().toString(), guid, count);
        im.saveSearch(temp);
    }
}
