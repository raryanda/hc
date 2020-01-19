package com.raryanda.hc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "modules")
public class Module {
    @Id
    private Long moduleId;
    private String moduleName;
}
