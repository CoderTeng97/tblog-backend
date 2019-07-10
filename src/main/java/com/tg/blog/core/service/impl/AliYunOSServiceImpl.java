package com.tg.blog.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.tg.blog.core.service.AliYunOSService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class AliYunOSServiceImpl implements AliYunOSService{

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.endPoint}")
    private String endPoint;
    @Value("${aliyun.oss.bucket}")
    private String bucket;


    @Override
    public JSONObject getWritePolicy(String dir) throws UnsupportedEncodingException {
        String host = "https://" + bucket + "." + endPoint; // host的格式为 bucketname.endpoint
        // callbackUrl为上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        //String callbackUrl = "http://88.88.88.88:8888";

        OSS client = new OSSClient( endPoint, accessKeyId,  accessKeySecret);
        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);

        JSONObject respMap = new JSONObject();
        respMap.put("OSSAccessKeyId", accessKeyId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("success_action_status", 200);
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));
        return respMap;
    }

    @Override
    public boolean isExistObject(String dir) {
        OSS ossClient = new OSSClient( endPoint, accessKeyId,  accessKeySecret);
       // 判断文件是否存在。doesObjectExist还有一个参数isOnlyInOSS，如果为true则忽略302重定向或镜像；如果
        //为false，则考虑302重定向或镜像。
        boolean found = ossClient.doesObjectExist(bucket, dir);
        ossClient.shutdown();
        return found;
    }
}
