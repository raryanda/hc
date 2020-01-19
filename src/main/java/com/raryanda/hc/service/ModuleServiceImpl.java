package com.raryanda.hc.service;

import com.raryanda.hc.model.Module;
import com.raryanda.hc.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository repository;

    @Override
    public List<Module> getAllModules() {
        return repository.findAll();
    }
}
