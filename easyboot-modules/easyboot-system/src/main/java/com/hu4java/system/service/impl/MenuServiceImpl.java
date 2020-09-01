package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.entity.Menu;
import com.hu4java.system.mapper.MenuMapper;
import com.hu4java.system.service.MenuService;
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

    @Override
    public List<Menu> listTreeByPid(Long pid) {
        return mapper.selectTreeByPid(pid);
    }
}
