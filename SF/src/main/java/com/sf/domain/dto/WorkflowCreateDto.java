package com.sf.domain.dto;

import com.sf.domain.entity.Event;
import com.sf.domain.entity.State;
import java.util.List;
import lombok.Data;

/**.
 * 接收前端创建流程的输入
 */
@Data
public class WorkflowCreateDto {
  private Integer appId;
  private String name;
  private String desc;
  private List<State> states;
  private List<Event> events;
}
