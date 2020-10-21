package com.huuu.web.system.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class RegionUpdateRequest extends RegionFormRequest {

    @NotNull(message = "ID不能为空")
    private Long id;
}
