package com.baeldung.demo.model;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;

@Entity
public class Candidate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fileNames;
    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }




    public List<String> getFileNameList() {
        return fileNames != null ? Arrays.asList(fileNames.split(",")) : null;
    }


    public void setFileNameList(List<String> fileNameList) {
        this.fileNames = fileNameList != null ? String.join(",", fileNameList) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFilePath(String string) {
    }
}
