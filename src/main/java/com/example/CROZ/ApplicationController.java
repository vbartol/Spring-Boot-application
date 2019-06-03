package com.example.CROZ;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ApplicationController {
	
	@Autowired
    private JokeRepository jokeRepository; 

	@GetMapping("/")
	public String JokeForm(Model model, @RequestParam(defaultValue="0") int page) {
		model.addAttribute("data", jokeRepository.
				findAll(PageRequest.of(page, 10, Sort.Direction.DESC, "difference")));
		model.addAttribute("currentPage", page);
		return "thymeleaf/index";
	}

	// show the add joke form and also pass an empty backing bean object to
	// store the form field values
	@RequestMapping(value = "/new", method = {RequestMethod.GET, RequestMethod.POST})
	public String show(Model model) {
		List<String> listOfCategories = jokeRepository.findAllCategories();
		model.addAttribute("listOfCategories", listOfCategories);
		model.addAttribute("JokeForm", new Joke());
		return "JokeForm";
	}
	
	@RequestMapping(value="joke/likes/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateLikes (@PathVariable int id) {
		jokeRepository.updateLikes(id);
		jokeRepository.updateSort(id);
		return "redirect:/";
		
	}
	
	@RequestMapping(value="joke/dislikes/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateDislikes (@PathVariable int id) {
		jokeRepository.updateDislikes(id);		
		jokeRepository.updateSort(id);
		return "redirect:/";
		
	}
}