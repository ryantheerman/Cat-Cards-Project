package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	private RestTemplate restTemplate =new RestTemplate();
	private String API_BASE_URL = "https://cat-data.netlify.app/api/pictures/random";

	@Override
	public CatPic getPic() {
		String url = API_BASE_URL;
		// TODO Auto-generated method stub
		return restTemplate.getForObject(url,CatPic.class);
	}

}	
