package com.sf.domain.entity;

import lombok.Data;

/**.
 * 事件类
 */
@Data
public class Event {
  private String name;
  private String fromState;
  private String toState;
  private String role;
}
