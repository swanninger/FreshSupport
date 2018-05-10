package net.freshservers.support.zen.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
public class Ticket {

    private Integer id;
    private String url;
    private String type;
    private String subject;
    private Comment comment;
    private String description;
    private String priority;
    private String status;
    private String recipient;
    private Integer group_id;
    private Integer[] collaborator_ids;
    private String[] collaborators;
    private Date created_at;
    private Date updated_at;
}
