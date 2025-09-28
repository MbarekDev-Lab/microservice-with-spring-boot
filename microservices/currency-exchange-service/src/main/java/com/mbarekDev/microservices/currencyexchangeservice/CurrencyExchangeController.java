package com.mbarekDev.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mbarekDev.microservices.currencyexchangeservice.exception.ResourceNotFoundException;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    private final CurrencyExchangeRepository repository;
    private final Environment environment;

    public CurrencyExchangeController(CurrencyExchangeRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchangeBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        logger.info("retrieveExchangeValue called with {} to {}", from, to);
        CurrencyExchangeBean currencyExchangeBean = repository.findByFromAndTo(from, to);

        if (currencyExchangeBean == null) {
            throw new ResourceNotFoundException("Unable to Find data for " + from + " to " + to);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchangeBean.setEnvironment(port);
        return currencyExchangeBean;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean conversionCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        logger.info("conversionCurrency called with from {} to {}, quantity {}", from, to, quantity);
        CurrencyExchangeBean currencyExchangeBean = repository.findByFromAndTo(from, to);

        if (currencyExchangeBean == null) {
            throw new ResourceNotFoundException("Unable to Find data for " + from + " to " + to);
        }

        BigDecimal conversionMultiple = currencyExchangeBean.getConversionMultiple();
        BigDecimal totalCalculatedAmount = quantity.multiply(conversionMultiple);
        String port = environment.getProperty("local.server.port");

        return new CurrencyConversionBean(currencyExchangeBean.getId(), from, to, conversionMultiple, quantity, totalCalculatedAmount, port);
    }
}
