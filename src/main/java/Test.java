import com.sun.deploy.net.URLEncoder;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : Rison 2021/7/21 下午7:44
 */
public class Test {
    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String ENCODING = "UTF-8";
    public static void main(String[] args) throws UnsupportedEncodingException {
        final String HTTP_METHOD = "GET";
        Map parameters = new HashMap();
        // 输入公共请求参数
        parameters.put("Version", "20200430");
        parameters.put("AccessKeyId", "7847afe120411836");
        parameters.put("Timestamp", formatIso8601Date(new Date()));
        parameters.put("SignatureMethod", "HMAC-SHA1");
        parameters.put("SignatureVersion", "1.0");
        parameters.put("SignatureNonce", UUID.randomUUID().toString());
        parameters.put("Format", "json");
        // 输入接口独有参数(以queryClientViews该接口为例)
        parameters.put("clientName", "机器人名称");
        // 排序请求参数
        String[] sortedKeys = (String[]) parameters.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);
        final String SEPARATOR = "&";
        // 构造 stringToSign 字符串
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(HTTP_METHOD).append(SEPARATOR);
        stringToSign.append(percentEncode("/")).append(SEPARATOR);
        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (String key : sortedKeys) {
            // 这里注意编码 key 和 value
            canonicalizedQueryString.append("&")
                    .append(percentEncode(key)).append("=")
                    .append(percentEncode((String) parameters.get(key)));
        }
        // 这里注意编码 canonicalizedQueryString
        stringToSign.append(percentEncode(
                canonicalizedQueryString.toString().substring(1)));
    }

    private static String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }
    private static String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, ENCODING).replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
    }
}
