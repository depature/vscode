package com.sf.controller;

import com.sf.Application;
import com.sf.constant.StatusCode;
import com.sf.domain.dto.AppDto;
import com.sf.domain.entity.Auth;
import com.sf.domain.entity.Role;
import com.sf.domain.entity.State;
import com.sf.domain.vo.AppVo;
import com.sf.result.Result;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**.
 * 业务应用相关控制器
 */
@RestController
@RequestMapping("app")
public class ApplicationController {

  /**.
   * 创建应用
   *
   * @param appDto 应用DTO
   * @return 应用结果
   */
  @PostMapping("create")
  public Result<AppVo> createApplication(@RequestBody AppDto appDto) {
    List<State> states = appDto.getStates();
    String beginState = appDto.getBeginState();
    String endState = appDto.getEndState();
    //开始或者结束状态不在列表中
    if (!isStateInList(beginState, states) || !isStateInList(endState, states)) {
      return Result.error(StatusCode.START_OR_END_STATE_NOT_IN_LIST);
    }
    //至少有三个状态
    if (appDto.getStates().size() < 3) {
      return Result.error(StatusCode.INSUFFICIENT_STATE_COUNT);
    }
    //
    List<Role> roles = appDto.getRoles();
    for (Role role : roles) {
      for (Auth auth : role.getAuth()) {
        if (auth.getFromState().equals(endState) || auth.getToState().equals(beginState)) {
          return Result.error(StatusCode.INVALID_USER_ROLE_PERMISSION);
        }
      }
    }

    Application application = new Application();
    application.setName(appDto.getName());
    application.setDesc(appDto.getDesc());
    application.setStates(states);
    application.setRoles(roles);
    application.setBeginState(beginState);
    application.setEndState(endState);

    Application.appMap.put(application.getAppId(), application);

    AppVo appVo = new AppVo();
    appVo.setId(application.getAppId());

    System.out.println("应用 " + application.getAppId());

    return Result.success(appVo, "流程创建成功");
  }

  /**.
   * 检查状态是否在列表中
   *
   * @param stateCode 状态代码
   * @param states    状态列表
   * @return 状态是否在列表中
   */
  private boolean isStateInList(String stateCode, List<State> states) {
    for (State state : states) {
      if (state.getCode().equals(stateCode)) {
        return true;
      }
    }
    return false;
  }
}
