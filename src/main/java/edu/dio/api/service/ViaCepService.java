package edu.dio.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.dio.api.model.Endereco;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")

@Service
public interface ViaCepService {
    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json")
    Endereco consultarCep(@PathVariable("cep") String cep);

}
