package co.com.spark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.spark.dto.EntradaDTO;
import co.com.spark.dto.RespuestaDTO;
import co.com. spark.services.SparkServices;

@RestController
public class SparkController {
	
	@Autowired
	SparkServices sparkServices;
	
	@CrossOrigin
	@PostMapping("/listar")
	public ResponseEntity<List<RespuestaDTO>> listar(@RequestBody EntradaDTO input){
		List<RespuestaDTO> respuestas = sparkServices.listar(input);
		return new ResponseEntity<List<RespuestaDTO>>(respuestas, HttpStatus.OK);
	}
	
}
