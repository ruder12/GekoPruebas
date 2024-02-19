package com.Geko.pruebas.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaDto {


    private Long id;

    private String nit;

    private String nombre;
    private String address;
    private String telefono;

    private Date fecha;
}
