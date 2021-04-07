package com.orange.bootcamp.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class EmailService implements EmailServiceInterface {

	@Override
	public boolean enviar(String email) {
		System.out.println("Enviando email de fato");
		System.out.println(email);
		System.out.println("Email enviado: true");
		System.out.println("Não enviado: false");
		return true;
	}

}
