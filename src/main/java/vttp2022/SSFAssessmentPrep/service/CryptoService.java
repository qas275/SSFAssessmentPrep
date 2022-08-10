package vttp2022.SSFAssessmentPrep.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp2022.SSFAssessmentPrep.model.Crypto;

@Service
public class CryptoService {
    
    private static Logger logger = LoggerFactory.getLogger(CryptoService.class);

    @Value("${crypto_api_key}")
    private String apiKey;

    private static String URL = "https://min-api.cryptocompare.com/data/pricemulti";

    public Optional<Crypto> convertCrypto(Crypto Req){
        String toSendURL = UriComponentsBuilder.fromUriString(URL).queryParam("fsyms", Req.getCryptoString())
                                                .queryParam("tsyms", Req.getCurrencyString())
                                                .queryParam("api_key", apiKey)
                                                .toUriString();
        logger.info(toSendURL);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;
        try {
            resp = template.exchange(toSendURL, HttpMethod.GET, null, String.class, 1);
            logger.info(resp.getBody());
            Crypto convertedCrypto = Crypto.createJson(resp.getBody());
            convertedCrypto.setCryptoString(Req.getCryptoString());
            return Optional.of(convertedCrypto);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
