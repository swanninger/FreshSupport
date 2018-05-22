package net.freshservers.support.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"users"})
@ToString(exclude = "users")
@Table(name = "DBACCESS")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RECID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONID", referencedColumnName = "RECID")
    private Concept concept;

    @Column(name = "DBNAME")
    private String name;

    private String display_name;

    @ManyToMany(mappedBy = "stores")
    private Set<User> users;
}
