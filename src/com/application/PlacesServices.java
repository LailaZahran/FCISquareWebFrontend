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

public class PlacesServices {
	@Context
	HttpServletRequest request;

	@GET
	@Path("/addNewPlace")
	@Produces(MediaType.TEXT_HTML)
	public Response addNewPlace(){
		return Response.ok(new Viewable("/addNewPlace.jsp")).build();
	}
	
	@GET
	@Path("/savePlace")
	@Produces(MediaType.TEXT_HTML)
	public Response savePlace() {
		return Response.ok(new Viewable("/savePlace.jsp")).build();
	}
	
	/**
	 * 
	 * @param name
	 * @param desc
	 * @param lat
	 * @param lon
	 * @return
	 */
	
	@POST
	@Path("/addNewPlace")
    @Produces(MediaType.TEXT_HTML)
	public Response addNewPlace(@FormParam("name") String name,@FormParam("desc") String desc, 
            @FormParam("lat") Double lat,@FormParam("lon") Double lon) {
		
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/addNewPlace";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/addNewPlace";

	   String urlParameters = "name=" + name + "&desc=" + desc;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
		    obj = (JSONObject) parser.parse(retJson);
		    session.setAttribute("name", obj.get("name"));
		    session.setAttribute("desc", obj.get("desc"));
		    session.setAttribute("lat", obj.get("lat"));
			session.setAttribute("long", obj.get("long"));
		    Map<String, String> map = new HashMap<String,String>();

		    map.put("lat", (String) obj.get("lat"));
		    map.put("long", (String) obj.get("long"));
		    map.put("name", (String) obj.get("name"));
		    map.put("desc", (String) obj.get("desc"));
		    
		    return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * 
	 * @param UserId
	 * @param name
	 * @return
	 */
	
	@POST
	@Path("/savePlace")
    @Produces(MediaType.TEXT_HTML)
	public String savePlace(@FormParam("UserId") int UserId ,@FormParam("name") String name) {
		
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/savePlace";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/savePlace";

	   String urlParameters = "UserId=" + UserId + "&name=" + name;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(retJson);
		    session.setAttribute("UserId", obj.get("UserId"));
		    session.setAttribute("name", obj.get("name"));

		    Map<String, String> map = new HashMap<String,String>();
		    
		    map.put("UserId", (String) obj.get("UserId"));
		    map.put("name", (String) obj.get("name"));
		    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
}
