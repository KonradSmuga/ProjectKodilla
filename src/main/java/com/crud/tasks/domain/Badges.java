package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Badges {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("Attachment")
    private AttachmentByType attachmentByType;

    public int getVotes() {
        return votes;
    }

    public AttachmentByType getAttachmentByType() {
        return attachmentByType;
    }
}
