package com.testeando.cosas;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fruteria.DTO.ClienteDTO;
import fruteria.Provider.ClienteProvider;
import fruteria.Repository.ProviderImp.ClienteProviderImp;

@SpringBootApplication
public class CosasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosasApplication.class, args);
	}

}
