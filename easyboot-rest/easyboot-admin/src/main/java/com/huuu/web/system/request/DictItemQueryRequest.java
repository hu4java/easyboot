package com.huuu.web.system.request;

import com.huuu.base.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author huuu
 */
@Getter
@Setter
public class DictItemQueryRequest extends BaseRequest {
    private static final long serialVersionUID = -4818406501697943991L;

    /** 字典类型*/
    @NotBlank(message = "字典类型不能为空")
    private String dictType;
}
