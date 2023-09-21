package com.example.tuukkabookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tuukkabookstore.domain.Book;
import com.example.tuukkabookstore.domain.BookRepository;
import com.example.tuukkabookstore.domain.CategoryRepository;



@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// RESTfull palvelu joka palauttaa kaikki kirjat JSON olioina 
	 @RequestMapping(value="/books", method = RequestMethod.GET)
	 public @ResponseBody List<Book> bookListRest (){
		 return (List<Book>) repository.findAll();
	 }
	
	// RESTfull palvelu joka palauttaa kirjan ID:n mukaan JSON oliona
	 @RequestMapping (value="/book/{id}", method = RequestMethod.GET)
	 public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		 return repository.findById(id);
	 }
	
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
		model.addAttribute("categorys", crepository.findAll());
		return "addbook";
	}
	// palauttaa editbook sivun tietylle kirjalle kirjan id:n mukaan
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categorys", crepository.findAll());
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
