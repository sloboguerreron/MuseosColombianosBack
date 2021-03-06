package co.com.spark.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.LiteralImpl;
import org.apache.jena.rdfconnection.RDFConnection;
import org.springframework.stereotype.Service;

import co.com.spark.dto.EntradaDTO;
import co.com.spark.dto.RespuestaDTO;

@Service
public class SparkServices {

	public List<RespuestaDTO> listar(EntradaDTO input) {
		List<RespuestaDTO> respuestas = new ArrayList<RespuestaDTO>();
		try (RDFConnection conn = RDFConnection.connect("http://es.dbpedia.org/sparql")){
			
			QueryExecution query;
			
			if(input.getCiudad().equals("") ) {
				query = conn.query("prefix foaf: <http://xmlns.com/foaf/0.1/>"
						+ "prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#>"
						+ "prefix dbo: <http://dbpedia.org/ontology/>"
						+ "select ?nombre ?comentario ?homepage where {"
						+ "?museo rdfs:comment ?comentario ."
						+ "?museo foaf:name ?nombre ."
						+ "?museo foaf:homepage ?homepage ."
						+ "FILTER regex(?nombre, \""+input.getNombreMuseo()+"\", \"i\") ."
						+ "} LIMIT 100");
			} else { 
				query = conn.query("prefix foaf: <http://xmlns.com/foaf/0.1/>"
						+ "prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#>"
						+ "prefix dbo: <http://dbpedia.org/ontology/>"
						+ "prefix dbpedia-es: <http://es.dbpedia.org/resource/>"
						+ "select ?nombre ?comentario ?homepage where {"
						+ "?museo rdfs:comment ?comentario ."
						+ "?museo foaf:name ?nombre ."
						+ "?museo foaf:homepage ?homepage ."
						+ "FILTER regex(?nombre, \""+input.getNombreMuseo()+"\", \"i\") ."
						+ "?museo dbo:city dbpedia-es:"+input.getCiudad()+" ."
						+ "} LIMIT 100");
			}
			
			
			ResultSet rs = query.execSelect();
			
			while(rs.hasNext()) {
				QuerySolution qs = rs.next();
				//Resource concept = qs.getResource("nombre");
				RDFNode nombre = qs.get("nombre");
				RDFNode comentario = qs.get("comentario");
				RDFNode homepage = qs.get("homepage");
				RespuestaDTO respuesta = new RespuestaDTO(nombre.toString(), comentario.toString(), homepage.toString());
				respuestas.add(respuesta);
			}
		}catch(Exception e) {
			// Usar Logger
			System.out.println("ERROR en la conexi??n");
		}
		
		return respuestas;
	}
	
}
