package vttp2022.SSFAssessmentPrep.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.SSFAssessmentPrep.service.CryptoService;

@RestController
@RequestMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CryptoRestCon {
    private static final Logger logger = LoggerFactory.getLogger(CryptoRestCon.class);

    @Autowired
    private CryptoService service;


}
