package org.sfx.domain.DTO;

import lombok.Data;
import org.sfx.domain.entity.State;
import org.sfx.domain.entity.event;

import java.util.List;

@Data
public class WorkflowDTO {
    private Integer id;
    private String name;
    private String desc;
    private List<State> states;
    private List<event> events;
}
