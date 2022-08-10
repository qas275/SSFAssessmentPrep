package vttp2022.SSFAssessmentPrep.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vttp2022.SSFAssessmentPrep.model.Crypto;
import vttp2022.SSFAssessmentPrep.service.CryptoService;

@Controller
public class CryptoController {
    @Autowired
    private CryptoService service;
    
    private Logger logger = LoggerFactory.getLogger(CryptoController.class);

    @GetMapping("/")
    public String showIndexPage(Model model) {
        Crypto coinReq = new Crypto();
        model.addAttribute("Crypto", coinReq);
        return "request";
    }

    @GetMapping("/req")
    public String showConversion(Model model, @ModelAttribute Crypto coinReq){
        Crypto request = coinReq;
        Crypto response = service.convertCrypto(request).get();
        logger.info("CRYPTO NAME>>> "+response.getCryptoString());
        model.addAttribute("response", response);
        return "conversionRes";
    }
}
