package com.baeldung.demo.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Map;

@Document(indexName = "resumes")
public class Resumes {


    private  String id;
    private String content;
    private Map<String, Object> meta;
    private Map<String, Object> file;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public Map<String, Object> getFile() {
        return file;
    }

    public void setFile(Map<String, Object> file) {
        this.file = file;
    }

    public static class ResumeDetails {
        private String fileTitle;
        private String nameCandidate;

        public String getFileTitle() {
            return fileTitle;
        }

        public void setFileTitle(String fileTitle) {
            this.fileTitle = fileTitle;
        }

        public String getNameCandidate() {
            return nameCandidate;
        }

        public void setNameCandidate(String nameCandidate) {
            this.nameCandidate = nameCandidate;
        }
    }

    public static class FileDetails {
        private String extension;

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }
    }
}
