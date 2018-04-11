package com.manager.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manager.common.mapper.RegionMapper;
import com.manager.common.model.Region;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * region service
 *
 * @author chao
 * @since 2018-04-10
 */
@Service
public class RegionService {

    @Autowired
    private RegionMapper regionMapper;

    /**
     * select region detail by id
     * @param id primary key
     * @return region info
     */
    public Region selectRegionByid(int id) {
        return  regionMapper.selectByPrimaryKey(id);
    }

    /**
     * select region list
     *
     * @param pageNum   page number
     * @param pageSize  page size
     * @return  region list
     */
    public PageInfo<Region> selectRegionList(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Region> list = regionMapper.selectAll();
        return new PageInfo<>(list);
    }
}
