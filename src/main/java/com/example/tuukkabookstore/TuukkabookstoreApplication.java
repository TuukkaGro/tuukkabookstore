
package com.example.tuukkabookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.tuukkabookstore.domain.Category;
import com.example.tuukkabookstore.domain.CategoryRepository;

import com.example.tuukkabookstore.domain.AppUserRepository;

import com.example.tuukkabookstore.domain.AppUser;

import com.example.tuukkabookstore.domain.Book;
import com.example.tuukkabookstore.domain.BookRepository;


@SpringBootApplication
public class TuukkabookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(TuukkabookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TuukkabookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, AppUserRepository urepository) {
		return (args) -> {
			log.info("save some catogories");
			// lisätään katogorioita H2-tietokantaan
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Sci-fi"));
			crepository.save(new Category("Historical"));
			crepository.save(new Category("Art"));
			crepository.save(new Category("Romance"));
			
			log.info("save some books");
			// lisätään kirjoja H2-tietokantaan
			repository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "63728367", 9.99,
					crepository.findByName("Fantasy").get(0)));
			repository.save(new Book("Fellowship of the ring", "J.R.R. Tolkien", 1954, "63728367", 15.89,
					crepository.findByName("Fantasy").get(0)));
			// lisätään käyttäjiä H2-tietokantaan
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			} 
			

		};
	}
}


