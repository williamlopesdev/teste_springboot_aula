package com.ci.projeto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/teste")
public class HomeController {
	
	@GetMapping
	public String Hello() {
		return "Hello Word";
	}
}
