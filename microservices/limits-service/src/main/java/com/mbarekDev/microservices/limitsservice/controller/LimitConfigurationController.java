package com.mbarekDev.microservices.limitsservice.controller;

import com.mbarekDev.microservices.limitsservice.bean.LimitsConfiguration;
import com.mbarekDev.microservices.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigurationController {

    private final Configuration configuration;

    public LimitConfigurationController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("limits_config")
    public LimitsConfiguration retrievelLimitsFromConfigurations() {
        return new LimitsConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }
}

