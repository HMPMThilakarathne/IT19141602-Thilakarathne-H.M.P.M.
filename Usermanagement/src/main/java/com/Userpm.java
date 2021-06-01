package com;

import model.User;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;

import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;


@Path("/User") 
public class Userpm {
	
	
	User itemObj = new User();
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readItems() 
		{ 
			return itemObj.readItems(); 
		} 
		
		
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN) 
		
		public String insertItem
		
		(@FormParam("username") String username,
		@FormParam("institute")String institue, 
		@FormParam("phone")String phone,
		@FormParam("email") String email,
	    @FormParam("password")String password)
		
		{ 
			String output = itemObj.insertItem( username,  institue, phone,email,password ); 
			return output; 
		}
		
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateItem (String itemData)
		{
			//Convert the input string to a JSON object 
			
			JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
			
			//Read the values from the JSON object
			
			String id = itemObject.get("id").getAsString();
			String username = itemObject.get("username").getAsString(); 
			String institue = itemObject.get("institue").getAsString(); 
			String phone = itemObject.get("phone").getAsString();
			String email = itemObject.get("email").getAsString();  
			String  password = itemObject.get(" password").getAsString(); 
			  
			
			String output = itemObj.updateItem(id, username, institue, phone, email, password); 
			
			return output; 
			}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteItem(String itemData)
		{
			//Convert the input string to an XML document
			
			Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
			
			//Read the value from the element <itemID>
			
			String id = doc.select("id").text(); 
			String output = itemObj.deleteItem(id);
			
			return output;
			}
		}
