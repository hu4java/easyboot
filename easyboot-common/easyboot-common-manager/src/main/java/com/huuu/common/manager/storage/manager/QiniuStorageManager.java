package com.huuu.common.manager.storage.manager;

import com.huuu.common.manager.storage.StorageManager;
import com.huuu.common.manager.storage.StorageProperties;
import com.huuu.util.GsonUtils;
import com.huuu.util.RandomUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


/**
 * 七牛对象存储 Kodo
 * @author chenzhenhu
 */
@Slf4j
public class QiniuStorageManager implements StorageManager {

    private StorageProperties properties;

    private Auth auth;
    private BucketManager bucketManager;
    private UploadManager uploadManager;
    private Map<String, String> returnBodyMap = new HashMap<>(16);

    public QiniuStorageManager(StorageProperties properties) {
        this.properties = properties;
        this.auth = Auth.create(properties.getAk(), properties.getSk());
        returnBodyMap.put("key", "$(key)");
        returnBodyMap.put("hash", "$(etag)");
        returnBodyMap.put("bucket", "$(bucket)");
        returnBodyMap.put("fsize", "$(fsize)");
        returnBodyMap.put("fname", "$(fname)");
        returnBodyMap.put("mimeType", "$(mimeType)");
        returnBodyMap.put("ext", "$(ext)");
        Configuration configuration = new Configuration();
        this.bucketManager = new BucketManager(this.auth, configuration);
        this.uploadManager = new UploadManager(configuration);
    }

    @Override
    public String uploadToken() {
        String key = "common/file/" + RandomUtils.uuid();
        Map<String, String> returnBody = new HashMap<>();
        if (properties.getDomain().endsWith("/")) {
            returnBody.put("url", properties.getDomain() + key);
        } else {
            returnBody.put("url", properties.getDomain() + "/" + key);
        }

        returnBody.putAll(this.returnBodyMap);

        StringMap policy = new StringMap();
        policy.put("returnBody", GsonUtils.toJson(returnBody));
        policy.put("forceSaveKey", true);
        policy.put("saveKey",key);
        return auth.uploadToken(properties.getBucket(), null, properties.getExpireSeconds(), policy);
    }

    @Override
    public String uploadToken(String key) {
        Map<String, String> returnBody = new HashMap<>();
        returnBody.put("url", properties.getDomain() + "/" + key);

        returnBody.putAll(this.returnBodyMap);

        StringMap policy = new StringMap();
        policy.put("returnBody", GsonUtils.toJson(returnBodyMap));
        return auth.uploadToken(properties.getBucket(), key, properties.getExpireSeconds(), policy);
    }

    @Override
    public boolean remove(String key) {
        try {
            bucketManager.delete(properties.getBucket(), key);
            return true;
        } catch (QiniuException e) {
            log.error("七牛云删除文件异常：{}", e.getCause(), e);
        }
        return false;
    }
}
