package org.aula11.aula11_car.module;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "CARRO")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "placa", unique = true)
    @Size(min = 7, max = 7, message = "Tamanho da placa diferente de 7")
    @Pattern(regexp = "[A-Z]{3}\\d[A-Z]\\d{2}$", message = "Placa não segue o padrão correto")
    @NonNull private String placa;

    @Column(name = "marca")
    @NotNull(message = "Marca não pode ser null")
    @NotEmpty(message = "Marca não estar em branco")
    @Size(min = 2, message = "Tamanho da marca menor do que o exigido")
    @Pattern(regexp = "[A-Za-z\\-&', ]+", message = "Marca deve conter apenas letras")
    @NonNull private String marca;

    @Column(name = "modelo")
    @NotNull(message = "Modelo não pode ser null")
    @NotEmpty(message = "Modelo não estar em branco")
    @Size(min = 2, message = "Tamanho do modelo menor do que o exigido")
    @NonNull private String modelo;


    @NotNull(message = "Ano de fabricação não pode ser nulo")
    @Positive(message = "Ano de fabricação não pode ser negativo")
    @Min(value = 1960, message = "Ano de fabricação não pode ser menor que 1960")
    @Max(value = 2999, message = "Ano de fabricação não pode ser maior que 2999")
    @Column(name = "anoFabricacao")
    @NonNull private Integer anoFabricacao;

    @ManyToOne
    @JoinColumn(name = "concessionaria_id")
    private Concessionaria concessionaria;

    public void mostrarCarro(){
        System.out.println(toString());
    }
}