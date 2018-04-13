package com.manager.filter;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.dto.result.Code;
import com.manager.common.dto.result.Result;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

/**
 * QueryParamPortPreFilter
 *
 * @author chao
 * @since 2018-04-12
 */
@Configuration
@ConfigurationProperties("filter.validate")
@Component
public class ValidateFilter extends ZuulFilter {

    private static final String HEADER_AUTH = "Authentication";
    private static final String HEADER_SIGN = "Signature";

    private List<String> ignorePaths = new ArrayList<>();

    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = getCurrentContext();
        return !ctx.getRequest().getRequestURL().toString().contains("/free/");
    }

    public Object run() {
        RequestContext ctx = getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        /*
         * ignore path filter
         */
        if (!ignorePaths.contains(request.getRequestURI())) {
            String auth = request.getHeader(HEADER_AUTH);
            String sign = request.getHeader(HEADER_SIGN);
            System.out.println("auth:" + auth + ",sign:" + sign);
            if (!"xiachao".equals(auth) || !"123456".equals(sign)) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(200);
                ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                ctx.setResponseBody(Result.newResult(Code.ER_PARAMS).toString());
                return null;
            }
        }

        /*
         * validate signature
         */
        Map<String,Object> map = null;
        if (HttpMethod.POST.toString().equals(request.getMethod())) {
            if (request.getParameterMap() != null && request.getParameterMap().size() > 0) {
                map = new HashMap<>(request.getParameterMap());
            }
            // request body
            try {
                InputStream in = request.getInputStream();
                if (in != null) {
                    JSONObject jsonObject = JSONObject.parseObject(StreamUtils.copyToString(in,Charset.forName("UTF-8")));
                    if (!jsonObject.isEmpty()) {
                        if (map != null) {
                            map.putAll(jsonObject.getInnerMap());
                        } else {
                            map = new HashMap<>(jsonObject.getInnerMap());
                        }
                    }
                }
            } catch (IOException e) {
                rethrowRuntimeException(e);
            }
        } else {
            if (request.getParameterMap() != null && request.getParameterMap().size() > 0) {
                map = new HashMap<>(request.getParameterMap());
            }
        }
        String contentType = request.getContentType();
        // request parameter
        Map<String,String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null) {
            map = new HashMap<>(parameterMap);
        }


        return null;
    }

    public List<String> getIgnorePaths() {
        return ignorePaths;
    }

    public void setIgnorePaths(List<String> ignorePaths) {
        this.ignorePaths = ignorePaths;
    }
}
