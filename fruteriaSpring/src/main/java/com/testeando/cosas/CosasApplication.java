package com.testeando.cosas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fruteria.DTO.ClienteDTO;
import fruteria.Entity.ClienteEntity;
import fruteria.Provider.ClienteProvider;
import fruteria.Repository.ClienteRepository;
import fruteria.Repository.ProviderImp.ClienteProviderImp;

@EnableJpaRepositories(basePackages = "fruteria.Repository")
@ComponentScan(basePackages = "fruteria.Controller, fruteria.Provider, fruteria.Repository.ProviderImp, fruteria.Entity, fruteria.DTO")
@EntityScan(basePackages = "fruteria.Entity")
@EnableJpaAuditing
@SpringBootApplication
public class CosasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosasApplication.class, args);

	}
	/*
	@Autowired
	ClienteRepository clienteRepository;

	
    @Bean
    public CommandLineRunner startup() {
        return (args) -> {
        	ClienteEntity cliente = new ClienteEntity(1,"nom1");
        	ClienteEntity cliente2 = new ClienteEntity(2,"nom2");
        	ClienteEntity cliente3 = new ClienteEntity(3,"nom3");
          clienteRepository.save(cliente);
          clienteRepository.save(cliente2);
          clienteRepository.save(cliente3);
          	
          clienteRepository.findAll().forEach(System.out::println);
        };
    }*/
}
