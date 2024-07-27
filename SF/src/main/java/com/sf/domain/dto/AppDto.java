package com.sf.domain.dto;

import com.sf.domain.entity.Role;
import com.sf.domain.entity.State;
import java.util.List;
import lombok.Data;


/**.
 * 接收前端应用的输入
 */
@Data
public class AppDto {
  private String name;
  private String desc;
  private List<State> states;
  private String beginState;
  private String endState;
  private List<Role> roles;
}
