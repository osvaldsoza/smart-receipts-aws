package com.github.osvaldsoza.smart.receipts.aws.dto;

/**
 * DTO for Receipt data transfer with file upload support
 */
public class ResponseReceiptDTO {

    private String id;

    private UserDTO user;

    private String description;

    private String originalKey;

    private String processedKey;

    private String fileName;

    private Long fileSize;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalKey() {
        return originalKey;
    }

    public void setOriginalKey(String originalKey) {
        this.originalKey = originalKey;
    }

    public String getProcessedKey() {
        return processedKey;
    }

    public void setProcessedKey(String processedKey) {
        this.processedKey = processedKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
