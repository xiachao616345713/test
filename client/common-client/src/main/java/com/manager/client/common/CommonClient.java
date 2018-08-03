package com.manager.client.common;

import com.manager.common.dto.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * common
 *
 * @author chao
 * @since 2018-07-25
 */
@FeignClient(value = "common-provider")
@RequestMapping("/dictionary")
public interface CommonClient {

    /**
     * <p>@RequestParam annotation name attribute is necessary</p>
     *
     * <p>https://stackoverflow.com/questions/44313482/fiegn-client-with-spring-boot-requestparam-value-was-empty-on-parameter-0</p>
     *
     * <p>Both Spring MVC and Spring cloud feign are using same ParameterNameDiscoverer - named DefaultParameterNameDiscoverer to find
     * parameter name. It tries to find the parameter names with the following step.
     *
     * First, it uses StandardReflectionParameterNameDiscoverer. It tries to find the variable name with reflection. It is only possible
     * when your classes are compiled with -parameter.
     *
     * Second, if it fails, it uses LocalVariableTableParameterNameDiscoverer. It tries to find the variable name from the debugging info in
     * the class file with ASM libraries.
     *
     * The difference between Spring MVC and Feign occurs here. Feign uses above annotations (like @RequestParam) on methods of Java
     * interfaces. But, we use these on methods of Java classes when using Spring MVC. Unfortunately, javac compiler omits the debug
     * information of parameter name from class file for java interfaces. That's why feign fails to find parameter name without -parameter.
     *
     * Namely, if you compile your code with -parameter, both Spring MVC and Feign will succeed to acquire parameter names. But if you
     * compile without -parameter, only Spring MVC will succeed.
     *
     * As a result, it's not a bug. it's a limitation of Feign at this moment as I think.</p>
     */
    @GetMapping("/item")
    Result selectDictionary(@RequestParam(name = "id") int id);

    @GetMapping("/itemList")
    Result selectDictionaryList(@RequestParam(name = "pageNum") int pageNum, @RequestParam(name = "pageSize") int pageSize);
}
