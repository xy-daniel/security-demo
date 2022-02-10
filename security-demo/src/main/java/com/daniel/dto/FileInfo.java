package com.daniel.dto;

/**
 * 文件信息返回值
 *
 * @author daniel
 */
public class FileInfo {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileInfo(String path) {
        this.path = path;
    }
}
