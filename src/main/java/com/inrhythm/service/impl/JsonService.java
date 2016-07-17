package com.inrhythm.service.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inrhythm.pojo.InRhythmResponse;
import com.inrhythm.pojo.Posts;
import com.inrhythm.service.IJsonService;

public class JsonService implements IJsonService{

	public InRhythmResponse parseJson(String url) throws Exception {
		
		if(isEmpty(url)){
			throw new RuntimeException("Exception while parsing json url, the url should not be empty.");
		}

		ObjectMapper objectMapper = new ObjectMapper();
		List<Posts> posts = objectMapper.readValue(inputStream(url) , new TypeReference<List<Posts>>() { });
	    
		int userCount = userCounts(posts);
	
		return inRhythmResponse(userCount, posts);
	}

	public InRhythmResponse updateJson(String url, ResponseModifier modifier) throws Exception {
		InRhythmResponse response = parseJson(url);
		
		modifier.modify(response);
		return response;
	}

	private InRhythmResponse inRhythmResponse(int userCount, List<Posts> posts) {
		InRhythmResponse inRhythmResponse = new InRhythmResponse();
		inRhythmResponse.setUserCount(userCount);
		inRhythmResponse.setPosts(posts);
		
		return inRhythmResponse;
	}

	private int userCounts(List<Posts> response) {
		return response.stream().map(p -> p.getUserId()).collect(Collectors.toSet()).size();
	}

	private InputStream inputStream(String url) throws Exception {
		URLConnection openConnection = new URL(url).openConnection();
		openConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
		
		return openConnection.getInputStream();
	}

	public static boolean isEmpty(final String str) {
		  return str == null || str.length() == 0;
	}

	@Override
	public void writeJsonToFile(InRhythmResponse inRhythmResponse) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File("test.json"), inRhythmResponse.getPosts());
	}

}
