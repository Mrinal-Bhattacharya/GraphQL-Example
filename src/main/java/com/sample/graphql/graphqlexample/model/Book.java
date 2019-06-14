package com.sample.graphql.graphqlexample.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	private String isn;
	private String title;
	private String publisher;
	private String[] authors;
	private String publishDate;
}
