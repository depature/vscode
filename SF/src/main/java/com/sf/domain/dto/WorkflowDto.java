package com.sf.domain.dto;

import com.sf.domain.entity.Event;
import com.sf.domain.entity.State;
import java.util.List;
import lombok.Data;


/**.
 * 接受前端流程的输入
 */
@Data
public class WorkflowDto {
  private Integer id;
  private String name;
  private String desc;
  private List<State> states;
  private List<Event> events;
}
