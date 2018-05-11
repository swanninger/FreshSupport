package net.freshservers.support.zen.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    private String body;

    public Comment(String body) {
        this.body = body;
    }
}
