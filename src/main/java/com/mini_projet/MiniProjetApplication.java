package com.mini_projet;

import com.mini_projet.services.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class MiniProjetApplication {
	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(MiniProjetApplication.class, args);
	}


	@Bean
	public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.gauge("orders.total", orderService, OrderService::getTotalOrders);
	}
}
