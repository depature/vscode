package com.sf.domain.dto;

import lombok.Data;

/**.
 * 接收前端流程查询的输入
 */
@Data
public class WorkflowQueryDto {
  private Integer page;
  private Integer pageSize;
}
