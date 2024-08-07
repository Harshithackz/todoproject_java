package com.example.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class Todocontrollerjpa {
	
	private Todorepository todorepository; 	
	
	public Todocontrollerjpa(Todorepository todorepository) {
		super();
		this.todorepository=todorepository;
		
	}


	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUsername(model);
		List<Todo> todos = todorepository.findByUsername(username);
		model.addAttribute("todos", todos);
		
		return "todoslist";
	}
	@RequestMapping(value = "addtodo",method =RequestMethod.GET)
	public String shownewtodopage(ModelMap model) {
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}


	private String getLoggedInUsername(ModelMap model) {
		return (String)model.get("name");
	}
	@RequestMapping(value = "addtodo",method =RequestMethod.POST)
	public String addnewtodopage(ModelMap model , @Valid Todo todo , BindingResult result) {
		if (result.hasErrors()){
			return "todo";
		}
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todorepository.save(todo);
//		todoService.addtodo(username, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}
	@RequestMapping("delete-todo")
	public String deletetodo(@RequestParam int id) {
		todorepository.deleteById(id);
		return "redirect:list-todos";
	}
	@RequestMapping(value ="update-todo",method = RequestMethod.GET)
	public String updatetodo(@RequestParam int id,ModelMap model) {
		Todo todo = todorepository.findById(id).get();
		model.addAttribute("todo",todo);
		return "todo";
	}
	@RequestMapping(value = "update-todo",method =RequestMethod.POST)
	public String updatetodopage(ModelMap model , @Valid Todo todo , BindingResult result) {
		if (result.hasErrors()){
			return "todo";
		}
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todorepository.save(todo);
		return "redirect:list-todos";
	}
}
