package com.manager.common.service;

import com.manager.common.mapper.RegionMapper;
import com.manager.common.model.Region;
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
    public Region selectByPrimaryKey(int id) {
        return  regionMapper.selectByPrimaryKey(id);
    }
}
