package com.inrhythm.service;

import com.inrhythm.pojo.InRhythmResponse;
import com.inrhythm.service.impl.ResponseModifier;

public interface IJsonService {

	public InRhythmResponse parseJson(String url) throws Exception;
	
	public InRhythmResponse updateJson(String url, ResponseModifier responseModifier) throws Exception;
	
	public void writeJsonToFile(InRhythmResponse inRhythmResponse) throws Exception;
	
}
