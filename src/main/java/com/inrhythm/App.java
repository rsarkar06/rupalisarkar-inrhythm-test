package com.inrhythm;

import com.inrhythm.pojo.InRhythmResponse;
import com.inrhythm.pojo.Posts;
import com.inrhythm.service.IJsonService;
import com.inrhythm.service.impl.JsonService;

public class App {
	public static void main(String[] args) {
	
		String url = "http://jsonplaceholder.typicode.com/posts";	

		System.out.println("STARTED");
		
		IJsonService service = new JsonService();
		try{
			
			InRhythmResponse updatedResponse = service.updateJson(url, res -> {
				Posts postToUpdate = res.getPosts().get(3);
				postToUpdate.setBody("inrhythm");
				postToUpdate.setTitle("inrhythm");
			});
			
			System.out.println("Total user count in json : " + updatedResponse.getUserCount()); 
			
			System.out.println("Modified JSON : " + 
						String.format("Title - %s , Body - %s", updatedResponse.getPosts().get(3).getTitle(), 
								updatedResponse.getPosts().get(3).getBody()));
			
			service.writeJsonToFile(updatedResponse);
			
		}catch(Exception ex){
			System.out.println("Error while parsing and updating json object" + ex.getMessage());
			System.out.println(ex.getCause());
		}

		System.out.println("FINISHED");

	}
}
