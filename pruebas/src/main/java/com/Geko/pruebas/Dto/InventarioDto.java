package com.Geko.pruebas.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class InventarioDto {

    private Long id;
    private ProductoDto producto;
    private String movimiento;
    private int cantidad;
    private Date FechaRegistro;

}
