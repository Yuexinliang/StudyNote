package com.jr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * className Dept
 * packageName com.jr.entity
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/06 10:12
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dept {
    private Integer deptno;
    private String dname;
    private String loc;
}
