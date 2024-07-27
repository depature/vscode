package com.sf.controller;


import com.sf.Application;
import com.sf.WorkflowList;
import com.sf.constant.StatusCode;
import com.sf.domain.dto.WorkflowActionDto;
import com.sf.domain.dto.WorkflowCreateDto;
import com.sf.domain.dto.WorkflowDto;
import com.sf.domain.dto.WorkflowQueryDto;
import com.sf.domain.entity.Content;
import com.sf.domain.entity.Event;
import com.sf.domain.entity.State;
import com.sf.domain.entity.Workflow;
import com.sf.domain.vo.WorkflowPageVo;
import com.sf.domain.vo.WorkflowVo;
import com.sf.result.Result;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**.
 * 流程控制层
 */
@RestController
@RequestMapping("workflow")
public class WorkFlowController {
  static Integer workflowId = 1;

  /**.
   * 判断是否有环
   */
  public static boolean hasCycle(List<Event> events) {
    Map<String, List<String>> graph = new HashMap<>();
    for (Event event : events) {
      graph.computeIfAbsent(event.getFromState(), k -> new ArrayList<>()).add(event.getToState());
    }

    // 检测环
    Set<String> visited = new HashSet<>();
    Set<String> recursionStack = new HashSet<>();

    for (String node : graph.keySet()) {
      if (dfs(node, graph, visited, recursionStack)) {
        return true;
      }
    }
    return false;
  }

  private static boolean dfs(String node, Map<String, List<String>> graph,
                             Set<String> visited, Set<String> recursionStack) {
    if (recursionStack.contains(node)) {
      return true; // 发现环
    }
    if (visited.contains(node)) {
      return false;
    }

    visited.add(node);
    recursionStack.add(node);

    List<String> neighbors = graph.get(node);
    if (neighbors != null) {
      for (String neighbor : neighbors) {
        if (dfs(neighbor, graph, visited, recursionStack)) {
          return true;
        }
      }
    }

    recursionStack.remove(node);
    return false;
  }

  /**.
   * 创建一个流程
   */
  @PostMapping("create")
  public Result createWorkflow(@RequestBody WorkflowCreateDto workflowDto) {
    Integer appId = workflowDto.getAppId();
    //判断是否有应用
    if (!Application.appMap.containsKey(appId)) {
      return Result.error(StatusCode.APP_ID_NOT_FOUND);
    }
    //判断命名是否合法
    String name = workflowDto.getName();
    if (name.isEmpty()) {
      return Result.error(StatusCode.INVALID_STATE_NAME);
    }
    for (Workflow workflow : WorkflowList.workflowData.values()) {
      if (name.equals(workflow.getName())) {
        return Result.error(StatusCode.INVALID_STATE_NAME);
      }
    }
    //判断是否有环或者判断是否出现孤立状态
    List<Event> events = workflowDto.getEvents();
    List<State> states = workflowDto.getStates();
    Map<String, Integer> statesMap = new HashMap<>();
    for (int i = 0; i < states.size(); i++) {
      statesMap.put(states.get(i).getCode(), i);
    }
    for (Event event : events) {
      if (!statesMap.containsKey(event.getFromState())
              || !statesMap.containsKey(event.getToState())) {
        return Result.error(StatusCode.STATE_RELATION_WITHOUT_CONDITION);
      }
      if (hasCycle(events)) {
        return Result.error(StatusCode.CYCLIC_STATE_DEPENDENCY);
      }
    }

    Workflow workflow = new Workflow();
    workflow.setId(workflowId++);
    workflow.setAppId(appId);
    workflow.setName(name);
    workflow.setDesc(workflowDto.getDesc());
    workflow.setCreator("");
    workflow.setCreatedTime(LocalDateTime.now());
    workflow.setEvents(workflowDto.getEvents());
    workflow.setStates(workflowDto.getStates());
    WorkflowList.workflowData.put(workflow.getId(), workflow);
    WorkflowVo workflowVo = new WorkflowVo();
    workflowVo.setId(workflow.getId());
    return Result.success(workflowVo, "流程创建成功");
  }

