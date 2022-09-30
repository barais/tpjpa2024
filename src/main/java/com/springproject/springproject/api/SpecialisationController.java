package com.springproject.springproject.api;

import com.springproject.springproject.domain.Specialisation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "Get all Specialisations")
    @GetMapping("")
    public List<String> getAllSpecialisation() {
        return Stream.of(Specialisation.values())
                .map(Specialisation::toString)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get one Specialisations")
    @GetMapping("/{id}")
    public String getOneSpecialisation(@Parameter(description = "id of specialisation to be searched") @PathVariable Long id) {
        return Arrays.stream(Specialisation.values())
                .collect(Collectors.toList())
                .get(Math.toIntExact(id))
                .toString();
    }
}
