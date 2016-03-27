package com.application;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/")
public class UserController {

	@Context
	HttpServletRequest request;

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public Response loginPage() {
		return Response.ok(new Viewable("/Login.jsp")).build();
	}

	@GET
	@Path("/signUp")
	@Produces(MediaType.TEXT_HTML)
	public Response signUpPage() {
		return Response.ok(new Viewable("/Signup.jsp")).build();
	}
	
	@GET
	@Path("/showLocation")
	@Produces(MediaType.TEXT_HTML)
	public Response showLocationPage(){
		return Response.ok(new Viewable("/ShowLocation.jsp")).build();
	}
	
	@GET
	@Path("/followUser")
	@Produces(MediaType.TEXT_HTML)
	public Response showFollowerse(){
		return Response.ok(new Viewable("/followUser.jsp")).build();
	}
	
	@GET
	@Path("/unFollowUser")
	@Produces(MediaType.TEXT_HTML)
	public Response showUnFollowers(){
		return Response.ok(new Viewable("/unFollowUser.jsp")).build();
	}
	
	@GET
	@Path("/getFollowerPosition")
	@Produces(MediaType.TEXT_HTML)
	public Response showFollowingList(){
		return Response.ok(new Viewable("/getFollowerPosition.jsp")).build();
	}
	
	@GET
	@Path("/getFollowers")
	@Produces(MediaType.TEXT_HTML)
	public Response showFollowerPosition(){
		return Response.ok(new Viewable("/getFollowers.jsp")).build();
	}

	@POST
	@Path("/updateMyLocation")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateLocation(@FormParam("lat") String lat, @FormParam("long") String lon){
		HttpSession session = request.getSession();
		Long id = (Long) session.getAttribute("id");
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/updatePosition";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/login";

		String urlParameters = "id=" + id + "&lat=" + lat + "&long="+ lon;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject)parser.parse(retJson);
			Long status = (Long) obj.get("status");
			if(status == 1)
				return "Your location is updated";
			else
				return "A problem occured";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "A problem occured";
		
	}
	
	@POST
	@Path("/doLogin")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePage(@FormParam("email") String email,
			@FormParam("pass") String pass) {
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/login";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/login";

		String urlParameters = "email=" + email + "&pass=" + pass;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("email", obj.get("email"));
			session.setAttribute("name", obj.get("name"));
			session.setAttribute("id", obj.get("id"));
			session.setAttribute("lat", obj.get("lat"));
			session.setAttribute("long", obj.get("long"));
			session.setAttribute("pass", obj.get("pass"));
			Map<String, String> map = new HashMap<String, String>();

			map.put("name", (String) obj.get("name"));
			map.put("email", (String) obj.get("email"));

			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@POST
	@Path("/doSignUp")
	@Produces(MediaType.TEXT_HTML)
	public Response showHomePage(@FormParam("name") String name,
			@FormParam("email") String email, @FormParam("pass") String pass) {
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/signup";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/signup";

		String urlParameters = "name=" + name + "&email=" + email + "&pass="
				+ pass;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("email", obj.get("email"));
			session.setAttribute("name", obj.get("name"));
			session.setAttribute("id", obj.get("id"));
			session.setAttribute("lat", obj.get("lat"));
			session.setAttribute("long", obj.get("long"));
			session.setAttribute("pass", obj.get("pass"));
			Map<String, String> map = new HashMap<String, String>();

			map.put("name", (String) obj.get("name"));
			map.put("email", (String) obj.get("email"));

			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	
	@POST
	@Path("/dofollowUser")
    @Produces(MediaType.TEXT_HTML)
	public String showFollowers(@FormParam("followerid") String id1,
			@FormParam("followingid") String id2) {
		
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/followUser";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/followUser";

	   String urlParameters = "followerid=" + id1 + "&followingid=" + id2;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject)parser.parse(retJson);
			Long check = (Long) obj.get("check");
			if(check == 1)
				return "Your Follow is Done";
			else
				return "A problem occured";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "A problem occured";

	}
    
	////////////////unfollowUser//////////////////
	@POST
	@Path("/dounFollowUser")
	//@Produces(MediaType.TEXT_HTML)
	public String showUnFollowers(@FormParam("followerid") String id1,
			@FormParam("followingid") String id2) {
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/unFollowUser";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/unFollowUser";

		String urlParameters = "followerid=" + id1 + "&followingid=" + id2;
		// System.out.println(urlParameters);
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject)parser.parse(retJson);
			Long check = (Long) obj.get("check");
			if(check == 1)
				return "Your unFollow is Done";
			else
				return "A problem occured";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "A problem occured";

	}
	/////////////getfollowers///////////////
	@POST
	@Path("/dogetFollowers")
	//@Produces(MediaType.TEXT_HTML)
	public Response showFollowingList(@FormParam("id") String id1) {
		String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/getFollowers";
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/getFollowers";

		String urlParameters = "followerid=" + id1 ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		HttpSession session = request.getSession();
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(retJson);
			
			session.setAttribute("name", obj.get("name"));
			session.setAttribute("id", obj.get("id"));
			
			Map<String, String> map = new HashMap<String, String>();

			map.put("id", (String) obj.get("id"));
			map.put("name", (String) obj.get("name"));

			return Response.ok(new Viewable("/home.jsp", map)).build();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
/////////////getFollowerPosition///////////////

@POST
@Path("/dogetFollowerPosition")
@Produces(MediaType.TEXT_HTML)
public Response showFollowerPosition(@FormParam("id") String id1) {
String serviceUrl = "http://firstapp-sw2projectfci.rhcloud.com/FCISquare/rest/getFollowerPosition";
//String serviceUrl = "http://localhost:8080/FCISquare/rest/getFollowerPosition";

String urlParameters = "followerid=" + id1;
String retJson = Connection.connect(serviceUrl, urlParameters, "POST", "application/x-www-form-urlencoded;charset=UTF-8");
HttpSession session = request.getSession();
JSONObject obj = new JSONObject();
JSONParser parser = new JSONParser();
try {
    obj = (JSONObject) parser.parse(retJson);
    session.setAttribute("lat", obj.get("lat"));
	session.setAttribute("long", obj.get("long"));
    Map<String, String> map = new HashMap<String,String>();

    map.put("lat", (String) obj.get("lat"));
    map.put("long", (String) obj.get("long"));

    return Response.ok(new Viewable("/home.jsp", map)).build();

} catch (ParseException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
return null;

}

}
