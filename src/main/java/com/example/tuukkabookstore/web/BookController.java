package com.example.tuukkabookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tuukkabookstore.domain.Book;
import com.example.tuukkabookstore.domain.BookRepository;



@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	// palauttaa booklist sivun ja listaa kaikki kirjat 
	@GetMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	// poistaa kirjan ja palauttaa booklist sivua uudelleen 
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	// palauttaa addbook sivun
	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	// palauttaa editbook sivun tietylle kirjalle kirjan id:n mukaan
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		return "editbook";
		}
	// tallettaa kirjan ja palauttaa booklist sivun
	@PostMapping("/save")
	public String save(Book book) {
		repository.save(book);
		return"redirect:booklist";
	}
	
	// palauttaa sivun index
	@GetMapping("/index")
	public String Home() {
		
		return "index";
		
	}

}
