package com.manager.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manager.common.mapper.DictionaryMapper;
import com.manager.common.model.Dictionary;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * dictionary service
 *
 * @author chao
 * @since 2018-04-11
 */
@Service
public class DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    /**
     * select dictionary info by primary key
     *
     * @param id primary key
     * @return dictionary
     */
    public Dictionary selectDictionaryById(int id) {
        return dictionaryMapper.selectByPrimaryKey(id);
    }

    /**
     * select dictionary list
     *
     * @param pageNum page number
     * @param pageSize page size
     * @return dictionary page info
     */
    public PageInfo<Dictionary> selectDictionaryList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dictionary> list = dictionaryMapper.selectAll();
        return new PageInfo<>(list);
    }
}