  /**.
   * 分页查询流程
   */
  @PostMapping("query")
  public Result<WorkflowPageVo> workflowPage(@RequestBody WorkflowQueryDto workflowQueryDto) {
    Integer pageSize = 20;
    Integer page = 1;
    if (workflowQueryDto.getPage() == null || workflowQueryDto.getPageSize() == null
            || pageSize == 0 || page == 0) {
      pageSize = 20;
      page = 1;
    } else {
      pageSize = workflowQueryDto.getPageSize();
      page = workflowQueryDto.getPage();
    }
    WorkflowPageVo workflowPageVo = WorkflowPageVo.builder()
            .page(page)
            .size(pageSize)
            .build();
    List<Workflow> workflowList = new ArrayList<>(WorkflowList.workflowData.values());
    Collections.sort(workflowList, (w1, w2) ->
            Integer.compare(w2.getId(), w1.getId())
    );
    workflowPageVo.setTotal(workflowList.size());
    workflowPageVo.setTotalPage(workflowList.size() / pageSize + 1);
    int fromIndex = (page - 1) * pageSize;
    int toIndex = fromIndex + pageSize;
    if (page <= 0) {
      fromIndex = 0;
      toIndex = fromIndex + pageSize;
    }
    if (fromIndex >= workflowList.size()) {
      // 起始索引超过总数，返回空列表
      workflowPageVo.setContent(new ArrayList<>());
      return Result.success(workflowPageVo, "");
    }
    if (toIndex > workflowList.size()) {
      // 结束索引超过总数，调整为总数
      toIndex = workflowList.size();
    }
    List<Content> contents = new ArrayList<>();
    List<Workflow> workflows = workflowList.subList(fromIndex, toIndex);

    for (Workflow workflow : workflows) {
      Content content = new Content();
      content.setId(workflow.getId());
      content.setName(workflow.getName());
      content.setDesc(workflow.getDesc());
      content.setCreator(workflow.getCreator());
      content.setCreatedTime(workflow.getCreatedTime());
      contents.add(content);
    }
    workflowPageVo.setContent(contents);
    return Result.success(workflowPageVo, "");
  }

  /**.
   * 更新流程
   */
  @PostMapping("update")
  public Result<WorkflowVo> workflowUpdate(@RequestBody WorkflowDto workflowDto) {
    //判断流程是否存在
    int id = workflowDto.getId();
    if (!WorkflowList.workflowData.containsKey(id)) {
      return Result.error(StatusCode.WORKFLOW_ID_NOT_FOUND);
    }
    //判断命名是否合法
    String name = workflowDto.getName();
    if (name.isEmpty()) {
      return Result.error(StatusCode.INVALID_STATE_NAME);
    }
    //判断流程是否在进行中
    if (WorkflowList.processing.contains(id)) {
      return Result.error(StatusCode.WORKFLOW_IN_PROGRESS);
    }
    for (Workflow workflow : WorkflowList.workflowData.values()) {
      if (name.equals(workflow.getName()) && id != workflow.getId()) {
        return Result.error(StatusCode.INVALID_STATE_NAME);
      }
    }
    Workflow workflow = new Workflow();
    workflow.setId(id);
    workflow.setCreator(WorkflowList.workflowData.get(id).getCreator());
    workflow.setCreatedTime(WorkflowList.workflowData.get(id).getCreatedTime());
    workflow.setName(workflowDto.getName());
    workflow.setDesc(workflowDto.getDesc());
    workflow.setEvents(workflowDto.getEvents());
    workflow.setStates(workflowDto.getStates());
    WorkflowList.workflowData.put(id, workflow);

    WorkflowVo workflowVo = new WorkflowVo();
    workflowVo.setId(id);
    return Result.success(workflowVo, "流程更新成功");
  }

  /**.
   * 删除流程
   */
  @PostMapping("delete")
  public Result<WorkflowVo> workflowDelete(@RequestBody WorkflowDto workflowDto) {
    int id = workflowDto.getId();
    //判断流程是否存在
    if (!WorkflowList.workflowData.containsKey(id)) {
      return Result.error(StatusCode.WORKFLOW_ID_NOT_FOUND);
    }
    //判断流程是否在进行中
    if (WorkflowList.processing.contains(id)) {
      return Result.error(StatusCode.WORKFLOW_IN_PROGRESS);
    }
    WorkflowList.workflowData.remove(id);
    WorkflowVo workflowVo = new WorkflowVo();
    workflowVo.setId(id);
    return Result.success(workflowVo, "流程删除成功");
  }

  /**.
   * 流程运转
   */
  @PostMapping("action")
  public Result<Map> workflowAction(@RequestBody WorkflowActionDto workflowActionDto) {
    //判断流程是否存在
    int id = workflowActionDto.getId();
    if (!WorkflowList.workflowData.containsKey(id)) {
      return Result.error(StatusCode.WORKFLOW_ID_NOT_FOUND);
    }
    Workflow workflow = WorkflowList.workflowData.get(id);
    List<Event> events = workflow.getEvents();
    for (Event event : events) {
      if (event.getRole().equals(workflowActionDto.getRole())
              && event.getName().equals(workflowActionDto.getAction())) {
        WorkflowList.processing.add(id);
        return Result.success(new HashMap(), "操作成功");
      }
    }
    return Result.error(StatusCode.ILLEGAL_STATE_TRANSITION);
  }

}
