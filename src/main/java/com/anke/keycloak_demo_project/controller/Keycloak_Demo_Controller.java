package com.anke.keycloak_demo_project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo")
public class Keycloak_Demo_Controller {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('client_admin')") // Çalıştırılacak olan metotun sadece client_admin rolüne sahip kullanıcılar tarafından erişilebilmesini sağlar.
    // Girilen rolün doğruluğunu kontrol eder. Eğer doğruysa metot çalışır, değilse 403 Forbidden hatası döner.
    public ResponseEntity<String> adminDetails(){
        return ResponseEntity.ok("Welcome Admin");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('client_user')")
    public ResponseEntity<String> userDetails(){
        return ResponseEntity.ok("Welcome User");
    }

    @GetMapping("/hello-everyone")
    @PreAuthorize("hasAnyRole('client_admin', 'client_user')")
    public ResponseEntity<String> helloEveryone(){
        return ResponseEntity.ok("Hello Everyone");
    }
}
