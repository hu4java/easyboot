package com.hu4java.system.mapper;

import com.hu4java.base.mapper.BaseMapper;
import com.hu4java.system.condition.DeptCondition;
import com.hu4java.system.entity.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门 Mapper
 * @author hu4java
 */
@Repository
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 根据上级id查询
     * @param pid   上级id
     * @return      部门列表
     */
    List<Dept> selectByPid(Long pid);

    /**
     * 根据用户id查询
      * @param userId   用户id
     * @return          部门列表
     */
    List<Dept> selectByUserId(Long userId);

    /**
     * 根据条件查询
     * @param condition 条件
     * @return
     */
    List<Dept> selectByCondition(@Param("condition")DeptCondition condition);
}
