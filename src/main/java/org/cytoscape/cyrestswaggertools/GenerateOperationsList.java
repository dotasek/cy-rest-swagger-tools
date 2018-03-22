package org.cytoscape.cyrestswaggertools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class GenerateOperationsList {
	public static void main(String[] args) throws FileNotFoundException {
		readFile("./src/main/resources/cyrest_functions_swagger.json");
	}
	
	private static void readFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root;
		try {
			root = mapper.readTree(new BufferedReader(fileReader));
			JsonNode firstNode = root.get(0);
			System.out.println(root.toString());
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
