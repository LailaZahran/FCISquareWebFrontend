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

public class NotificationServices {
	@Context
	HttpServletRequest request;

	@GET
	@Path("respondNotification")
	@Produces(MediaType.TEXT_HTML)
	public Response respondNotification(){
		return Response.ok(new Viewable("/respondNotification.jsp")).build();
	}
	
	/**
	 * 
	 * @param notificationID
	 * @param type
	 * @return
	 */
	@POST
	@Path("/respondNotification")
	@Produces(MediaType.TEXT_HTML)
	public Response respondNotification(@FormParam("notificationID") int notificationID,
			 @FormParam("type") int type) { 
	String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/respondNotification";
	//String serviceUrl = "http://localhost:8080/FCISquare/rest/respondNotification";

	String urlParameters = "notificationID=" + notificationID + "&type" + type;
	String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
	HttpSession session = request.getSession();
	JSONObject obj = new JSONObject();
	JSONParser parser = new JSONParser();
	try {
	    obj = (JSONObject) parser.parse(retJson);
	    session.setAttribute("notificationID", obj.get("notificationID"));
		session.setAttribute("type", obj.get("type"));
	    Map<String, String> map = new HashMap<String,String>();

	    map.put("notificationID", (String) obj.get("notificationID"));
	    map.put("type", (String) obj.get("type"));

	    return Response.ok(new Viewable("/home.jsp", map)).build();

	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;

	}


}
