package net.freshservers.support.zen.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Requester {
    private String name;
    private String email;
//    private int locale_id;

    public Requester(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
