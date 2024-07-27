package com.sf.domain.dto;

import lombok.Data;

/**.
 * 接收流程流转的输入
 */
@Data
public class WorkflowActionDto {
  private Integer id;
  private String action;
  private String role;
}
