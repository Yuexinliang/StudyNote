package com.jr.service.impl;

import com.jr.entity.Emp;
import com.jr.mapper.EmpMapper;
import com.jr.pojo.EmpDept;
import com.jr.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * className EmpServiceImpl
 * packageName com.jr.service.impl
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/06 13:02
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public boolean addEmp(Emp emp) {
        return empMapper.insertEmp(emp) == 1;
    }

    @Override
    public EmpDept getByEmpno(int empno) {
        return empMapper.selectByEmpno(empno);
    }
}
