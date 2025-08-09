package org.giftilist.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
class HelloController {
    private final org.giftilist.backend.HelloService service;
    HelloController(org.giftilist.backend.HelloService service) { this.service = service; }

    @GetMapping("/health")
    String health() { return service.health(); }

    @GetMapping("/hello")
    String hello() { return service.hello(); }
}