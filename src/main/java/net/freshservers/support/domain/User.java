package net.freshservers.support.domain;

import lombok.Data;
import javax.persistence.*;

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

    private String concept;

    private String typeUser;

    private Boolean activeflg;
}
