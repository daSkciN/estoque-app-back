package com.estoqueApp.estoqueApp.dto.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaCreateDto {

    @NotNull(message = "O nome é obrigatório.")
    private String nome;
}
