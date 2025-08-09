package org.giftilist.backend;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String health() { return "OK"; }
    public String hello()  { return "Hello from Gifti List 👋"; }
}