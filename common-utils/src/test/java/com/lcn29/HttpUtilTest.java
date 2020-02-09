package com.lcn29;

import com.lcn29.http.HttpUtil;
import com.lcn29.http.request.RequestInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *  Test {@link com.lcn29.http.HttpUtil}
 * </pre>
 *
 * @author LCN
 * @date 2020-02-08 11:25
 */
public class HttpUtilTest {

    public static final String BAIDU_TEST_URL = "http://www.baidu.com/s";

    @Test
    public void getTest() {

        try {
            Map<String, String> param = new HashMap<>();
            param.put("wd","weather");
            param.put("ie","utf-8");

            RequestInfo info = new RequestInfo().url(BAIDU_TEST_URL);
            //info.params(param);

            String response = HttpUtil.get(info);
            System.out.println(response);
        } catch (IOException e) {
            System.out.println("Get Request occurs exception");
            e.printStackTrace();
        }
    }

    @Test
    public void postTest() {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("wd","疫情");
            param.put("ie","utf-8");

            RequestInfo info = new RequestInfo().url(BAIDU_TEST_URL);
                //info.params(param);
            String response = HttpUtil.post(info);
            System.out.println(response);
        } catch (IOException e) {
            System.out.println("Get Request occurs exception");
            e.printStackTrace();
        }
    }

    @Test
    public void putTest() {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("wd","疫情");
            param.put("ie","utf-8");

            String url = "http://10.144.139.98:8888/common/settings/chargeUnits";
            RequestInfo info = new RequestInfo().url(url).params(param);
            String response = HttpUtil.put(info);
            System.out.println(response);
        } catch (IOException e) {
            System.out.println("Get Request occurs exception");
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTest() {
        try {
            Map<String, String> param = new HashMap<>();
            param.put("wd","疫情");
            param.put("ie","utf-8");

            String url = "http://10.144.139.98:8888/common/settings/chargeUnits";
            RequestInfo info = new RequestInfo().url(url).params(param);
            String response = HttpUtil.delete(info);
            System.out.println(response);
        } catch (IOException e) {
            System.out.println("Get Request occurs exception");
            e.printStackTrace();
        }
    }

    @Test
    void file() throws IOException {
    }
}
