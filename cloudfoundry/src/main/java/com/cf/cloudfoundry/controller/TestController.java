package com.cf.cloudfoundry.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping(value = "/cloudfoundry")
public class TestController {

	@Value("${propertycfOne}")
	private String testPropertyCfOne;

	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<TestJson> fetchAvailableTimeSlots(){

		TestJson test= new TestJson();

		test.setMessage(testPropertyCfOne);
		
		ResponseEntity<TestJson> response =new ResponseEntity<TestJson>(test,HttpStatus.OK);

		return response;
	}
	

	
	@RequestMapping(value = "/test/feignclient/{message}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> testFeignClient(@PathVariable String message){

		TestJson test= new TestJson();

		test.setMessageForInterface(message);
	
		return  new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
