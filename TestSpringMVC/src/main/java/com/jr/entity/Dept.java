package com.jr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className Dept
 * packageName com.jr.entity
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/08 11:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer deptno;
    private String dname;
    private String loc;
}
