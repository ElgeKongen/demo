package com.example.demo.service;

import com.example.demo.client.CountyClient;
import com.example.demo.model.CountyInfo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service // Gjør klassen tilgjengelig som en Spring-bean for dependency injection
public class CountyService {

    private final CountyClient countyClient;

    // Dependency injection av client – følger clean architecture og gjør testing lettere
    public CountyService(CountyClient countyClient) {
        this.countyClient = countyClient;
    }

    // Henter fylkesnavn for gitt fylkesnummer via ekstern API-klient
    public String getCountyName(String countyNumber) {
        return countyClient.fetchCountyName(countyNumber);
    }

    // Returnerer liste over alle fylker – også hentet fra ekstern API. Gjorde dette for morro. Har ikke noe med casen å gjøre.
    public List<CountyInfo> getAllCounties(){
        return countyClient.fetchAllCounties();
    }
}

