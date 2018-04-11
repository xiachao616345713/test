package com.manager.common.provider;

import com.manager.common.dto.result.Result;
import com.manager.common.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * region provider
 *
 * @author chao
 * @since 2018-04-10
 */
@RestController
@RequestMapping("/region")
public class RegionProvider {

    @Autowired
    private RegionService regionService;

    @GetMapping("/item")
    public Result selectRegion(@RequestParam int id){
        return Result.newSuccessResult(regionService.selectByPrimaryKey(id));
    }
}
