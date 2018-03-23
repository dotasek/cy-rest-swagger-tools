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
		System.out.println("\nFunctions\n");
		readFile("./src/main/resources/cy_rest_functions_swagger.json");
		System.out.println("\nCommands\n");
		readFile("./src/main/resources/cy_rest_commands_swagger.json");
	}
	
	private static void readFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root;
		try {
			root = mapper.readTree(new BufferedReader(fileReader));
			JsonNode paths = root.get("paths");
			
			for (String path : (Iterable<String>)() -> paths.fieldNames()) {
				JsonNode pathNode = paths.get(path);
				for (String method : (Iterable<String>)() -> pathNode.fieldNames()) {
					JsonNode methodNode = pathNode.get(method);
					System.out.println(method.toUpperCase() + "\t" +  path + "\t" + methodNode.get("summary").asText());
				}
			}
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
