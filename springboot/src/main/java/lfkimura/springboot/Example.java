package lfkimura.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class Example {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

}