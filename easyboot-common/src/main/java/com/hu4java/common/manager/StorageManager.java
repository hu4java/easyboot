package com.hu4java.common.manager;

/**
 * @author chenzhenhu
 */
public interface StorageManager {

    String uploadToken();

    String uploadToken(String key);

    boolean remove(String key);
}
