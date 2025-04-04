package com.example.demo.model;

//bare for morro skyld, har ikkenoe med casen å gjøre
public class CountyInfo {
    private String fylkesnummer;
    private String fylkesnavn;

    public String getFylkesnummer(){
        return fylkesnummer;
    }

    public void setFylkesnummer(String fylkesnummer){
        this.fylkesnummer = fylkesnummer;
    }

    public String getFylkesnavn(){
        return fylkesnavn;
    }

    public void setFylkesnavn(String fyklesnavn){
        this.fylkesnavn = fyklesnavn;
    }
}


