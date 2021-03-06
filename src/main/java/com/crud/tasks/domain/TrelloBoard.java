package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@AllArgsConstructor
public class TrelloBoard {
    private String id;
    private String name;
    private List<TrelloList> lists;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TrelloList> getLists() {
        return lists;
    }
}
