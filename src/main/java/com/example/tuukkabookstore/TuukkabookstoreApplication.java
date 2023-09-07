package com.example.tuukkabookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.tuukkabookstore.domain.Book;
import com.example.tuukkabookstore.domain.BookRepository;


@SpringBootApplication
public class TuukkabookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(TuukkabookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TuukkabookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("save some books");
			repository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937, 63728367, 9.99));
			repository.save(new Book("Fellowship of the ring", "J.R.R. Tolkien", 1954, 63728367, 15.89));
			
		
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}



