package com.huuu.web.system.response;

import com.huuu.base.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author chenzhenhu
 */
@Getter
@Setter
public class RegionSelectResponse extends BaseResponse {
    private static final long serialVersionUID = 2621499685921905227L;


    private Long id;

    private Long pid;

    private String name;

    private String firstLetter;

    private List<RegionSelectResponse> children;
}
