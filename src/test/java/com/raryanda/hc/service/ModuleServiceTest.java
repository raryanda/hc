package com.raryanda.hc.service;

import com.raryanda.hc.SpringTestConverterConfig;
import com.raryanda.hc.SpringTestServiceConfig;
import com.raryanda.hc.model.Module;
import com.raryanda.hc.repository.ModuleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SpringTestServiceConfig.class, SpringTestConverterConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase
@EntityScan("com.raryanda.hc.model")
@EnableJpaRepositories("com.raryanda.hc.repository")
public class ModuleServiceTest {

    @Autowired
    ModuleService moduleService;

    @Autowired
    ModuleRepository repository;

    @Test
    public void getAllModules() {
        List<Module> modules = moduleService.getAllModules();
        assertEquals(modules.get(0).getModuleName(), "Category");
        assertEquals(modules.get(1).getModuleName(), "History");
        assertEquals(modules.get(2).getModuleName(), "Promo");

        Module module = new Module();
        module.setModuleId(6L);
        module.setModuleName("Module");
        repository.save(module);

        assertEquals(moduleService.getAllModules().size(), 6);
    }
}
