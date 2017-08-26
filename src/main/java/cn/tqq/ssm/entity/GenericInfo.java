package cn.tqq.ssm.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * User: TianQiangQiang
 * Date: 2017/08/20 15:09
 * Desc: 通用交互类
 */
public class GenericInfo {

    //状态码
    private int statusCode;
    //提示信息
    private String message;
    //交互数据
    private Map<String, Object> data = new HashMap<>();

    public static GenericInfo success() {
        GenericInfo genericInfo = new GenericInfo();
        genericInfo.setStatusCode(1);
        genericInfo.setMessage("处理成功!");
        return genericInfo;
    }

    public static GenericInfo fail() {
        GenericInfo genericInfo = new GenericInfo();
        genericInfo.setStatusCode(-1);
        genericInfo.setMessage("处理失败!");
        return genericInfo;
    }

    public GenericInfo add(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
