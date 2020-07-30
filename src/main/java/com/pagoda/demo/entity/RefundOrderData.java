package com.pagoda.demo.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author wlx
 */
@Data
public class RefundOrderData {
    private String refundOrderNo;
    private String customerCode;
    private String orderNo;
    private String deviceNo;
    private String unionId;
    private String mobile;
    private String ip;
    private String refundMoney;
    private String refundTime;
    private String status;
    private List<String> saleModel;

    public static void transfer(Object orderData) throws IllegalAccessException {
        Field[] fields = RefundOrderData.class.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            System.out.println(field.getName()+","+field.get(orderData));
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        RefundOrderData refundOrderData = new RefundOrderData();
        refundOrderData.setCustomerCode("1111");
        String sal = null;
//        refundOrderData.setSaleModel(StringUtils.isEmpty(sal) ? null : Collections.singletonList(sal));
//        System.out.println(JSON.toJSONString(refundOrderData));

        Integer onComplainRefundNum = Integer.valueOf(refundOrderData.getRefundMoney());

        System.out.println(onComplainRefundNum/10);
    }
}
