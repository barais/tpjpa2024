package com.springproject.springproject.api;

import com.springproject.springproject.domain.Specialisation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/specialisation")
public class SpecialisationController {

    @GetMapping("")
    public List<String> getAllSpecialisation() {
        return Stream.of(Specialisation.values())
                .map(Specialisation::toString)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public String getOneSpecialisation(@PathVariable Long id) {
        return Arrays.stream(Specialisation.values())
                .collect(Collectors.toList())
                .get(Math.toIntExact(id))
                .toString();
    }
}
