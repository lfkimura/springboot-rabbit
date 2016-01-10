package lfkimura.springboot;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class Example {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

}