package com.raryanda.hc.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class Group {
    @Id
    private Long groupId;
    private String groupName;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    /*@JoinTable(
            name = "group_modules",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id")
    )*/
    @JsonManagedReference
    private List<GroupModule> modules;
}
