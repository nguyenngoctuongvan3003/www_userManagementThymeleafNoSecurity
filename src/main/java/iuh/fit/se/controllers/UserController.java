package iuh.fit.se.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import iuh.fit.se.entities.User;
import iuh.fit.se.services.UserService;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "user/register";
	}
	
	@PostMapping("/register")
    public String registration(@Valid @ModelAttribute User user, 
    		BindingResult result, Model model){
		if(result.hasErrors()){
	       model.addAttribute("user", user);
	       return "user/register";
	    }
		 
		User existingUser = userService.findByEmail(user.getEmail());

        if(existingUser != null 
        		&& existingUser.getEmail() != null 
        		&& !existingUser.getEmail().isEmpty()){
            
        	result.rejectValue("email", null, "Email da ton tai");
           
        	return "user/register";
        }

        userService.save(user);
        return "redirect:/register?success";
    }
	
	@GetMapping("/users")
    public String getUsers(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }
}
