package edu.eci.arsw.Controllers;

import edu.eci.arsw.Entities.Data;
import edu.eci.arsw.Service.GuidFinderService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
@RequestMapping("/uuid")
public class GuidFinderControllerApi {
    
    @Autowired
    GuidFinderService gfs;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getSearch(){
        try {            
            return new ResponseEntity<>(gfs.getData(),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(GuidFinderControllerApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/guids")
    public ResponseEntity<?> getGuids(){
        try {            
            return new ResponseEntity<>(gfs.getGuid(),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(GuidFinderControllerApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> postSearch(@RequestBody String guid){
        try {
            //registrar dato
            gfs.setSearch(guid, gfs.countGuidFinder(guid));            
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(GuidFinderControllerApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);            
        }
    }
    
}
