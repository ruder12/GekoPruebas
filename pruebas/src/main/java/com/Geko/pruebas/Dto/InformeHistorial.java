package com.Geko.pruebas.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class InformeHistorial {
    private String fileName;
    private byte[] stream;
    private int length;
}
