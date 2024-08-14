package net.javaguides.springboot.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody // convert the java object to json
@RestController // equal @controller and @ responseBody
public class HelloWorldController {
    @GetMapping("/helloworld")// Http request
   public String HelloWorld(){
       return "Hello World";
   }
}
