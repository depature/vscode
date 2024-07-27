package com.sf.domain.entity;

import java.time.LocalDateTime;
import lombok.Data;

/**.
 * 内容类
 */
@Data
public class Content {
  private Integer id;
  private String name;
  private String desc;
  private String creator;
  private LocalDateTime createdTime;
}
