package com.sf;

import com.sf.domain.entity.Workflow;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**.
 * 流程信息存储
 */
public class WorkflowList {
  public static Map<Integer, Workflow> workflowData = new HashMap<>();
  public static Set<Integer> processing = new HashSet<>();
}
