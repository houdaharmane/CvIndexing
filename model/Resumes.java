package com.baeldung.demo.model;

public class Resumes {
    private String content;
    private ResumeDetails resumeDetails;
    private FileDetails fileDetails;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content=content;
    }

    public ResumeDetails getResumeDetails() {
        return resumeDetails;
    }

    public void setResumeDetails(ResumeDetails resumeDetails) {
        this.resumeDetails = resumeDetails;
    }

    public FileDetails getFileDetails() {
        return fileDetails;
    }

    public void setFileDetails(FileDetails fileDetails) {
        this.fileDetails = fileDetails;
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
        private String ExtensionFile;


        public String getExtensionFile() {
            return ExtensionFile;
        }

        public void setExtensionFile(String extensionFile) {
            this.ExtensionFile = extensionFile;
        }
    }
}

