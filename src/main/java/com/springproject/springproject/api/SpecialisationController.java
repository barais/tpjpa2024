package com.springproject.springproject.api;

import com.springproject.springproject.domain.Specialisation;
import com.springproject.springproject.dto.DoctorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/specialisation")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class SpecialisationController {

    @Operation(summary = "Get all Specialisations")
    @GetMapping("")
    @ResponseStatus(code=HttpStatus.OK)
    public List<String> getAllSpecialisation() {
        return Stream.of(Specialisation.values())
                .map(Specialisation::toString)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get one Specialisations")
    @GetMapping("/{id}")
    public ResponseEntity<String> getOneSpecialisation(@Parameter(description = "id of specialisation to be searched") @PathVariable Long id) {
        String ret = "";
        try {
            ret= Arrays.stream(Specialisation.values())
                    .collect(Collectors.toList())
                    .get(Math.toIntExact(id))
                    .toString();
        }
        catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(ret);
    }
}
