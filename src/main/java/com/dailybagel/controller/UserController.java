package com.dailybagel.controller;


import java.io.IOException;
import java.io.PrintWriter;

import com.dailybagel.user.resources.User;
import com.dailybagel.user.resources.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin
@Controller
public class UserController {
	UserService us;
	
	public UserController() {
		us = new UserService();
	}

	@RequestMapping("/getAllUsers")
	public void getAllUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.println(gson.toJson(this.us.getAllUsers()));
	}
	
	@RequestMapping("/getUserPage")
	public void getUserPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int pageNumber;
		int itemsPerPage;
		try {
			 pageNumber = Integer.valueOf(request.getParameter("pageNumber"));
			 itemsPerPage = Integer.valueOf(request.getParameter("itemsPerPage"));
		} catch (NumberFormatException e) {
			pageNumber = 0;
			itemsPerPage = 5;
		}
		PrintWriter out = response.getWriter();
	    Gson gson = new Gson();
	    out.println(gson.toJson(this.us.getUserPage(pageNumber,itemsPerPage)));
	}
	
	@RequestMapping("/getUser")
	public void getUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int userId = Integer.valueOf(request.getParameter("userId"));
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.println(gson.toJson(this.us.getUser(userId)));
	}
		
	@RequestMapping("/addUser")
	public void addUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		   
		Gson gson = new Gson();
	    JsonParser parser = new JsonParser();
	    JsonObject obj = (JsonObject) parser.parse(request.getReader());
	        
	    User user = gson.fromJson(obj, new User().getClass());
	    us.addUser(user);
	    
	}
	
	@RequestMapping("/deleteUser")
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		   
		Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(request.getReader());
        
        User user = gson.fromJson(obj, new User().getClass());
        us.deleteUser(user);
	}
	
	@RequestMapping("/updateUser")
	public void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		   
		Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(request.getReader());

        User user = gson.fromJson(obj, new User().getClass());
        us.updateUser(user);
	}

	@RequestMapping("/updateUserPassword")
	public void updateUserPassword(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		   
		Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(request.getReader());

        User user = gson.fromJson(obj, new User().getClass());
        us.updateUserPassword(user);
	}
	
	@RequestMapping("/updateUserRole")
	public void updateUserRole(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		   
		Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(request.getReader());

        User user = gson.fromJson(obj, new User().getClass());
        us.updateUserRole(user);
	}
	
}

