package com.manager.common.provider;

import com.manager.common.dto.result.Result;
import com.manager.common.service.RegionService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand
    @GetMapping("/item")
    public Result selectRegion(@RequestParam int id) {
        return Result.newSuccessResult(regionService.selectRegionByid(id));
    }

    @HystrixCommand
    @GetMapping("/itemList")
    public Result selectRegionList(@RequestParam int pageNum, @RequestParam int pageSize) {
        return Result.newSuccessResult(regionService.selectRegionList(pageNum, pageSize));
    }
}
