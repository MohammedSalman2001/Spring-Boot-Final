package com.nextravel.travelpackageserviceapi.api;


import com.nextravel.travelpackageserviceapi.dto.TravelPackageDTO;
import com.nextravel.travelpackageserviceapi.exception.DeleteFailException;
import com.nextravel.travelpackageserviceapi.exception.NotFoundException;
import com.nextravel.travelpackageserviceapi.exception.SaveFailException;
import com.nextravel.travelpackageserviceapi.exception.UpdateFailException;
import com.nextravel.travelpackageserviceapi.service.TravelPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/travel-package")
@CrossOrigin
public class TravelPackageApi {
    TravelPackageService service;
    public TravelPackageApi(TravelPackageService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity save(@RequestBody TravelPackageDTO obj){
        String save = null;
        try {
            save = String.valueOf(service.save(obj));
            return ResponseEntity.ok(save);
        } catch (SaveFailException e) {
            return new ResponseEntity("Operation Fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id){
        try {
            TravelPackageDTO travelPackageDTO = service.fidById(id);
            return ResponseEntity.ok(travelPackageDTO);
        }catch (NotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

   // @GetMapping("/{category:^REGULAR|MID-LEVEL|LUXURY|SUPER LUXURY$}")

    @GetMapping(params = "category")
    public ResponseEntity findAll(@RequestParam String  category){
        List<TravelPackageDTO> list  = service.findAllByCategory(category);
        return ResponseEntity.ok(list);
    }


    @GetMapping(path = "list")
    public ResponseEntity findAll(){
        List<TravelPackageDTO> all = service.findAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody TravelPackageDTO obj){
        try {
            service.update(obj);
            return ResponseEntity.ok().build();
        } catch (UpdateFailException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @DeleteMapping("/{id:^NEXT-\\d{5}$}")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id){
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (DeleteFailException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
