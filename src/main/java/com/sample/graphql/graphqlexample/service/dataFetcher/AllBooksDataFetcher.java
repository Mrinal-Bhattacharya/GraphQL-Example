package com.sample.graphql.graphqlexample.service.dataFetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.graphql.graphqlexample.model.Book;
import com.sample.graphql.graphqlexample.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {

	@Autowired
	BookRepository bookRespository;
	
	@Override
	public List<Book> get(DataFetchingEnvironment environment) {
		return bookRespository.findAll();
	}

}
