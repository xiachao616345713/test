package com.manager.filter;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import com.manager.common.dto.result.Result;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * error filter
 *
 * @author chao
 * @since 2018-04-13
 */
@Slf4j
@Component
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = getCurrentContext();
        log.error(ctx.getThrowable().getMessage());

        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(200);
        ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ctx.setResponseBody(Result.FAIL.toString());
        return null;
    }
}
