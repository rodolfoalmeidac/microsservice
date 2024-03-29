package com.dev.hrpayroll.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.hrpayroll.entities.Payment;
import com.dev.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping( value = "/payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService service;
	
	@HystrixCommand(fallbackMethod = "getPaymentAlternativa")
	@GetMapping(value = "/{workerId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days){
		Payment pay = service.getPayment(workerId, days);
		return ResponseEntity.ok(pay);
	}
	
	public ResponseEntity<Payment> getPaymentAlternativa(Long workerId, Integer days){
		Payment pay = new Payment("Brann", 400.0, days);
		return ResponseEntity.ok(pay);
	}

}
