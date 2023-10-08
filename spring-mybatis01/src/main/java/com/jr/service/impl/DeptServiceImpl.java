package com.jr.service.impl;

import com.jr.entity.Dept;
import com.jr.mapper.DeptMapper;
import com.jr.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * className DeptServiceImpl
 * packageName com.jr.service
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/06 10:28
 */
@Service
/*添加事务传播策略的注解
* 设置当前业务类下， 所有业务方法,都遵守REQUIRED 传播策略；
REQUIRED ：有事务就加入，没事务就开启*/
@Transactional (propagation= Propagation.REQUIRED)
public class DeptServiceImpl implements DeptService {
    @Autowired/*byType类型注入*/
    private DeptMapper deptMapper;
    @Override
    public boolean addDept(Dept dept) {
        return deptMapper.insertDept(dept) == 1;
    }

    @Override
    public boolean upDept(Dept dept) {
        int i = 0 ;
        i = deptMapper.insertDept(dept);
        dept.setDname(null);
        i += deptMapper.updateDept(dept);
        if(i == 2){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean removeDept(int deptno) {
        return deptMapper.deleteDept(deptno) == 1;
    }

    @Override
    public List<Dept> getAll() {
        return deptMapper.selectAll();
    }
}
