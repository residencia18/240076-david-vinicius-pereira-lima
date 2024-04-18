package org.aula06.aula06_car.module;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "placa")
    @NonNull private String placa;

    @Column(name = "marca")
    @NonNull private String marca;

    @Column(name = "modelo")
    @NonNull private String modelo;

    @Column(name = "anoFabricacao")
    @NonNull private Integer anoFabricacao;

    @ManyToOne
    @JoinColumn(name = "concessionaria_id")
    private Concessionaria concessionaria;

    public void setPlaca(String placa) throws Exception {
        if (placa == null || placa.isEmpty())
            throw new Exception("Placa não pode estar vazia ou nula");
        else if (placa.length() != 7)
            throw new Exception("Tamanho da placa é diferente de 7");
        else if (!verificaPlaca(placa))
            throw new Exception("Placa não segue padrão correto");
        else
            this.placa = placa;
    }

    public void setMarca(String marca) throws Exception {
        if(marca == null || marca.isEmpty())
            throw new Exception("Marca não pode ser nula ou vazia");
        else if(marca.chars().anyMatch(Character::isDigit))
            throw new Exception("Marca não pode conter dígitos");
        else if (marca.length() < 3)
            throw new Exception("Tamanho da marca menor que 3");
        else if (!verificaMarca(marca))
            throw new Exception("Marca não segue o padrão correto");
        else
            this.marca = marca;
    }

    public void setAnoFabricacao(Integer anoFabricacao) throws Exception{
        if (anoFabricacao == null)
            throw new Exception("Ano de fabricação não pode ser nulo");
        else if (anoFabricacao < 0)
            throw new Exception("Ano de fabricação não pode ser negativo");
        else if (anoFabricacao < 1960)
            throw new Exception("Ano de fabricação abaixo de 1960");
        else if (anoFabricacao > 2999)
            throw new Exception("Ano de fabricação acima de 2999");
        else
            this.anoFabricacao = anoFabricacao;
    }

    public void mostrarCarro(){
        System.out.println(toString());
    }

    public boolean verificaPlaca(String placa){
        String regex = "[A-Z]{3}\\d[A-Z]\\d{2}$";
        return placa.matches(regex);
    }

    public boolean verificaMarca(String nome){
        return nome.matches("[A-Z][a-zA-Z]+$");
    }

}