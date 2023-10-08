package com.jr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * className EmpController
 * packageName com.jr.entity
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/07 21:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;
    private Boolean isLive;
    /*包装类型*/
    private Dept dept;
    /*数组*/
    private String[] likes;
    /*list集合*/
    private List<Dept> depts;
    /*map集合*/
    private Map<Integer,Dept> deptMaps;
}
