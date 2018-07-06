package spring.project.terms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TermsController {

    @RequestMapping(value = "terms/pterms")
    public String pterms() {
	return "terms/pterms";
    }

    @RequestMapping(value = "terms/rterms")
    public String rterms() {
	return "terms/rterms";
    }
}
