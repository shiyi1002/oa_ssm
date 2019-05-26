package com.qf.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

@Controller
@RequestMapping("/process")
public class ProcessController {
@Autowired
    private RepositoryService repositoryService;
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "process/process_add";
    }
@RequestMapping("/add")
    public String add(String processName, @RequestParam MultipartFile processFile) throws IOException {
    DeploymentBuilder builder = repositoryService.createDeployment();
    builder.name(processName).addZipInputStream(new ZipInputStream(processFile.getInputStream())).deploy();
    return "process/process_add";
}


}
