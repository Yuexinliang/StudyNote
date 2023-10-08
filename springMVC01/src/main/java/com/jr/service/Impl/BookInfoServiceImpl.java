package com.jr.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * className BookInfoServiceImpl
 * packageName com.jr.service.Impl
 * Description TODO
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/10/07 09:25
 */
@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    private BookInfoMapper bookInfoMapper;
    /**
     * @description: 使用pageHelper进行模糊分页查询
     * @param bookInfo
     * @param pageNum
     * @param pageSize
     * @return: com.github.pagehelper.PageInfo<com.jr.entity.BookInfo>
     * @author CYQH
     * @date: 2023/10/07 9:57
     */
    @Override
    public PageInfo<BookInfo> getByBookInfo(BookInfo bookInfo,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<BookInfo> bookInfos = bookInfoMapper.selectByBookInfo(bookInfo);
        return new PageInfo<BookInfo>(bookInfos);
    }

    /**
     * @description: 返回下拉列表中的图书信息
     * @return: java.util.List<com.jr.entity.BookInfo>
     * @author CYQH
     * @date: 2023/10/07 9:58
     */
    @Override
    public List<BookInfo> selectBookType() {
        return bookInfoMapper.selectBookType();
    }
}
