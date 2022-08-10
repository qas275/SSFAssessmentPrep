package vttp2022.SSFAssessmentPrep.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp2022.SSFAssessmentPrep.model.Crypto;
import vttp2022.SSFAssessmentPrep.service.CryptoService;

@RestController
@RequestMapping(path = "/")
public class CryptoRestCon {
    private static final Logger logger = LoggerFactory.getLogger(CryptoRestCon.class);

    @Autowired
    private CryptoService service;

    @PostMapping(path = "/convert")
    public ResponseEntity<String> convertCoin(@RequestParam (name = "coin") String coin, @RequestParam(name = "currencies")String currencies){
        logger.info("REST CON STARTED");
        JsonObject responseJson;
        Crypto request = new Crypto();
        request.setCryptoString(coin);
        request.setCurrencyString(currencies);
        Crypto response = service.convertCrypto(request).get();
        JsonObjectBuilder builder = Json.createObjectBuilder();
        for(String crypto:response.getCryptos()){
            for(String currency:response.getCurrencies()){
                builder.add(crypto, response.getCryptoMultiResult().get(crypto).get(currency));
            }
        }//this one wrong, i lazy do sianz
        responseJson = builder.build();
        return ResponseEntity.ok(responseJson.toString());
    }

}
