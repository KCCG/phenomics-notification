package au.org.garvan.kccg.notifications.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.NamedArg;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dinuka
 */
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
@Data
public class EmailNotificationRequestDto implements Serializable {

    private static final long serialVersionUID = -1528423248799532018L;

    @JsonProperty("toRecipients")
    private List<String> toRecipients;

    @JsonProperty("ccRecipients")
    private List<String> ccRecipients;

    @JsonProperty("bccRecipients")
    private List<String> bccRecipients;

    @JsonProperty(value = "message", required = true)
    @NotNull
    private String message;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("emailAttachments")
    private List<EmailAttachmentInfoDto> emailAttachments = new LinkedList<>();

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("uniqueID")
    private String uniqueID;



    public EmailNotificationRequestDto(@JsonProperty("toRecipients") List<String> toRecipients,
                                       @JsonProperty("ccRecipients") List<String> ccRecipients,
                                       @JsonProperty("bccRecipients") List<String> bccRecipients, @JsonProperty("message") String message,
                                       @JsonProperty("subject") String subject,
                                       @JsonProperty("emailAttachments") List<EmailAttachmentInfoDto> emailAttachments,
                                       @JsonProperty("sender") String sender,
                                       @JsonProperty("uniqueID") String uniqueID
                                       ) {
        this.toRecipients = toRecipients;
        this.ccRecipients = ccRecipients;
        this.bccRecipients = bccRecipients;
        this.message = message;
        this.subject = subject;
        this.emailAttachments = emailAttachments;
        this.sender = sender;
        this.uniqueID = uniqueID;
    }



}
