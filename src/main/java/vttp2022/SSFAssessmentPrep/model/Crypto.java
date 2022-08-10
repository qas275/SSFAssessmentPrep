package vttp2022.SSFAssessmentPrep.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Crypto {
    private static String cryptoString;
    private static String currencyString;
    private static String convertedResult;
    private static Map<String, String> cryptoMultiResult;
    private static List<String> currencies = new LinkedList<>();

    public List<String> getCurrencies() {
        return currencies;
    }
    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    private static Logger logger = LoggerFactory.getLogger(Crypto.class);
    
    public Map<String, String> getCryptoMultiResult() {
        return cryptoMultiResult;
    }
    public void setCryptoMultiResult(Map<String, String> cryptoMultiResult) {
        this.cryptoMultiResult = cryptoMultiResult;
    }
    public String getConvertedResult() {
        return convertedResult;
    }
    public void setConvertedResult(String convertedResult) {
        this.convertedResult = convertedResult;
    }
    public String getCurrencyString() {
        return currencyString;
    }
    public void setCurrencyString(String currencyString) {
        this.currencyString = currencyString;
    }

    public String getCryptoString() {
        return cryptoString;
    }
    public void setCryptoString(String cryptoString) {
        this.cryptoString = cryptoString;
    }

    public static Crypto createJson(String json) throws IOException{
        Crypto fromAPICrypto = new Crypto();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject jObj = r.readObject();
            if(currencyString.contains(",")){
                String[] currencyArr = currencyString.split(",");
                for(int i=0; i<currencyArr.length;i++){
                    currencies.add(currencyArr[i]);
                    fromAPICrypto.cryptoMultiResult.put(currencyArr[i], jObj.get(currencyArr[i]).toString());
                }
            }
            fromAPICrypto.convertedResult = jObj.get(currencyString).toString();
            logger.info(fromAPICrypto.convertedResult);
        }
        return fromAPICrypto; 
    }

}
