package com.sf.domain.vo;


import com.sf.domain.entity.Content;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**.
 * 传回给前端的分页
 */
@Data
@Builder
public class WorkflowPageVo {
  private Integer page;
  private Integer size;
  private Integer totalPage;
  private Integer total;
  private List<Content> content;
}
