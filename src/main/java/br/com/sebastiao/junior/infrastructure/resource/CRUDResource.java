package br.com.sebastiao.junior.infrastructure.resource;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CRUDResource<D, I> {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<D> findAll();

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    D findById(@PathVariable("id") I id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    D insert(@RequestBody(required = true) D dto);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    D update(@RequestBody(required = true) D dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") I id);
}
