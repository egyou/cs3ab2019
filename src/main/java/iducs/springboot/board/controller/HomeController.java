package iducs.springboot.board.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.User;
import iducs.springboot.board.exception.ResourceNotFoundException;
import iducs.springboot.board.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class HomeController {
	@Autowired UserRepository userRepo; // Dependency Injection
	
	@GetMapping("/test")
	public String home(Model model) {
		model.addAttribute("test", "인덕 컴소");
		model.addAttribute("egy", "유응구");
		return "index";
	}
	@GetMapping("/")
	public String getAllUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "userlist";
	}
	/*
	@GetMapping("/{fn}")
	public String getEmployeeByFirstName(@PathVariable(value = "fn") String firstName)
			throws ResourceNotFoundException {
		Employee employee = userRepo.findByFirstName(firstName)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this firstname :: " + firstName));
		return ResponseEntity.ok().body(employee);
	}
	*/
	@GetMapping("/{id}")
	public String getUserById(@PathVariable(value = "id") Long userId, Model model)
			throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		model.addAttribute("name", user.getName());
		model.addAttribute("company", user.getCompany());
		return "user";
		//return ResponseEntity.ok().body(user);
	}
	@PutMapping("/{id}")
	public String updateUserById(@PathVariable(value = "id") Long userId, Model model)
			throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		model.addAttribute("name", user.getName());
		model.addAttribute("company", user.getCompany());
		return "user";
		//return ResponseEntity.ok().body(user);
	}
	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable(value = "id") Long userId, Model model)
			throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		model.addAttribute("name", user.getName());
		model.addAttribute("company", user.getCompany());
		return "user";
		//return ResponseEntity.ok().body(user);
	}
	/*
	@GetMapping("/employees/{fn}")
	public ResponseEntity<Employee> getEmployeeByFirstName(@PathVariable(value = "fn") String firstName)
			throws ResourceNotFoundException {
		Employee employee = userRepo.findByFirstName(firstName)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this firstname :: " + firstName));
		return ResponseEntity.ok().body(employee);
	}
	*/
	@GetMapping("/form")
	public String regform() {
		return "form";
	}
	@PostMapping("/create")
	public String createUser(@Valid @RequestBody User user, Model model) {
		userRepo.save(user);
		model.addAttribute("user", user);
		return "redirect:/users";
	}
}
