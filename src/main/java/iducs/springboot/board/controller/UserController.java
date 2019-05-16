package iducs.springboot.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.User;
import iducs.springboot.board.exception.ResourceNotFoundException;
import iducs.springboot.board.repository.UserRepository;

@Controller
public class UserController {
	@Autowired UserRepository userRepo; // Dependency Injection
	
	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
	@GetMapping("/user-reg")
	public String regform() {
		return "user-form";
	}
	
	@PostMapping("/users")
	public String createUser(@Valid User userDetails, Model model) {
		userRepo.save(userDetails);
		model.addAttribute("user", userDetails);
		return "redirect:/users";
	}
	
	@GetMapping("/users")
	public String getAllUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "user-list";
	}	
	@GetMapping("/users/{id}")
	public String getUserById(@PathVariable(value = "id") Long userId, Model model)
			throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id:: " + userId));
		model.addAttribute("user", user);
		return "user-info";
		//return ResponseEntity.ok().body(user);
	}
	@GetMapping("/users/fn")
	public String getEmployeeByFirstName(@Param(value = "name") String name, Model model)
			throws ResourceNotFoundException {
		System.out.println(name);
		List<User> users = userRepo.findByName(name);
		model.addAttribute("users", users);
		return "user-list";
	}
	@PutMapping("/users/{id}")
	public String updateUserById(@PathVariable(value = "id") Long userId, @Valid User userDetails, Model model)
			throws ResourceNotFoundException {
		System.out.println("upupupup");
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		user.setName(userDetails.getName());
		user.setCompany(userDetails.getCompany());
		User userUpdate = userRepo.save(user);
		model.addAttribute("user", userUpdate);
		return "user-updated";
	}
	@PatchMapping("/users/{id}")
	//@RequestBody 사용하는 경우 
	public ResponseEntity<User> patchUserById(@PathVariable(value = "id") Long userId, @Valid  User userDetails, Model model)
			throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		user.setName(userDetails.getName());
		//user.setCompany(userDetails.getCompany());
		User userUpdate = userRepo.save(user);
		return ResponseEntity.ok(userUpdate);
	}
	@DeleteMapping("/users/{id}")
	public String deleteUserById(@PathVariable(value = "id") Long userId, Model model)
			throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		userRepo.delete(user);
		model.addAttribute("name", user.getName());
		return "user-deleted";
	}

}
