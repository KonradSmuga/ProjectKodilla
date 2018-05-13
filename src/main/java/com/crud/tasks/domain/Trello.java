package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter

public class Trello {

    @JsonProperty("trello")
    private int board;

    @JsonProperty("card")
    private int card;

}
