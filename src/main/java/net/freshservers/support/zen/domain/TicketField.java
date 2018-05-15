package net.freshservers.support.zen.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TicketField {
    private int id;
    private String value;

    public TicketField(int id, String value) {
        this.id = id;
        this.value = value;
    }
}