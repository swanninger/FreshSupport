package net.freshservers.support.zen.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class Comment {
    private String body;

    public Comment(String body) {
        this.body = body;
    }
}
