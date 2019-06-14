package com.sample.graphql.graphqlexample.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.sample.graphql.graphqlexample.model.Book;
import com.sample.graphql.graphqlexample.repository.BookRepository;
import com.sample.graphql.graphqlexample.service.dataFetcher.AllBooksDataFetcher;
import com.sample.graphql.graphqlexample.service.dataFetcher.BookDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Value("classpath:books.graphql")
	Resource resource;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;

	@Autowired
	private BookDataFetcher bookDataFetcher;

	private GraphQL graphQL;

	@PostConstruct
	private void loadSchema() throws IOException {
		loadData();
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();

	}

	private void loadData() {
		Stream.of(new Book("1", "test1", "pub1", new String[] {"pub111"}, "111"), new Book("2", "test2", "pub2", new String[] {"pub222"}, "222"))
				.forEach(book -> bookRepository.save(book));

	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
				.dataFetcher("allBooks", allBooksDataFetcher).dataFetcher("book", bookDataFetcher)).build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
