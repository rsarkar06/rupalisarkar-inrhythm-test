package com.inrhythm;

import org.junit.Assert;
import org.junit.Test;

import com.inrhythm.pojo.InRhythmResponse;
import com.inrhythm.pojo.Posts;
import com.inrhythm.service.IJsonService;
import com.inrhythm.service.impl.JsonService;

import junit.framework.TestCase;

public class AppTest extends TestCase {
	
	private static final String URL = "http://jsonplaceholder.typicode.com/posts";

	@Test
	public void testPostModify() {

		IJsonService service = new JsonService();
		
		InRhythmResponse response = null;
		try {
			response = service.updateJson(URL, res -> {
				Posts posts = res.getPosts().get(3);
				posts.setBody("inrhythm");
				posts.setTitle("inrhythm");
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getCause());
			Assert.fail("Error while parsing and updating json object : " + e.getMessage());
		}

		int postCount = 0;
		for (Posts post : response.getPosts()) {
			if (postCount == 3) {
				assertTrue(post.getBody().equalsIgnoreCase("inrhythm") && post.getTitle().equalsIgnoreCase("inrhythm"));
			}
			postCount = postCount + 1;
		}
	}
	


	@Test
	public void testUserCount() {

		IJsonService service = new JsonService();

		InRhythmResponse response = null;
		try {
			response = service.parseJson(URL);
		} catch (Exception e) {
		   Assert.fail("Error while parsing response : " + e.getMessage());
		}

		assertTrue(response.getUserCount() == 10);
	}
}
