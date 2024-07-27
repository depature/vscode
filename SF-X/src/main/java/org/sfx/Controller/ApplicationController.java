package org.sfx.Controller;
import org.sfx.Application;
import org.sfx.domain.DTO.AppDTO;
import org.sfx.domain.VO.AppVO;
import org.sfx.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app")
public class ApplicationController {
    @PostMapping("create")
    public Result<AppVO> CreateApplication(@RequestBody AppDTO appDTO){
        Application application = new Application();
        application.setName(appDTO.getName());
        application.setDesc(appDTO.getDesc());
        application.setStates(appDTO.getStates());
        application.setRoles(appDTO.getRoles());
        application.setBeginState(appDTO.getBeginState());
        application.setEndState(appDTO.getEndState());

        AppVO appVO=new AppVO();
        appVO.setId(application.getAppId());
        return Result.success(appVO,"流程创建成功");
    }
}
