package edu.dio.api.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dio.api.model.Cliente;
import edu.dio.api.model.ClienteRepository;
import edu.dio.api.model.Endereco;
import edu.dio.api.model.EnderecoRepository;
import edu.dio.api.service.ClienteService;
import edu.dio.api.service.ViaCepService;

@Service
public class ClienteServiceImplementation implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        this.saveOrUpdateClientes(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // buscar cliente por id, caso exista
        Optional<Cliente> clienteDB = clienteRepository.findById(id);

        if (clienteDB.isPresent()) {
            this.saveOrUpdateClientes(cliente);
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void saveOrUpdateClientes(Cliente cliente) {
        // Verifica se o endereco ja existe.
        String cep = cliente.getEndereco().getCep();

        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

}
