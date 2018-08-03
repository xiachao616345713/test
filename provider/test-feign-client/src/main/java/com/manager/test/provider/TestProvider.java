package com.manager.test.provider;

import com.manager.client.common.CommonClient;
import com.manager.common.dto.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * test feign provider
 *
 * @author chao
 * @since 2018-08-01
 */
@RestController
@RequestMapping("/test")
public class TestProvider {

    @Autowired
    private CommonClient commonClient;

    @GetMapping("/item")
    public Result selectDictionary(@RequestParam int id) {
        System.out.println("=====================test============");
        return commonClient.selectDictionary(id);
    }

    @GetMapping("/itemList")
    public Result selectDictionaryList(@RequestParam int pageNum, @RequestParam int pageSize) {
        System.out.println("=====================test============");
        return commonClient.selectDictionaryList(pageNum, pageSize);
    }
}
