package com.orange.bootcamp.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevEmailService implements EmailServiceInterface {

	@Override
	public boolean enviar(String email) {
		System.out.println("Finge que envia email");
		return true;
	}

}
