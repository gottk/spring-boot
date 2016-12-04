package com.au.silverrail.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.au.silverrail.domain.Attachment;
import com.au.silverrail.domain.UserCharacters;
import com.au.silverrail.domain.UserState;
import com.au.silverrail.domain.UserSum;
import com.au.silverrail.exception.CharacterLimitException;
import com.au.silverrail.exception.InvalidAttachmentException;
import com.au.silverrail.exception.InvalidCharacterException;
import com.au.silverrail.service.UserService;

@RestController
public class UserController  {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
    @RequestMapping("/")
    @ResponseBody
    String home() {
    	return "Hy there, welcome to the spring boot demo.";
    }
    
    @RequestMapping(value="/state", method = RequestMethod.GET)
    @ResponseBody
    UserState getState(HttpServletRequest request,
    		@RequestHeader(value=HttpHeaders.USER_AGENT) String userAgent) {
    	
    	int id = hashCode(request, userAgent);    	
    	String state = userService.getState(id);  
    	System.out.println("User with id: " + id + " sent /state request, state is: " + state);
    	log.debug("User with id: " + id + " sent /state request, state is: " + state);
    	return new UserState(id, state);
    }
    
    @RequestMapping(value="/sum", method = RequestMethod.GET)
    @ResponseBody
    UserSum getSum(HttpServletRequest request, 
    		@RequestHeader(value=HttpHeaders.USER_AGENT) String userAgent) {	
    	
    	int id = hashCode(request, userAgent);
    	int sum = userService.getSum(id);
    	System.out.println("User with id: " + id + " sent /sum request, sum is: " + sum);
    	log.debug("User with id: " + id + " sent /sum request, sum is: " + sum);
    	return new UserSum(id, sum);
    }
    
    @RequestMapping(value="/chars", method = RequestMethod.GET)
    @ResponseBody
    UserCharacters getChars(HttpServletRequest request, 
    		@RequestHeader(value=HttpHeaders.USER_AGENT) String userAgent) {	
    	
    	int id = hashCode(request, userAgent);
    	String characters = userService.getChars(id);
    	System.out.println("User with id: " + id + " sent /getChar request, char is: " + characters);
    	log.debug("User with id: " + id + " sent /getChar request, char is: " + characters);
    	return new UserCharacters(id, characters);
    }
    
    @RequestMapping(value="/chars", method = RequestMethod.POST)
    void postCharacters(HttpServletRequest request, HttpServletResponse response,
    		@RequestHeader(value=HttpHeaders.USER_AGENT) String userAgent,
    		Attachment attachment) {
    	
    	int id = hashCode(request, userAgent);
    	
    	try {
			userService.addCharacters(id, attachment);
			System.out.println("User with id: " + id + " sent post request /chars request, char is: " + attachment);
			log.debug("User with id: " + id + " sent post request /chars request, char is: " + attachment);
    	} catch (InvalidAttachmentException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (CharacterLimitException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
    }
    
    @RequestMapping(value="/chars", method = RequestMethod.DELETE)
    void deleteCharacter(HttpServletRequest request, HttpServletResponse response,
    		@RequestHeader(value=HttpHeaders.USER_AGENT) String userAgent,
    		@RequestParam(value="character") String character) {

    	int id = hashCode(request, userAgent);
    	
    	try {
			userService.deleteCharacter(id, character);
			System.out.println("User with id: " + id + " sent delete request, char is: " + character);
			log.debug("User with id: " + id + " sent delete request, char is: " + character);
		} catch (InvalidCharacterException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
    }
    
    private int hashCode(HttpServletRequest request, String userAgent) {
    	return request.getRemoteAddr().hashCode() * userAgent.hashCode();
    }
}