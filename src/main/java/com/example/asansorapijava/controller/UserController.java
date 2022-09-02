package com.example.asansorapijava.controller;


import com.example.asansorapijava.dao.entitiy.User;
import com.example.asansorapijava.dto.ApartmentDto;
import com.example.asansorapijava.dto.UserDto;
import com.example.asansorapijava.service.impl.UserServiceImpl;
import com.example.asansorapijava.util.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping()
    @Operation(summary = "Get All Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succesful Operation", content = @Content(schema = @Schema(implementation = UserDto.class)))
    })
    public ResponseEntity<List<UserDto>> getAll() {

        List<UserDto> data = userServiceImpl.getUsers();
        return ResponseEntity.ok(data);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get By Id Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succesful Operation", content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "Error")
    })
    public ResponseEntity<UserDto> getById(@PathVariable(value = "id", required = true) Long id) {

        try {
            UserDto userDto = userServiceImpl.getById(id);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //return 404, with null body
        }
    }

    @PostMapping
    // @ApiOperation(value = "Create Operation", response = NoteDto.class)
    @Operation(summary = "Create Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Succesfull Operation", content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "409", description = "Role could not be created")
    })
    public ResponseEntity<UserDto> createApartment(@Valid @RequestBody UserDto userDto) {

        try {
            UserDto newUser = userServiceImpl.save(userDto);

            return ResponseEntity.created(new URI(ApiPaths.ApartmentCtrl.CTRL + "/" + newUser.getId())).body(newUser);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    //@ApiOperation(value = "Update Operation", response = NoteDto.class)
    @Operation(summary = "Update Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Succesfull Operation", content = @Content(schema = @Schema(implementation = ApartmentDto.class))),
            @ApiResponse(responseCode = "404", description = "Note not found ")})
    public ResponseEntity<Object> updateApartment(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody UserDto userdto, User user) {
        try {
            userServiceImpl.update(id, userdto, user);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    //@ApiOperation(value = "Delete Operation", response = Boolean.class)
    @Operation(summary = "Delete Operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Succesfull Operation", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "400", description = "Id cannot be null"),
            @ApiResponse(responseCode = "404", description = "An error occured")})
    public ResponseEntity<Void> delete(@PathVariable(value = "id", required = true) Long id, User user) {
        try {
            if (id != null) {

                userServiceImpl.delete(id, user);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}

