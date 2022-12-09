package com.example.bankmanagementsystem.models;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Converter {

    private Transaction transaction;
    private BankAccount bankAccount;


    public Converter(Transaction transaction, BankAccount bankAccount) {
        this.transaction = transaction;
        this.bankAccount = bankAccount;
    }

    public ResponseEntity convert(Integer sum){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.twelvedata.com/currency_conversion?symbol=" + transaction.getCurrencyShortname()+ "/" + bankAccount.getLimit() + "&amount=" + sum +"&apikey=82cdce29bc3b440eae41708f428c66ab";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Integer> jsonData = new HashMap<>();
        jsonData.getOrDefault("amount", transaction.getSum());
        HttpEntity<Map<String, Integer>> request = new HttpEntity<>(jsonData, headers);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, request);

        return  response;
    }
}
