package com.manager.common.provider;

import com.manager.common.dto.result.Result;
import com.manager.common.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * dictionary provider
 *
 * @author chao
 * @since 2018-04-11
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryProvider {

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/item")
    public Result selectDictionary(@RequestParam int id) {
        return Result.newSuccessResult(dictionaryService.selectDictionaryById(id));
    }

    @GetMapping("/itemList")
    public Result selectDictionaryList(@RequestParam int pageNum, @RequestParam int pageSize) {
        return Result.newSuccessResult(dictionaryService.selectDictionaryList(pageNum, pageSize));
    }
}
