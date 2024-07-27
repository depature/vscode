package org.sfx.constant;

import java.util.HashMap;
import java.util.Map;

public class StatusCode {
    public static final int SUCCESS = 20000;
    public static final int INVALID_START_OR_END_STATE = 50010;
    public static final int INSUFFICIENT_STATE_COUNT = 50011;
    public static final int START_OR_END_STATE_NOT_IN_LIST = 50012;
    public static final int INVALID_USER_ROLE_PERMISSION = 50013;
    public static final int INVALID_STATE_TRANSITION = 50020;
    public static final int MISSING_REQUIRED_STATE = 50021;
    public static final int INVALID_STATE_NAME = 50022;
    public static final int CYCLIC_STATE_DEPENDENCY = 50023;
    public static final int STATE_RELATION_WITHOUT_CONDITION = 50024;
    public static final int APP_ID_NOT_FOUND = 50031;
    public static final int WORKFLOW_ID_NOT_FOUND = 50032;
    public static final int WORKFLOW_IN_PROGRESS = 50033;
    public static final int ILLEGAL_STATE_TRANSITION = 50040;
    public static final int INVALID_INPUT_PARAMETER = 50050;
    public static final int SYSTEM_ERROR = 50502;

    public static final Map<Integer, String> MESSAGES = new HashMap<>();

    static {
        MESSAGES.put(SUCCESS, "操作成功");
        MESSAGES.put(INVALID_START_OR_END_STATE, "开始状态或结束状态不合理");
        MESSAGES.put(INSUFFICIENT_STATE_COUNT, "状态总数不能少于 3 个");
        MESSAGES.put(START_OR_END_STATE_NOT_IN_LIST, "开始状态或结束状态不在状态列表中");
        MESSAGES.put(INVALID_USER_ROLE_PERMISSION, "用户角色权限不合理");
        MESSAGES.put(INVALID_STATE_TRANSITION, "状态转换不合理");
        MESSAGES.put(MISSING_REQUIRED_STATE, "缺少必要状态");
        MESSAGES.put(INVALID_STATE_NAME, "非法的状态名");
        MESSAGES.put(CYCLIC_STATE_DEPENDENCY, "存在循环依赖的状态流转关系");
        MESSAGES.put(STATE_RELATION_WITHOUT_CONDITION, "存在没有触发条件的状态关系");
        MESSAGES.put(APP_ID_NOT_FOUND, "appId 不存在");
        MESSAGES.put(WORKFLOW_ID_NOT_FOUND, "流程 ID 不存在");
        MESSAGES.put(WORKFLOW_IN_PROGRESS, "流程正在进行中");
        MESSAGES.put(ILLEGAL_STATE_TRANSITION, "非法的状态流转");
        MESSAGES.put(INVALID_INPUT_PARAMETER, "输入参数错误");
        MESSAGES.put(SYSTEM_ERROR, "系统异常，请稍后重试");
    }

    public static String getMessage(int code) {
        return MESSAGES.getOrDefault(code, "未知错误");
    }
}
