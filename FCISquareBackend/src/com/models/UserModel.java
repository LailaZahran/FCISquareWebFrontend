package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class UserModel {

	/**
	 * This class holds the user basic functions (sign-in / sign-up / followUser/ unfollowUser
	 * getFollowersList / GetFollowerPosition )
	 */
	
	private String name;
	private String email;
	private String pass;
	private Integer id;
	private Double lat;
	private Double lon;
	
	public String getPass(){
		return pass;
	}
	
	public void setPass(String pass){
		this.pass = pass;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	/**
	 * 
	 * @param name
	 * @param email
	 * @param pass
	 * @return
	 */
	public static UserModel addNewUser(String name, String email, String pass) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Insert into users (`name`,`email`,`password`) VALUES  (?,?,?)";
			// System.out.println(sql);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, pass);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.id = rs.getInt(1);
				user.email = email;
				user.pass = pass;
				user.name = name;
				user.lat = 0.0;
				user.lon = 0.0;
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 
	 * @param email
	 * @param pass
	 * @return
	 */
	public static UserModel login(String email, String pass) {
		try {
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Select * from users where `email` = ? and `password` = ?";
			PreparedStatement stmt;
            
			stmt = conn.prepareStatement(sql);
			
			
			stmt.setString(1, email);
			stmt.setString(2, pass);
			
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				user.id = rs.getInt(1);
				user.email = rs.getString("email");
				user.pass = rs.getString("password");
				user.name = rs.getString("name");
				user.lat = rs.getDouble("lat");
				user.lon = rs.getDouble("long");
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//////////// Follow User/////////////////////////
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	public static boolean followUser(int id1, int id2) {
		try {
			
			Connection conn = DBConnection.getActiveConnection();
			
			String sql= "INSERT INTO followers (`followerID`, `followingID`) VALUES(?,?)";
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.executeUpdate();
		 
			//////////////////////////////////////////////////
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				UserModel user = new UserModel();
				UserModel user2 = new UserModel();
				user.id = rs.getInt(1);
	            user2.id=rs.getInt(2);
			}
			System.out.println(id1);
			System.out.println(id2);
				return true;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
////////////unFollow User/////////////////////////
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	public static boolean unFollowUser(int id1,int id2) {
		try {
			
			Connection conn = DBConnection.getActiveConnection();
			//Check if this statement is written correctly or not
			String sql = "DELETE FROM followers WHERE `followerID` = ? and `followingID` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				UserModel user = new UserModel();
				UserModel user2 = new UserModel();
				user.id = rs.getInt(1);
	            user2.id=rs.getInt(2);
			}
			System.out.println(id1);
			System.out.println(id2);
		 
			return true;
			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return false;
		}
	
	//////////// ID to Name/////////////////////////
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static String fromIDtoName(int id)
	{
		try{
		Connection conn = DBConnection.getActiveConnection();
		
		String sql = "Select name from users where `id` = ?";
		
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			UserModel user = new UserModel();
			
			user.name = rs.getString("name");
			return user.name;
		}
		return null;
		
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}return null;
	
	}

	//////////// get Followers/////////////////////////
	/**
	 * 
	 * @param id1
	 * @return
	 */
	public static UserModel getFollowers(int id1) {
		String name;
		try {
			Connection conn = DBConnection.getActiveConnection();
			//Check if this statement is true or not
			String sql =" SELECT `followerID` FROM `followers` WHERE `followingID`=?";
			
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id1);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				UserModel user = new UserModel();
				user.id = rs.getInt("followerID");
				
				name=fromIDtoName(user.id);
				System.out.println(name);
				user.name = name;
			   return user;	
		    }
		 
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 
	 * @param id
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static boolean updateUserPosition(Integer id, Double lat, Double lon) {
		try{
			Connection conn = DBConnection.getActiveConnection();
			String sql = "Update users set `lat` = ? , `long` = ? where `id` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, lat);
			stmt.setDouble(2, lon);
			stmt.setInt(3, id);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
////////////getFollowerLastPosition/////////////////////////
	/**
	 * 
	 * @param followerID
	 * @return
	 */
	public static UserModel getFollowerPosition(Integer followerID) {
		try{
			Connection conn = DBConnection.getActiveConnection();
			
			String sql = "SELECT `lat`, `long` FROM `users` WHERE`id`=?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, followerID);
		
			ResultSet rs =  stmt.executeQuery();
			if (rs.next()) {
				UserModel user = new UserModel();
				//user.id = rs.getInt(1);
				user.lat = rs.getDouble("lat");
				user.lon = rs.getDouble("long");
				System.out.println(user.lat);
				System.out.println(user.lon);
				return user;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
