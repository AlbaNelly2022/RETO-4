/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Modelo.Machine;
import com.example.demo.Repositorio.MachineRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service

public class MachineServicio {

    @Autowired
    private MachineRepositorio machineRepository;

    public List<Machine> getAll() {
        return machineRepository.getAll();
    }

    public Optional<Machine> getMachine(int machineId) {
        return machineRepository.getMachine(machineId);
    }

    public Machine save(Machine machine) {
        if (machine.getId() == null) {
            return machineRepository.save(machine);
        } else {
            Optional<Machine> e = machineRepository.getMachine(machine.getId());
            if (e.isEmpty()) {
                return machineRepository.save(machine);
            } else {
                return machine;
            }
        }
    }

    public boolean deleteMachine(int IdMachine) {
        Boolean d = getMachine(IdMachine).map(machine -> {
            machineRepository.delete(machine);
            return true;
        }).orElse(false);
        return d;
    }

    public Machine update(Machine machine) {
        if (machine.getId() != null) {
            Optional<Machine> g = machineRepository.getMachine(machine.getId());

            if (!g.isEmpty()) {

                if (machine.getName() != null) {
                    g.get().setName(machine.getName());
                }
                if (machine.getBrand() != null) {
                    g.get().setBrand(machine.getBrand());
                }

                if (machine.getYear() != null) {
                    g.get().setYear(machine.getYear());
                }

                if (machine.getDescription() != null) {
                    g.get().setDescription(machine.getDescription());
                }

                if (machine.getCategory() != null) {
                    g.get().setCategory(machine.getCategory());
                }
                machineRepository.save(g.get());
                return g.get();
            } else {
                return machine;
            }
            }else{
            return machine;
            }
        }
    }
