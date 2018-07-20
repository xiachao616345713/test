package com.manager.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * <p>sample filter</p>
 * <p>pre filter -> route filter -> post filter</p>
 *
 * <p>Pre filters set up data in the RequestContext for use in filters downstream.</p>
 * <p>The main use case is to set information required for route filters</p>
 *
 * <p>Route filters run after pre filters and make requests to other services.</p>
 * <p>Much of the work here is to translate request and response data to and from the model required by the client</p>
 *
 * <p>Post filters typically manipulate the response</p>
 *
 * <p>@see http://cloud.spring.io/spring-cloud-static/Edgware.SR4/single/spring-cloud.html#_custom_zuul_filter_examples</p>
 *
 * @author chao
 * @since 2018-04-13
 */
public class SampleFilter {

    public class PreSampleFilter extends ZuulFilter {

        @Override
        public String filterType() {
            return FilterConstants.PRE_TYPE;
        }

        @Override
        public int filterOrder() {
            return FilterConstants.PRE_DECORATION_FILTER_ORDER;
        }

        @Override
        public boolean shouldFilter() {
            return true;
        }

        @Override
        public Object run() {
            return null;
        }
    }

    public class RouteSampleFilter extends ZuulFilter {

        @Autowired
        private ProxyRequestHelper helper;

        @Override
        public String filterType() {
            return FilterConstants.ROUTE_TYPE;
        }

        @Override
        public int filterOrder() {
            return FilterConstants.RIBBON_ROUTING_FILTER_ORDER;
        }

        @Override
        public boolean shouldFilter() {
            return false;
        }

        @Override
        public Object run() {
            return null;
        }
    }

    public class PostSampleFilter extends ZuulFilter {

        @Override
        public String filterType() {
            return FilterConstants.POST_TYPE;
        }

        @Override
        public int filterOrder() {
            return FilterConstants.SEND_RESPONSE_FILTER_ORDER;
        }

        @Override
        public boolean shouldFilter() {
            return false;
        }

        @Override
        public Object run() {
            return null;
        }
    }

    public class ErrorSampleFilter extends ZuulFilter {

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
            return false;
        }

        @Override
        public Object run() {
            return null;
        }
    }

}
