package co.com.spark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.spark.services.SparkServices;

@RestController
public class SparkController {
	
	@Autowired
	SparkServices sparkServices;

	@PostMapping("/listar")
	public ResponseEntity<String> listar(){
		sparkServices.listar();
		return new ResponseEntity<String>("Funcionando", HttpStatus.OK);
	}
	
}
