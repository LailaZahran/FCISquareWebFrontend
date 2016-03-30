package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;

import com.models.DBConnection;
import com.models.UserModel;

@Path("/")
public class Services {
	
    System.out.println("SignUp Service");
	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signUp(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		UserModel user = UserModel.addNewUser(name, email, pass);
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
	}
  
	/** 
	 * @param email
	 * @param pass
	 * @return
	 */
	 System.out.println("LogIn Service");
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		UserModel user = UserModel.login(email, pass);
		if(user==null)
		{
			return "error";
		}
		else{
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		json.put("email", user.getEmail());
		json.put("pass", user.getPass());
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		return json.toJSONString();
		}
	}
	
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	 System.out.println("FollowUser Service");
	@POST
	@Path("/followUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String followUser(@FormParam("followerid") String id1,
			@FormParam("followingid") String id2) {
		boolean check = UserModel.followUser(Integer.parseInt(id1), Integer.parseInt(id2));
		JSONObject json = new JSONObject();
		json.put("check", check ? 1 : 0);
		return json.toJSONString();
	}
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	 System.out.println("UnFollowUser Service");
	@POST
	@Path("/unFollowUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String unFollowUser(@FormParam("followerid") String id1,
			@FormParam("followingid") String id2) {
		boolean check = UserModel.unFollowUser(Integer.parseInt(id1), Integer.parseInt(id2));
		JSONObject json = new JSONObject();
		json.put("check", check ? 1 : 0);
		return json.toJSONString();
	}
	
	/**
	 * 
	 * @param id1
	 * @return
	 */
	 System.out.println("getFollowers Service");
	@POST
	@Path("/getFollowers")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFollowers(@FormParam("id") int id1) {
		UserModel  user= UserModel.getFollowers(id1);
		//return check;
		if(user==null)
		{
			return "error";
		}
		else{
		JSONObject json = new JSONObject();
		json.put("id", user.getId());
		json.put("name", user.getName());
		
		return json.toJSONString();
		}
		
	}
	/**
	 * 
	 * @param id1
	 * @return
	 */
	 System.out.println("getFollowerPosition Service");
	@POST
	@Path("/getFollowerPosition")
	@Produces(MediaType.TEXT_PLAIN)
	public String getFollowerPosition(@FormParam("id") int id1) {
       UserModel user=UserModel.getFollowerPosition(id1);	
       if(user==null)
		{
			return "error";
		}
		else{
		JSONObject json = new JSONObject();
		 
		json.put("lat", user.getLat());
		json.put("long", user.getLon());
		
		return json.toJSONString();
		}
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
}
