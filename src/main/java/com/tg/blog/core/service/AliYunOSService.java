package com.tg.blog.core.service;

import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public interface AliYunOSService {
    /**
     * 获取读写权限
     * @param dir
     * @return
     * @throws UnsupportedEncodingException
     */
    JSONObject getWritePolicy(String dir) throws UnsupportedEncodingException;

    /**
     * 是否存在文件或者文件夹对象
     * @param dir
     * @return
     */
    boolean isExistObject(String dir);

    /**
     * 文件上传
     * @param objectName 文件路径/文件名名
     * @param stream 文件流
     */
    public void uploadFile(String objectName, InputStream stream);

}
