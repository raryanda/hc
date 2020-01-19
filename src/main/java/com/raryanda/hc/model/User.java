package com.raryanda.hc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    private Long userId;
    private String userName;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
