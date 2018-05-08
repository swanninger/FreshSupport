package net.freshservers.support.zen.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {
    private String body;

    public Comment(String body) {
        this.body = body;
    }
}
