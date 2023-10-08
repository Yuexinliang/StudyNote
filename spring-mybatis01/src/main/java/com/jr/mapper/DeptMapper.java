package com.jr.mapper;

import com.jr.entity.Dept;

import java.util.List;

/**
 * className DeptMapper
 * packageName com.jr.mapper
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/06 10:15
 */
public interface DeptMapper {
    int insertDept(Dept dept);
    int deleteDept(int deptno);
    int updateDept(Dept dept);
    List<Dept> selectAll();

}
