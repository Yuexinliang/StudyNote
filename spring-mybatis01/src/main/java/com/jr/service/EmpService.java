package com.jr.service;

import com.jr.entity.Dept;
import com.jr.entity.Emp;
import com.jr.pojo.EmpDept;

/**
 * className EmpService
 * packageName com.jr.service
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/06 13:01
 */
public interface EmpService {
    boolean addEmp(Emp emp);
    EmpDept getByEmpno(int empno);
}
