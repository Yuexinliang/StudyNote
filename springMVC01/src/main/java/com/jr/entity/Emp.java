package com.jr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * className Emp
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
    private Integer deptno;
}
