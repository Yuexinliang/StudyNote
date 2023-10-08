package com.jr.service;

import com.jr.entity.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * className DeptService
 * packageName com.jr.service
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/06 10:26
 */
public interface DeptService {
    boolean addDept(Dept dept);
    boolean upDept(Dept dept);
    boolean removeDept(int deptno);
    List<Dept> getAll();

}
