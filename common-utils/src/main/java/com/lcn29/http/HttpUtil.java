package com.lcn29.http;

import com.alibaba.fastjson.JSONObject;
import com.lcn29.constant.CommonConstants;
import com.lcn29.http.request.RequestInfo;
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
    public static String get(RequestInfo info) throws IllegalArgumentException, IOException {

        if (Objects.isNull(info) || Objects.isNull(info.getUrl())) {
            throw  new IllegalArgumentException("the request info or the request url is null");
        }
        // make the params join the url
        Optional.ofNullable(info.getParams()).ifPresent(params -> {

            StringBuilder sb = new StringBuilder(info.getUrl()).append(CommonConstants.QUESTION_MARK);
            params.forEach((key, value) -> sb.append(key)
                .append(CommonConstants.EQUAL_SIGN)
                .append(value)
                .append(CommonConstants.QUESTION_MARK));
            info.url(sb.substring(0, sb.length() - 1));
        });

        Request.Builder builder = new Request.Builder();
        Optional.ofNullable(info.getHeaders()).ifPresent(headers -> {
            Headers.Builder headerBuilder = new Headers.Builder();
            headers.forEach(headerBuilder::add);
            builder.headers(headerBuilder.build());
        });

        Request request = builder.url(info.getUrl()).build();
        try (Response response = getHttpClient().newCall(request).execute()) {
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
     * http post request
     * @param info
     * @return
     */
    public static String post(RequestInfo info) throws IllegalArgumentException, IOException{
        if (Objects.isNull(info) || Objects.isNull(info.getUrl())) {
            throw  new IllegalArgumentException("the request info or the request url is null");
        }

        Request.Builder builder = new Request.Builder();
        // make the params join the url
        Optional.ofNullable(info.getParams()).ifPresent(params -> {
            JSONObject jsonObject = new JSONObject();
            params.forEach(jsonObject::put);
            RequestBody body = RequestBody.create(JSONObject.toJSONString(jsonObject), JSON);
            builder.post(body);
        });

        Optional.ofNullable(info.getHeaders()).ifPresent(headers -> {
            Headers.Builder headerBuilder = new Headers.Builder();
            headers.forEach(headerBuilder::add);
            builder.headers(headerBuilder.build());
        });

        Request request = builder.url(info.getUrl()).build();
        try (Response response = getHttpClient().newCall(request).execute()) {
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
}
