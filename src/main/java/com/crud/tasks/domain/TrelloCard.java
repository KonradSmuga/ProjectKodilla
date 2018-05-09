package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TrelloCard {
    private String name;
    private String description;
    private String pos;
    private String listId;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPos() {
        return pos;
    }

    public String getListId() {
        return listId;
    }
}