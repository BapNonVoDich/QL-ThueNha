package web.project.HouseRentalAPI.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
    @GetMapping("/")
    public String home() {
        String code = "<h1>thue nha backend</h1>" +
                "<p>đây là host của backend thuê nhà</p>";
        return code;
    }
}



