package com.sf;

import com.sf.domain.entity.Role;
import com.sf.domain.entity.State;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;


/**.
 * 应用业务类
 */
@Data
public class Application {
  public static Map<Integer, Application> appMap = new HashMap<>();
  private static Integer id = 1;
  private Integer appId;
  private String name;
  private String desc;
  private List<State> states;
  private String beginState;
  private String endState;
  private List<Role> roles;

  public Application() {
    appId = id++;
  }

}
