package org.sfx.Controller;

import org.sfx.WorkflowList;
import org.sfx.constant.StatusCode;
import org.sfx.domain.DTO.AppDTO;
import org.sfx.domain.DTO.WorkflowActionDTO;
import org.sfx.domain.DTO.WorkflowDTO;
import org.sfx.domain.DTO.WorkflowQueryDTO;
import org.sfx.domain.VO.AppVO;
import org.sfx.domain.VO.WorkflowPageVO;
import org.sfx.domain.VO.WorkflowVO;
import org.sfx.domain.entity.Content;
import org.sfx.domain.entity.Workflow;
import org.sfx.result.Result;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("workflow")
public class WorkFlowController {
    static Integer workflowId=0;
    @PostMapping("create")
    public Result<WorkflowVO> CreateWorkflow(@RequestBody WorkflowDTO workflowDTO){
        Workflow workflow = new Workflow();
        workflow.setId(workflowId++);
        workflow.setName(workflowDTO.getName());
        workflow.setDesc(workflowDTO.getDesc());
        workflow.setCreator("");
        workflow.setCreatedTime(LocalDateTime.now());
        WorkflowList.workflowData.put(workflow.getId(),workflow);
        WorkflowVO workflowVO = new WorkflowVO();
        workflowVO.setId(workflow.getId());
        return Result.success(workflowVO,"流程创建成功");
    }
    @PostMapping("query")
    public Result<WorkflowPageVO> WorkflowPage(@RequestBody WorkflowQueryDTO workflowQueryDTO){
        if(workflowQueryDTO.getPageSize()==null||workflowQueryDTO.getPage()==null||workflowQueryDTO.getPageSize()==0||workflowQueryDTO.getPage()==0){
            return Result.error(StatusCode.INVALID_INPUT_PARAMETER,StatusCode.getMessage(StatusCode.INVALID_INPUT_PARAMETER));
        }
        WorkflowPageVO workflowPageVO = WorkflowPageVO.builder()
                .page(workflowQueryDTO.getPage())
                .pageSize(workflowQueryDTO.getPageSize())
                .build();
        List<Workflow> workflowList = new ArrayList<>(WorkflowList.workflowData.values());
        workflowPageVO.setTotal(workflowList.size());
        workflowPageVO.setTotalPages(workflowList.size()/workflowQueryDTO.getPageSize());
        int fromIndex = (workflowQueryDTO.getPage() - 1) * workflowQueryDTO.getPageSize();
        int toIndex = fromIndex + workflowQueryDTO.getPageSize();
        if(fromIndex<0||toIndex<0){
            return Result.error(StatusCode.INVALID_INPUT_PARAMETER,StatusCode.getMessage(StatusCode.INVALID_INPUT_PARAMETER));
        }
        if (fromIndex >= workflowList.size()) {
             // 起始索引超过总数，返回空列表
            return Result.success(workflowPageVO,"");
        }
        if (toIndex > workflowList.size()) {
            toIndex = workflowList.size(); // 结束索引超过总数，调整为总数
        }
        List<Content> contents=new ArrayList<>();
        List<Workflow> workflows = workflowList.subList(fromIndex, toIndex);
        for(Workflow workflow:workflows){
            Content content = new Content();
            content.setId(workflow.getId());
            content.setName(workflow.getName());
            content.setDesc(workflow.getDesc());
            content.setCreator(workflow.getCreator());
            content.setCreatedTime(workflow.getCreatedTime());
            contents.add(content);
        }
        workflowPageVO.setContent(contents);
        return Result.success(workflowPageVO,"");
    }
    @PostMapping("update")
    public Result<WorkflowVO> WorkflowUpdate(@RequestBody WorkflowDTO workflowDTO){
        int id=workflowDTO.getId();
        if(!WorkflowList.workflowData.containsKey(id)){
            return Result.error(StatusCode.APP_ID_NOT_FOUND,StatusCode.getMessage(StatusCode.APP_ID_NOT_FOUND));
        }
        else{
            Workflow workflow=new Workflow();
            workflow.setId(id);
            workflow.setCreator(WorkflowList.workflowData.get(id).getCreator());
            workflow.setCreatedTime(WorkflowList.workflowData.get(id).getCreatedTime());
            workflow.setName(workflowDTO.getName());
            workflow.setDesc(workflowDTO.getDesc());
            workflow.setEvents(workflowDTO.getEvents());
            workflow.setStates(workflowDTO.getStates());
            WorkflowList.workflowData.put(id,workflow);
        }
        WorkflowVO workflowVO = new WorkflowVO();
        workflowVO.setId(id);
        return Result.success(workflowVO,"流程更新成功");
    }
    @PostMapping("delete")
    public Result<WorkflowVO> WorkflowDelete(@RequestBody WorkflowDTO workflowDTO){
        int id=workflowDTO.getId();
        if(!WorkflowList.workflowData.containsKey(id)){
            return Result.error(StatusCode.APP_ID_NOT_FOUND,StatusCode.getMessage(StatusCode.APP_ID_NOT_FOUND));
        }
        else{
            WorkflowList.workflowData.remove(id);
        }
        WorkflowVO workflowVO = new WorkflowVO();
        workflowVO.setId(id);
        return Result.success(workflowVO,"流程删除成功");
    }
    @PostMapping("action")
    public Result<Map> WorkflowAction(@RequestBody WorkflowActionDTO workflowActionDTO){
        int id=workflowActionDTO.getId();
        if(!WorkflowList.workflowData.containsKey(id)){
            return Result.error(StatusCode.APP_ID_NOT_FOUND,StatusCode.getMessage(StatusCode.APP_ID_NOT_FOUND));
        }
        else{
            Workflow workflow = WorkflowList.workflowData.get(id);
        }
        return Result.success(new HashMap(),"操作成功");
    }
}
