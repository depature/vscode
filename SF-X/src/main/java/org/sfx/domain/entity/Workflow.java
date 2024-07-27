package org.sfx.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Data
public class Workflow {
    private Integer id;
    private String name;
    private String desc;
    private String creator;
    private LocalDateTime createdTime;
    private Integer appId;
    private List<State> states;
    private List<event> events;
    public void Workflow(){
        this.id=new Random().nextInt();
        //TODO
    }
}
