package com.huuu.web.system.response;

import com.huuu.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class DeptTreeTableResponse extends BaseResponse {
    private static final long serialVersionUID = 5259717383495963825L;

    private Long id;
    /** 部门名称*/
    private String name;
    /** 部门编码*/
    private String code;
    /** 联系电话*/
    private String phone;
    /** 上级部门id*/
    private Long pid;
    /** 状态：0-正常 1-禁用*/
    private Integer status;
    /** 排序*/
    private Integer orderNum;
    /** 下级部门*/
    private List<DeptTreeTableResponse> children;

    public List<DeptTreeTableResponse> getChildren() {
        if (CollectionUtils.isEmpty(children)) {
            return null;
        }
        return children;
    }
}
