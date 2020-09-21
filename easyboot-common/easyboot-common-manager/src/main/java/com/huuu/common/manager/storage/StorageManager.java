package com.huuu.common.manager.storage;

/**
 * @author chenzhenhu
 */
public interface StorageManager {

    String uploadToken();

    String uploadToken(String key);

    boolean remove(String key);
}
