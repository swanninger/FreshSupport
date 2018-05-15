package net.freshservers.support.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "CONCEPTS")
public class Concept {

    @Id
    @GeneratedValue
    @Column(name = "RECID")
    private Long id;

    private String concept;

    private String conceptName;

    @OneToMany(mappedBy = "concept")
    private Set<Store> stores;
}
