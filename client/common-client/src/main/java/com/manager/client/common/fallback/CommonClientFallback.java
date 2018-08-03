package com.manager.client.common.fallback;

import com.manager.client.common.CommonClient;
import com.manager.common.dto.result.Result;
import org.springframework.stereotype.Component;

/**
 * simple default fall back
 *
 * @author chao
 * @since 2018-08-01
 */
//@Component
public class CommonClientFallback implements CommonClient {

    @Override
    public Result selectDictionary(int id) {
        return Result.FAIL;
    }

    @Override
    public Result selectDictionaryList(int pageNum, int pageSize) {
        return Result.FAIL;
    }
}
