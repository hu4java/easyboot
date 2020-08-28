package com.hu4java.user.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.user.entity.Menu;
import com.hu4java.user.mapper.MenuMapper;
import com.hu4java.user.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hu4java
 */
@Service
public class MenuServiceImpl extends AbstractServiceImpl<Menu, MenuMapper> implements MenuService {

    @Override
    public List<Menu> listByPid(Long pid) {
        return mapper.selectByPid(pid);
    }
}
