package com.sample.graphql.graphqlexample.service.dataFetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.graphql.graphqlexample.model.Book;
import com.sample.graphql.graphqlexample.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book>{
	
	@Autowired
	BookRepository bookRespository;

	@Override
	public Book get(DataFetchingEnvironment environment) {
		String isn = environment.getArgument("isn");
		return bookRespository.findById(isn).get();
	
	}

}
