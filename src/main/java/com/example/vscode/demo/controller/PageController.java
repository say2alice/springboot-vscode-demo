package com.example.vscode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PageController {

  @GetMapping("/page/{pageName}")
  public String handleGetPages(@PathVariable("pageName") String pageName) {
    log.debug("handle page {}", pageName);
    
    return pageName;
  }
}
