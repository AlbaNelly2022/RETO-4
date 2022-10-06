/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Modelo.Client;
import com.example.demo.Repositorio.ClientRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service

public class ClientServicio {

    @Autowired
    private ClientRepositorio clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int clientId) {
        return clientRepository.getClient(clientId);
    }

    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> e = clientRepository.getClient(client.getIdClient());
            if (e.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public boolean deleteClient(int Idclient) {
        Boolean d = getClient(Idclient).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return d;

    }

    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> g = clientRepository.getClient(client.getIdClient());
            if (!g.isEmpty()) {
             ///   if (client.getEmail() != null) {
             ///       g.get().setEmail(client.getEmail());
             ///   }
                if (client.getAge() != null) {
                    g.get().setAge(client.getAge());
                }
                if (client.getName() != null) {
                    g.get().setName(client.getName());
                }
                if (client.getPassword() != null) {
                    g.get().setPassword(client.getPassword());
                }
                clientRepository.save(g.get());
                return g.get();
            } else {
                return client;
            }
        } else {
            return client;
        }
    }

}
