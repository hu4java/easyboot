package com.hu4java.web.system.request;

import com.hu4java.base.request.PageRequest;
import com.hu4java.system.entity.OperationLog;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hu4java
 */
@Getter
@Setter
public class OperationLogTableRequest extends PageRequest<OperationLog> {
    private static final long serialVersionUID = 1519626068378222575L;


}
