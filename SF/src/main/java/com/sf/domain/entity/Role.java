package com.sf.domain.entity;

import java.util.List;
import lombok.Data;

/**.
 * 角色类
 */
@Data
public class Role {
  private String role;
  private List<Auth> auth;
}
