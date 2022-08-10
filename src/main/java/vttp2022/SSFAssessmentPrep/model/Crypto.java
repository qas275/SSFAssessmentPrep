package vttp2022.SSFAssessmentPrep.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import jakarta.json.JsonValue;

public class Crypto {
    private static String cryptoString;
    private static String currencyString;
    private static String convertedResult;
    private static Map<String, Map<String, String>> cryptoMultiResult= new HashMap<>();
    private static Map<String, String> cryptoCurrenciesResult;
    private static List<String> currencies = new LinkedList<>();
    private static List<String> cryptos = new LinkedList<>();
    private static Logger logger = LoggerFactory.getLogger(Crypto.class);
    
    public static Map<String, String> getCryptoCurrenciesResult() {
        return cryptoCurrenciesResult;
    }
    public static void setCryptoCurrenciesResult(Map<String, String> cryptoCurrenciesResult) {
        Crypto.cryptoCurrenciesResult = cryptoCurrenciesResult;
    }
    public static List<String> getCryptos() {
        return cryptos;
    }
    public static void setCryptos(List<String> cryptos) {
        Crypto.cryptos = cryptos;
    }
    public List<String> getCurrencies() {
        return currencies;
    }
    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    
    public Map<String, Map<String, String>> getCryptoMultiResult() {
        return cryptoMultiResult;
    }
    public void setCryptoMultiResult(Map<String, Map<String, String>> cryptoMultiResult) {
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
            cryptos = new LinkedList<>();
            if(cryptoString.contains(",")){
                String[] cryptoArr = cryptoString.split(",");
                for(int i=0; i<cryptoArr.length;i++){
                    cryptos.add(cryptoArr[i]);
                }
            }else{
                cryptos.add(cryptoString);
            }
            currencies =  new LinkedList<>();
            if(currencyString.contains(",")){
                String[] currencyArr = currencyString.split(",");
                for(int i=0; i<currencyArr.length;i++){
                    currencies.add(currencyArr[i]);
                }
            }else{
                currencies.add(currencyString);
            }
            for(String crypto:cryptos){
                JsonObject holdingres = jObj.get(crypto).asJsonObject();
                Map<String,String> holdingMap = new HashMap<>();
                for (String currency: currencies){
                    holdingMap.put(currency, holdingres.get(currency).toString());
                }
                fromAPICrypto.cryptoMultiResult.put(crypto, holdingMap);
            }
        }
        return fromAPICrypto; 
    }

}
