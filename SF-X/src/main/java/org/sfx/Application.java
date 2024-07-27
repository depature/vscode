package org.sfx;

import lombok.Data;
import org.sfx.domain.entity.Role;
import org.sfx.domain.entity.State;

import java.util.List;

@Data
public class Application {
    private static Integer id=0;
    private Integer appId;
    private String name;
    private String desc;
    private List<State> states;
    private String beginState;
    private String endState;
    private List<Role> roles;
    public Application(){
        appId=id++;
    }

}
