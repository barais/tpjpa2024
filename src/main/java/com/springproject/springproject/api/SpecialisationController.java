package com.springproject.springproject.api;

import com.springproject.springproject.domain.Specialisation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/specialisation")
public class SpecialisationController {

    @GetMapping("")
    public List<String> getAllSpecialisation() {
        return Arrays.stream(Specialisation.values()).map(Specialisation::toString).toList();
    }

    @GetMapping("/{id}")
    public String getOneSpecialisation(@PathVariable Long id) {
        return Arrays.stream(Specialisation.values()).toList().get(Math.toIntExact(id)).toString();
    }
}
