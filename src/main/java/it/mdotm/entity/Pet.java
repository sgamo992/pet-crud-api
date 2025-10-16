package it.mdotm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Species cannot be null")
    @NotBlank(message = "Species is mandatory")
    @Column(nullable = false)
    private String species;

    @PositiveOrZero(message = "Age must be zero or positive")
    private Integer age;

    private String ownerName;


}