package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	private RestTemplate restTemplate =new RestTemplate();
	private String API_BASE_URL = "https://cat-data.netlify.app/api/facts/random";

	@Override
	public CatFact getFact() {
		String url = API_BASE_URL;
		// TODO Auto-generated method stub
		return restTemplate.getForObject(url, CatFact.class);
	}

}
