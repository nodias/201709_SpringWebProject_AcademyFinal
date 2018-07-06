package spring.project.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {
	 
	@ExceptionHandler(Exception.class)
	private ModelAndView errorModelAndView(Exception e) {
		log.info(e.toString());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/error_common");
		modelAndView.addObject("exception",e);
		return modelAndView;
	}
	
}
