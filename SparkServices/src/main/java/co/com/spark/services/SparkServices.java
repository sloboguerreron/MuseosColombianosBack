package co.com.spark.services;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.LiteralImpl;
import org.apache.jena.rdfconnection.RDFConnection;
import org.springframework.stereotype.Service;

@Service
public class SparkServices {

	public void listar() {
		try (RDFConnection conn = RDFConnection.connect("http://es.dbpedia.org/sparql")){
			QueryExecution query = conn.query("prefix foaf: <http://xmlns.com/foaf/0.1/> select ?nombre where {?museo foaf:name ?nombre .} LIMIT 100");
			ResultSet rs = query.execSelect();
			while(rs.hasNext()) {
				QuerySolution qs = rs.next();
				//Resource concept = qs.getResource("nombre");
				RDFNode node = qs.get("nombre");
				System.out.println("Nombre: "+node);
			}
		}catch(Exception e) {
			System.out.println("ERROR en la conexión");
		}
	}
	
}
