package pl.macias.dip.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by macias on 24.02.15.
 */

@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public String sayHelloToAnonymous (ModelMap model) {
        return "hello";
    }

    @RequestMapping(value = "/hello/{nick}", method = RequestMethod.GET)
    public String sayHelloToKnownGuy (ModelMap model, @PathVariable("nick") String nick) {
        model.addAttribute("nick", nick);
        return "hello";
    }

}
