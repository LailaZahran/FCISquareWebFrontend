package com.application;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HomePageServices {
	@Context
	HttpServletRequest request;

	@GET
	@Path("/showHomePage")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePage(){
		return Response.ok(new Viewable("/showHomePage.jsp")).build();
	}
	
	@GET
	@Path("/sort")
	@Produces(MediaType.TEXT_HTML)
	public Response sort(){
		return Response.ok(new Viewable("/sort.jsp")).build();
	}
	/**
	 * 
	 * @param userId
	 * @return
	 */
	@POST
	@Path("/showHomePage")
	@Produces(MediaType.TEXT_PLAIN)
	public Response showHomePage(@FormParam("userId") int userId){
		HttpSession session = request.getSession();
		//Long id = (Long) session.getAttribute("id");
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/showHomePage";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/showHomePage";

		String urlParameters = "userId=" + userId ;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
		    session.setAttribute("userId", obj.get("userId"));
		    Map<String, String> map = new HashMap<String,String>();

		    map.put("userId", (String) obj.get("userId"));

		    return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@POST
	@Path("/sort")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sort(@FormParam("userId") int userId){ //what is the parameters??
		HttpSession session = request.getSession();
		//Long id = (Long) session.getAttribute("id");
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/sort";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/sort";

		String urlParameters = "userId=" + userId ;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
		    session.setAttribute("userId", obj.get("userId"));
		    Map<String, String> map = new HashMap<String,String>();

		    map.put("userId", (String) obj.get("userId"));

		    return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	

}
