package com.example.asansorapijava.controller;

import com.example.asansorapijava.dto.ApartmentDto;
import com.example.asansorapijava.service.impl.ApartmentServiceImpl;
import com.example.asansorapijava.util.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.ApartmentCtrl.CTRL)

@Slf4j
@CrossOrigin
@NoArgsConstructor
@AllArgsConstructor

public class ApartmentController {
    @Autowired
    private ApartmentServiceImpl apartmentServiceImpl;

    @GetMapping()
    @Operation(summary = "Get All Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succesful Operation", content = @Content(schema = @Schema(implementation = ApartmentDto.class)))
    })
    public ResponseEntity<List<ApartmentDto>> getAll() {

        List<ApartmentDto> data = apartmentServiceImpl.getApartments();
        return ResponseEntity.ok(data);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get By Id Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succesful Operation", content = @Content(schema = @Schema(implementation = ApartmentDto.class))),
            @ApiResponse(responseCode = "404", description = "Error")
    })
    public ResponseEntity<ApartmentDto> getById(@PathVariable(value = "id", required = true) Long id) {

        try {
            ApartmentDto apartmentDto = apartmentServiceImpl.getById(id);
            return ResponseEntity.ok(apartmentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //return 404, with null body
        }
    }

    @PostMapping
    // @ApiOperation(value = "Create Operation", response = NoteDto.class)
    @Operation(summary = "Create Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfull Operation", content = @Content(schema = @Schema(implementation = ApartmentDto.class))),
            @ApiResponse(responseCode = "409", description = "Role could not be created")
    })
    public ResponseEntity<ApartmentDto> createApartment(@Valid @RequestBody ApartmentDto apartmentDto) {

        try {
            ApartmentDto newApt = apartmentServiceImpl.save(apartmentDto);

            return ResponseEntity.created(new URI(ApiPaths.ApartmentCtrl.CTRL + "/" + newApt.getId())).body(newApt);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    //@ApiOperation(value = "Update Operation", response = NoteDto.class)
    @Operation(summary = "Update Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Succesfull Operation",content = @Content(schema = @Schema(implementation = ApartmentDto.class))),
            @ApiResponse(responseCode = "404", description = "Note not found ")})
    public ResponseEntity<Object> updateApartment(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody ApartmentDto apartment) {
        try {
            apartmentServiceImpl.update(id, apartment);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    //@ApiOperation(value = "Delete Operation", response = Boolean.class)
    @Operation(summary = "Delete Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Succesfull Operation",content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "Id cannot be null"),
            @ApiResponse(responseCode = "404", description = "An error occured")})
    public ResponseEntity<Void> delete(@PathVariable(value = "id", required = true) Long id) {
        try {
            if (id != null) {

                apartmentServiceImpl.delete(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}
