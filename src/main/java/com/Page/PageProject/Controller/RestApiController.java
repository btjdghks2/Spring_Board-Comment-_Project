package com.Page.PageProject.Controller;


import com.Page.PageProject.Entity.PageEntity;
import com.Page.PageProject.service.RestApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RestApiController {

    private final RestApiService restApiService;

    @CrossOrigin(origins = "*")
    @PostMapping("/api/post")
    public ResponseEntity<?> save(@RequestBody PageEntity pageEntity) throws IllegalAccessException {
        return new ResponseEntity<>(restApiService.saved(pageEntity), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/api/")
    public ResponseEntity<?> findAll() {

        return new ResponseEntity<>(restApiService.homePage(), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/api/detail/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(restApiService.DetailPage(id), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/api/detail/{id}/updated/")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PageEntity pageEntity) {
        return new ResponseEntity<>(restApiService.update(id, pageEntity), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/api/detail/{id}/delete/")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody PageEntity pageEntity) {
        return new ResponseEntity<>(restApiService.delete(id), HttpStatus.OK);
    }



}
