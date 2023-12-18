package com.example.examendamdesarrollo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {

    private Integer id;
    private String nombre;
    private String correo;

}
