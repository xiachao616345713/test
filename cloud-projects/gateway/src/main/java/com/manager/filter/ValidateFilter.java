package com.manager.filter;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * QueryParamPortPreFilter
 *
 * @author chao
 * @since 2018-04-12
 */
@Component
public class ValidateFilter extends ZuulFilter {

    private static final String HEADER_AUTH = "Authentication";
    private static final String HEADER_SIGN = "Signature";

    public int filterOrder() {
        return 1;
    }

    public String filterType() {
        return "pre";
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = getCurrentContext();
        return ctx.getRequest().getParameter("port") != null;
    }

    public Object run() {
        RequestContext ctx = getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // put the serviceId in `RequestContext`
        String port = request.getParameter("port");
        try {
            URL url = UriComponentsBuilder.fromUri(ctx.getRouteHost().toURI())
                    .port(new Integer(port))
                    .build().toUri().toURL();
            ctx.setRouteHost(url);
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}
