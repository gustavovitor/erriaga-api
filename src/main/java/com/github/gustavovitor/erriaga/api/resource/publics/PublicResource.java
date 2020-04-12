package com.github.gustavovitor.erriaga.api.resource.publics;

import com.github.gustavovitor.erriaga.api.domain.source.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/source")
public class PublicResource {

    @GetMapping
    public ResponseEntity<Source> source() {
        return ResponseEntity.ok(new Source());
    }

}
