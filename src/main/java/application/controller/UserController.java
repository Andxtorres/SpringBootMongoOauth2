package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import application.configuration.security.mongo.OAuth2AccessTokenRepository;
import application.model.User;
import application.service.UserService;
import application.utility.JsonResponse;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    ResponseEntity<?> addUser(@RequestBody User user) {
    	userService.insertUser(user);
    	JsonResponse<String> result= new JsonResponse<>(200,"Created");
		return new ResponseEntity<>(result,null,HttpStatus.OK) ;
    }    
    @RequestMapping(value="/find/firstName/{firstName}",method = RequestMethod.GET)
    ResponseEntity<?> getUserByFirstName(@PathVariable String firstName) {
    	User user=userService.findByName(firstName);
    	JsonResponse<User> result= new JsonResponse<>(200,user);
		return new ResponseEntity<>(result,null,HttpStatus.OK) ;
    } 
    @RequestMapping(value="/update",method = RequestMethod.PUT)
    ResponseEntity<?> updateUser(@RequestBody User user) {
    	userService.updateUser(user);
    	JsonResponse<String> result= new JsonResponse<>(200,"Updated");
		return new ResponseEntity<>(result,null,HttpStatus.OK) ;
    }      
}
