package org.springframework.security.demo.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.demo.domain.CalendarUser;
import org.springframework.security.demo.service.CalendarService;
import org.springframework.security.demo.service.UserContext;
import org.springframework.security.demo.web.model.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignupController {
	private final UserContext userContext;
	private final CalendarService calendarService;

	@Autowired
	public SignupController(UserContext userContext, CalendarService calendarService) {
		if (userContext == null) {
			throw new IllegalArgumentException("userContext cannot be null");
		}
		if (calendarService == null) {
			throw new IllegalArgumentException("calendarService cannot be null");
		}
		this.userContext = userContext;
		this.calendarService = calendarService;
	}

	@RequestMapping("/signup/form")
	public String signup(@ModelAttribute SignupForm signupForm) {
		return "signup/form";
	}

	@RequestMapping(value = "/signup/new", method = RequestMethod.POST)
	public String signup(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "signup/form";
		}

		String email = signupForm.getEmail();
		if (calendarService.findUserByEmail(email) != null) {
			result.rejectValue("email", "errors.signup.email", "Email address is already in use.");
			return "signup/form";
		}

		CalendarUser user = new CalendarUser();
		user.setEmail(email);
		user.setFirstName(signupForm.getFirstName());
		user.setLastName(signupForm.getLastName());
		user.setPassword(signupForm.getPassword());

		int id = calendarService.createUser(user);
		user.setId(id);
		userContext.setCurrentUser(user);

		redirectAttributes.addFlashAttribute("message", "TODO we will implement signup later in the chapter");
		return "redirect:/";
	}
}
