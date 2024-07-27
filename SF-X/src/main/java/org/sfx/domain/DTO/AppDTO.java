package org.sfx.domain.DTO;

import lombok.Data;
import org.sfx.domain.entity.Role;
import org.sfx.domain.entity.State;

import java.util.List;

@Data
public class AppDTO {
    private String name;
    private String desc;
    private List<State> states;
    private String beginState;
    private String endState;
    private List<Role> roles;
}
