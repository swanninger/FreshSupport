package net.freshservers.support.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Data
@EqualsAndHashCode(exclude = {"stores"})
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RECID")
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PW")
    private String password;

    private String fname;
    private String lname;
    private String email;

    private String concept;

    private String typeUser;

    private Boolean activeflg;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERACCTBL",
            joinColumns = @JoinColumn(name = "USEID", referencedColumnName = "RECID"),
            inverseJoinColumns = @JoinColumn(name = "DBA_ID", referencedColumnName = "RECID")
    )
    private Set<Store> stores = new TreeSet<>();
}
