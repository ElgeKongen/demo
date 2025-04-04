package com.example.demo.client;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.CountyInfo;


@Component
public class CountyClient {

    private final WebClient webClient;

    public CountyClient(WebClient webClient) {
        this.webClient = webClient;
    }

    // Henter alle fylker som en liste av CountyInfo-objekter
    public List<CountyInfo> fetchAllCounties(){
        return webClient.get()
            .uri("/fylker")
            .accept(org.springframework.http.MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(CountyInfo.class)
            .collectList()
            .block();
    }

    // Henter navnet på ett fylke basert på fylkesnummer
    public String fetchCountyName(String countyNumber) {
        return webClient.get()
        .uri("/fylker/{number}", countyNumber)
        .accept(org.springframework.http.MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(CountyResponse.class)
        .doOnError(error -> System.out.println("Feil ved henting av fylke: " + error.getMessage()))
        .map(CountyResponse::getFylkesnavn)
        .onErrorReturn("Ukjent fylke")
        .block();
    }

    // intern DTO for å parse JSON
    private static class CountyResponse {
        private String fylkesnavn;
    
        public String getFylkesnavn() {
            return fylkesnavn;
        }
    
        public void setFylkesnavn(String fylkesnavn) {
            this.fylkesnavn = fylkesnavn;
        }
    }
    
}
