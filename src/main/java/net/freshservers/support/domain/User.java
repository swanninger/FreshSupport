package net.freshservers.support.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
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

    @ManyToMany
    @JoinTable(
            name = "USERACCTBL",
            joinColumns = @JoinColumn(name = "USEID", referencedColumnName = "RECID"),
            inverseJoinColumns = @JoinColumn(name = "DBA_ID", referencedColumnName = "RECID")
    )
    private Set<Store> stores;
}
