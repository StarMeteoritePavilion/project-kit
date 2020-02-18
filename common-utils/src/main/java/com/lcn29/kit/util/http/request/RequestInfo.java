package com.lcn29.kit.util.http.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2020-02-08 16:20
 */
public class RequestInfo {

    /**
     * the tag for the request call
     */
    protected Object tag;

    /**
     * the request url
     */
    protected String url;

    /**
     * the request param
     */
    protected Map<String, String> params;

    /**
     * request http header
     */
    protected Map<String, String> headers;

    public RequestInfo tag(String tag) {
        this.tag = tag;
        return this;
    }

    public RequestInfo url(String url) {
        this.url = url;
        return this;
    }

    public RequestInfo params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public RequestInfo addParam(String key, String value) {
        if (Objects.isNull(params)) {
            params = new HashMap<>(8);
        }
        params.put(key,value);
        return this;
    }

    public RequestInfo headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public RequestInfo addHeader(String key, String value) {
        if (Objects.isNull(headers)) {
            headers = new HashMap<>(8);
        }
        headers.put(key,value);
        return this;
    }

    public Object getTag() {
        return tag;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
