package net.freshservers.support.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TicketCommand {
    private String sendTo;
    private String body;
    private List<String> ccs = new LinkedList<>();
    private String requesterName;
    private String requesterEmail;
    private String subject;
    private String concept;
    private Long group; //zendesk group

}
