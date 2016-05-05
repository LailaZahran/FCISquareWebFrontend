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

public class ChechInServices {
	@Context
	HttpServletRequest request;

	@GET
	@Path("/checkin")
	@Produces(MediaType.TEXT_HTML)
	public Response checkin(){
		return Response.ok(new Viewable("/checkin.jsp")).build();
	}
	
	@GET
	@Path("/comment")
	@Produces(MediaType.TEXT_HTML)
	public Response comment(){
		return Response.ok(new Viewable("/comment.jsp")).build();
	}
	
	@GET
	@Path("/like")
	@Produces(MediaType.TEXT_HTML)
	public Response like(){
		return Response.ok(new Viewable("/like.jsp")).build();
	}
	/**
	 * 
	 * @param placeName
	 * @param UserId
	 * @return
	 */
	@POST
	@Path("/chechin")
    @Produces(MediaType.TEXT_HTML)
	public Response checkin(@FormParam("placeName") String placeName, @FormParam("UserId") int UserId) {
		
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/checkin";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/checkin";

	   String urlParameters = "placeName=" + placeName + "&UserId=" + UserId;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
		    obj = (JSONObject) parser.parse(retJson);
		    session.setAttribute("placeName", obj.get("placeName"));
		    session.setAttribute("UserId", obj.get("UserId"));
		    
		    Map<String, String> map = new HashMap<String,String>();
 
		    map.put("placeName", (String) obj.get("placeName"));
		    map.put("UserId", (String) obj.get("UserId"));
		    
		    return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @param checkinId
	 * @param comment
	 * @param userId
	 * @return
	 */
	@POST
	@Path("/comment")
    @Produces(MediaType.TEXT_HTML)
	public Response comment(@FormParam("checkinId") int checkinId, @FormParam("comment") String comment, 
			@FormParam("userId") int userId) {
		
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/comment";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/comment";

	   String urlParameters = "checkinId=" + checkinId + "&comment=" + comment + "&userId=" + userId;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
		    obj = (JSONObject) parser.parse(retJson);
		    session.setAttribute("checkinId", obj.get("checkinId"));
		    session.setAttribute("comment", obj.get("comment"));
		    session.setAttribute("userId", obj.get("userId"));
		    
		    Map<String, String> map = new HashMap<String,String>();
            
		    map.put("checkinId", (String) obj.get("checkinId"));
		    map.put("comment", (String) obj.get("comment"));
		    map.put("userId", (String) obj.get("userId"));
		    
		    return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * 
	 * @param checkinId
	 * @param userId
	 * @return
	 */
	@POST
	@Path("/like")
    @Produces(MediaType.TEXT_HTML)
	public Response like(@FormParam("checkinId") int checkinId, @FormParam("userId") int userId) {
		
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/like";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/like";

	   String urlParameters = "checkinId=" + checkinId + "&userId=" + userId;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
		    obj = (JSONObject) parser.parse(retJson);
		    session.setAttribute("checkinId", obj.get("checkinId"));
		    session.setAttribute("userId", obj.get("userId"));
		    
		    Map<String, String> map = new HashMap<String,String>();
            
		    map.put("checkinId", (String) obj.get("checkinId"));
		    map.put("userId", (String) obj.get("userId"));
		    
		    return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
