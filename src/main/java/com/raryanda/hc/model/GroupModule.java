package com.raryanda.hc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "group_modules")
public class GroupModule implements Serializable {
    @Embeddable
    @Getter
    @Setter
    private static class Pk implements Serializable {
        @Column(name = "module_id", nullable = false, updatable = false)
        private Long moduleId;
        @Column(name = "group_id", nullable = false, updatable = false)
        private Long groupId;
    }

    @EmbeddedId
    private Pk id;

    public GroupModule() {
        this.id = new Pk();
    }

    @MapsId("moduleId")
    @ManyToOne
    @JoinColumn(name = "module_id", insertable = false, updatable = false)
    private Module module;

    @MapsId("groupId")
    @ManyToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    @JsonBackReference
    private Group group;

    private Long moduleOrder;
}
