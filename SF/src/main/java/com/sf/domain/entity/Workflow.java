package com.sf.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**.
 * 流程类
 */
@Data
public class Workflow {
  private Integer id;
  private String name;
  private String desc;
  private String creator;
  private LocalDateTime createdTime;
  private Integer appId;
  private List<State> states;
  private List<Event> events;
}
