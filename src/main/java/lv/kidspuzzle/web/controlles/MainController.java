package lv.kidspuzzle.web.controlles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {
//all basic pages
    @GetMapping("/") //which URL address we working on -  "/" - main page
    public String home(Model model) {
        model.addAttribute("title", "Puzzle Homepage"); //parameter title will be transferred to html home
        return "home"; // call html page - home
    }


    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us"); //parameter title will be transferred to html home
        return "about"; // call html page - about
    }



}
