package com.sample.graphql.graphqlexample.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.graphql.graphqlexample.service.GraphQLService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/rest/books")
public class BookResource {
	
	
	@Autowired
	GraphQLService graphQLService;

	@GetMapping
	public ResponseEntity<Object> getAllBooks(@RequestParam String query) {
		ExecutionResult execute = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(execute,HttpStatus.OK);
	}

}
