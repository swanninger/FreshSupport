package net.freshservers.support.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"stores"})
@ToString(exclude = {"stores"})
@Table(name = "CONCEPTS")
public class Concept {

    @Id
    @GeneratedValue
    @Column(name = "RECID")
    private Long id;

    @Column(name = "concept")
    private String name;

    private String conceptName;

    @OneToMany(mappedBy = "concept")
    private Set<Store> stores;
}
