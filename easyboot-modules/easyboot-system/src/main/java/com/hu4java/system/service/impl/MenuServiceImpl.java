package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.entity.Menu;
import com.hu4java.system.mapper.MenuMapper;
import com.hu4java.system.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author hu4java
 */
@Service
public class MenuServiceImpl extends AbstractServiceImpl<Menu, MenuMapper> implements MenuService {

    @Override
    public boolean save(Menu entity) {
        if (Objects.equals(entity.getPid(), 0)) {
            entity.setPids("0");
        } else {
            Menu parent = mapper.selectById(entity.getPid());
            entity.setPids(parent.getPids() + "," + parent.getId());
        }
        return returnBool(mapper.insert(entity));
    }

    @Override
    public boolean update(Menu entity) {
        Menu dbItem = mapper.selectById(entity.getId());
        if (!Objects.equals(entity.getPid(), 0) && !Objects.equals(dbItem.getPid(), entity.getPid())) {
            Menu parent = mapper.selectById(entity.getPid());
            entity.setPids(parent.getPids() + "," + parent.getId());
        }
        return returnBool(mapper.updateById(entity));
    }

    @Override
    public List<Menu> listByPid(Long pid) {
        return mapper.selectByPid(pid);
    }

    @Override
    public List<Menu> listTreeByPid(Long pid) {
        return mapper.selectTreeByPid(pid);
    }
}
