package com.manager.client.common.fallback;

import com.manager.client.common.CommonClient;
import com.manager.common.dto.result.Code;
import com.manager.common.dto.result.Result;
import com.manager.common.exception.BusinessException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author chao
 * @since 2018-08-01
 */
//@Component
public class CommonClientFallbackFactory implements FallbackFactory<CommonClient> {

    @Override
    public CommonClient create(Throwable throwable) {
        return new CommonClient(){

            @Override
            public Result selectDictionary(int id) {
                if (throwable instanceof BusinessException) {
                    return Result.newResult(Code.FAIL.code(), throwable.getMessage());
                } else {
                    return Result.FAIL;
                }
            }

            @Override
            public Result selectDictionaryList(int pageNum, int pageSize) {
                if (throwable instanceof BusinessException) {
                    return Result.newResult(Code.FAIL.code(), throwable.getMessage());
                } else {
                    return Result.FAIL;
                }
            }
        };
    }
}
