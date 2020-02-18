package com.lcn29.kit.util.http;

import com.alibaba.fastjson.JSONObject;
import com.lcn29.kit.util.CollectionMapUtil;
import com.lcn29.kit.util.constant.CommonConstants;
import com.lcn29.kit.util.http.request.RequestInfo;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2020-02-08 13:51
 */
public class HttpUtil {

    /**
     * json mediaType
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * singleton
     */
    private volatile static HttpUtil INSTANCE;

    private OkHttpClient httpClient;

    private HttpUtil() {
    }

    private HttpUtil(OkHttpClient.Builder builder) {
        this.httpClient = Objects.isNull(builder) ? new OkHttpClient() : new OkHttpClient(builder);
    }

    /**
     * http get request
     * @param info
     * @return
     * @throws Exception
     */
    public static String get(RequestInfo info) throws IOException {
        return startRequest(info, HttpMethod.GET);
    }

    /**
     * http post request
     * @param info
     * @return
     * @throws Exception
     */
    public static String post(RequestInfo info) throws IOException {
        return startRequest(info, HttpMethod.POST);
    }

    /**
     * http put request
     * @param info
     * @return
     * @throws Exception
     */
    public static String put(RequestInfo info) throws IOException {
        return startRequest(info, HttpMethod.PUT);
    }

    /**
     * http delete request
     * @param info
     * @return
     * @throws Exception
     */
    public static String delete(RequestInfo info) throws IOException {
        return startRequest(info, HttpMethod.DELETE);
    }

    /**
     * http patch request
     * @param info
     * @return
     * @throws Exception
     */
    public static String patch(RequestInfo info) throws IOException {
        return startRequest(info, HttpMethod.PATCH);
    }

    /**
     * really request process
     * @param info
     * @param method
     * @return
     * @throws IOException
     */
    private static String startRequest(RequestInfo info, HttpMethod method) throws IOException {

        if (verifyArgs(info)) {
            throw new IllegalArgumentException("the request info or the request url is null");
        }
        Request.Builder builder = new Request.Builder();
        urlHandler(builder, info, method);
        paramsHandler(builder, info, method);
        headersHandler(builder, info);
        return executeRequest(builder);
    }

    /**
     * verify the request argument
     * @return
     */
    private static boolean verifyArgs(RequestInfo info) {
        return Objects.isNull(info) || Objects.isNull(info.getUrl());
    }

    /**
     * handler the request url
     * @param builder
     * @param info
     * @param httpMethod
     */
    private static void urlHandler(Request.Builder builder, RequestInfo info, HttpMethod httpMethod) {

        if (!HttpMethod.GET.equals(httpMethod) || CollectionMapUtil.isEmpty(info.getParams())) {
            builder.url(info.getUrl());
            return;
        }
        // make the params join the url
        StringBuilder sb = new StringBuilder(info.getUrl())
            .append(CommonConstants.QUESTION_MARK);

        info.getParams().forEach((key, value) -> sb.append(key)
            .append(CommonConstants.EQUAL_SIGN)
            .append(value)
            .append(CommonConstants.QUESTION_MARK));

        builder.url(sb.substring(0, sb.length() - 1));
    }

    /**
     * handler the request params
     * @param builder
     * @param info
     * @param method
     */
    private static void paramsHandler(Request.Builder builder, RequestInfo info, HttpMethod method) {

        if (HttpMethod.GET == method || CollectionMapUtil.isEmpty(info.getParams())) {
            builder.method(method.getMethod(),null);
            return;
        }
        JSONObject jsonObject = new JSONObject();
        info.getParams().forEach(jsonObject::put);
        RequestBody body = RequestBody.create(JSONObject.toJSONString(jsonObject), JSON);
        builder.method(method.getMethod(), body);
    }

    /**
     * handler the request header
     * @param builder
     * @param info
     */
    private static void headersHandler(Request.Builder builder, RequestInfo info) {

        Optional.ofNullable(info.getHeaders()).ifPresent(headers -> {
            Headers.Builder headerBuilder = new Headers.Builder();
            headers.forEach(headerBuilder::add);
            builder.headers(headerBuilder.build());
        });
    }

    /**
     * start request the service
     * @param builder
     * @return
     * @throws IOException
     */
    private static String executeRequest(Request.Builder builder) throws IOException {
        try (Response response = getHttpClient().newCall(builder.build()).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            ResponseBody responseBody = response.body();
            if (Objects.isNull(responseBody)) {
                throw new IOException("the response body no content");
            }
            return responseBody.string();
        }
    }

    /**
     * create and config the OkHttpClient,
     * if the OkHttpClient instance is exist, will use the exist
     *
     * @param builder
     * @return
     */
    public final static void initDefaultConfig(OkHttpClient.Builder builder) {
        getHttpClient(builder);
    }

    /**
     * get the single instance without config
     * @return
     */
    private final static OkHttpClient getHttpClient() {
        return getHttpClient(null);
    }

    /**
     * get the single instance
     * @return
     */
    private final static OkHttpClient getHttpClient(OkHttpClient.Builder builder) {
        if (Objects.isNull(INSTANCE)) {
            synchronized (HttpUtil.class) {
                if (Objects.isNull(INSTANCE)) {
                    INSTANCE = new HttpUtil(builder);
                }
            }
        }
        return INSTANCE.httpClient;
    }

    /**
     * enum for http request method
     */
    private enum HttpMethod {

        /** http get request */
        GET("get"),
        /** http post request */
        POST("post"),
        /** http put request */
        PUT("put"),
        PATCH("patch"),
        /** http delete request */
        DELETE("delete")
        ;

        /**
         * Request Method
         */
        private String method;

        HttpMethod(String method){
            this.method = method;
        }
        public String getMethod() {
            return method;
        }
    }
}
