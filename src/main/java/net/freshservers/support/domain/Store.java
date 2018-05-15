package net.freshservers.support.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "DBACCESS")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RECID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CONID")
    private Concept concept;

    @Column(name = "DISPLAY_NAME")
    private String name;

    @ManyToMany(mappedBy = "stores")
    private Set<User> users;
}
