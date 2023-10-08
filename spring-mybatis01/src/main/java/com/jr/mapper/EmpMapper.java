package com.jr.mapper;

import com.jr.entity.Emp;
import com.jr.pojo.EmpDept;

/**
 * className EmpMapper
 * packageName com.jr.mapper
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/06 12:53
 */
public interface EmpMapper {
    int insertEmp(Emp emp);
    EmpDept selectByEmpno(int empno);
}
