package net.freshservers.support.zen.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Ticket {

    private int id;
    private String url;
    private String type;
    private String subject;
    private Comment comment;
    private String description;
    private String priority;
    private String status;
    private String recipient;
    private long group_id;
    private List<Integer> collaborator_ids;
    private List<Collaborators> collaborators;
    private Date created_at;
    private Date updated_at;
    private Requester requester;
    private List<TicketField> custom_fields;

    public Ticket(String subject, Comment comment){

    }

    public Ticket(String subject, Comment comment, Long group_id) {
        this.subject = subject;
        this.comment = comment;
        this.group_id = group_id;
    }
}
