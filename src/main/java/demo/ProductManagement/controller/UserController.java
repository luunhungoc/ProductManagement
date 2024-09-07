package demo.ProductManagement.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import demo.ProductManagement.entity.Role;
import demo.ProductManagement.entity.RoleService;
import demo.ProductManagement.entity.User;
import demo.ProductManagement.entity.UserService;
import demo.ProductManagement.repository.RoleRepository;
import demo.ProductManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/")
	public String viewHomePage() {
		return "user/index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		List<Role> roleList = (List<Role>) roleRepository.findAll();
		model.addAttribute("roleList", roleList);

		return "user/signup_form";
	}

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@PostMapping("/process_register")
	public String processRegister(User user,
								  @RequestParam(value = "roles", required = false) List<Long> roleIds) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		// Fetch selected roles by their IDs
		Set<Role> roles = new HashSet<>();
		if (roleIds != null) {
			for (Long roleId : roleIds) {
				Role role = roleService.getRoleById(roleId);
				if (role != null) {
					roles.add(role);
				}
			}
		}

		// Set roles to user
		user.setRoles(roles);

		// Save user with the selected roles
		userService.saveUserWithRoles(user);

		return "user/register_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);

		return "user/users";
	}


}
