package com.huuu.web.system.response;

import com.huuu.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class OperateLogTableResponse extends BaseResponse  {
    private static final long serialVersionUID = 4339846205371254473L;
    /** 日志ID*/
    private Long id;
    /** 操作时间*/
    private LocalDateTime operateTime;
    /** 操作人ID*/
    private Long operateUserId;
    /** 操作人姓名*/
    private String operateUser;

    /** 业务类型*/
    private Integer type;

    /** 描述*/
    private String description;

    /** IP*/
    private String ip;


    /** 状态: 0-成功 1-异常*/
    private Integer status;

    /** 位置*/
    private String location;

    /** 请求方法*/
    private String requestMethod;

}
