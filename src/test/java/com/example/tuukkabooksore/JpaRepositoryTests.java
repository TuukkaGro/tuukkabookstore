package com.example.tuukkabooksore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tuukkabookstore.TuukkabookstoreApplication;
import com.example.tuukkabookstore.domain.AppUser;
import com.example.tuukkabookstore.domain.AppUserRepository;
import com.example.tuukkabookstore.domain.Book;
import com.example.tuukkabookstore.domain.BookRepository;
import com.example.tuukkabookstore.domain.CategoryRepository;

@SpringBootTest(classes = TuukkabookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaRepositoryTests {
	
	@Autowired 
	private BookRepository repository;

	@Autowired 
	private CategoryRepository crepository;

	@Autowired 
	private AppUserRepository urepository;
	
	// testataan uuden kirjan lisääminen tietokantaan
	@Test
	public void addNewBook () {
		//luodaan uusi kirja
		Book book = new Book("The Innocent Man", "John Grisham", 2007, "63728367", 9.99, 
				crepository.findByName("Fantasy").get(0));
		// tallennetaan kirja repositoryn avulla
		repository.save(book);
		// tarkistetaan onko uudella kirjalla tietokannassa jokaiselle kirjalle uniikki id arvo.
		// jos id arvo löytyy testi menee hyväksytynä läpi
		assertThat(book.getId()).isNotNull();
	}
	
	// testataan kirjan poistamista
	@Test 
	public void deleteBook () {
		//etsitään kirja nimellä jonka tiedetään olevan testi datana tietokannassa joka lisätään listaan books
		List<Book> books = repository.findByTitle("The Hobbit");
		Book book = books.get(0);
		// repository poistaa kirjan
		repository.delete(book);
		// etsitään kirja uudelleen ja lisätään se uuteen listaan newBooks
		List<Book> newBooks = repository.findByTitle("The Hobbit");
		// kirjaa ei kuitenkaan kuuluisi enään löytyä jos näin on listan newBooks koko on nolla.
		// jos näin on testi menee hyväksyttynä läpi
		assertThat(newBooks).hasSize(0);	
			
	}
	// testataan uuden käyttäjän luomista 
	
	@Test
	public void addNewUser () {
		// luodaan uusi käyttäjä
		AppUser newUser1 = new AppUser("tuukka", 
				"$2a$10$CelXakteIydPM.rA9osm5.OBzaVpRl.XYe8vLcR7LE4EsRNFXED8e", "TUUKKA");
		// tallennetaan käyttäjä reposirotyn avulla
		urepository.save(newUser1);
		// tarkistetaan onko uudella käyttäjällä tietokannassa jokaiselle käyttäjälle uniikki id arvo.
		// jos id arvo löytyy testi menee hyväksytynä läpi
		assertThat(newUser1.getId()).isNotNull();
	
	}
	
	 
}
