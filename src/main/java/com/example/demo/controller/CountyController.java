package com.example.demo.controller;

import com.example.demo.model.CountyInfo;
import com.example.demo.service.CountyService;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//API paths bør være substantiv i flertall, counties istedet for county 
@RestController
@RequestMapping("/county")
public class CountyController {

    private final CountyService countyService;

    public CountyController(CountyService countyService) {
        this.countyService = countyService;
    }

    // GET /county/{countyNumber} – Returnerer fylkesnavn som ren tekst
    @GetMapping(value = "/{countyNumber}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getCountyName(@PathVariable String countyNumber) {
        try {
            String name = countyService.getCountyName(countyNumber);
            if (name == null || name.equalsIgnoreCase("Ukjent fylke")) {
                return ResponseEntity.status(404).body("Fylke ikke funnet");
            }
            return ResponseEntity.ok(name);
        } catch (Exception e) {
            return ResponseEntity.status(502).body("Feil ved henting av fylkesdata");
        }
    }

    // GET /county – Henter alle fylker som JSON
    // Ikke en del av oppgaven, men nyttig for testing og utvidelse
    @GetMapping
    public ResponseEntity<?> getAllCounties() {
        try {
            List<CountyInfo> counties = countyService.getAllCounties();
    
            if (counties == null || counties.isEmpty()) {
                return ResponseEntity.status(204).body("Ingen fylker funnet");
            }
    
            return ResponseEntity.ok(counties);
        } catch (Exception e) {
            return ResponseEntity.status(502).body("Feil ved henting av fylkesliste");
        }
    }
    
}
