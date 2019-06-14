package com.sample.graphql.graphqlexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.graphql.graphqlexample.model.Book;

public interface BookRepository extends JpaRepository<Book, String>{

}
