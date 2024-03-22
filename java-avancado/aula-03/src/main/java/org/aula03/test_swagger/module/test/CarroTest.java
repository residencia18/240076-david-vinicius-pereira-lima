package org.aula03.test_swagger.module.test;

import org.aula03.test_swagger.module.Carro;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarroTest {
   @Test
    public void definirPlaca(){
       Carro carro = new Carro();
       String placa = "ABC1D23";
       try {
           carro.setPlaca(placa);
       }catch (Exception e){
           fail("Gerou exceção indevida: "+e.getMessage());
       }
       assertEquals(placa, carro.getPlaca());
    }

    @Test
    public void definirPlacaNula(){
       Carro carro = new Carro();
       String placa = "";
       try {
           carro.setPlaca(placa);
       }catch (Exception e){
            assertEquals("Placa não pode estar vazia ou nula", e.getMessage());
       }
       assertNotEquals(placa , carro.getPlaca());
    }

    @Test
    public void definirTamanhoPlaca(){
       Carro carro = new Carro();
       String placa = "ABC1D2";
       try {
           carro.setPlaca(placa);
       }catch (Exception e){
           assertEquals("Tamanho da placa é diferente de 7", e.getMessage());
       }
       assertNotEquals(placa, carro.getPlaca());
    }

    @Test
    public void definirPlacaDespadronizada(){
       Carro carro =  new Carro();
       String placa = "ABC1D2E";
       try {
           carro.setPlaca(placa);
       }catch (Exception e){
           assertEquals("Placa não segue padrão correto", e.getMessage());
       }
       assertNotEquals(placa, carro.getPlaca());
    }

    @Test
    public void definirAnoFabricacao(){
       Carro carro = new Carro();
       Integer anoFab = 1992;
       try {
           carro.setAnoFabricacao(anoFab);
       }catch (Exception e){
           fail("Gerou exceção indevida: "+ e.getMessage());
       }
       assertEquals(anoFab, carro.getAnoFabricacao());
    }

    @Test
    public void definirAnoFabricacaoNulo(){
       Carro carro = new Carro();
       Integer anoFab = null;
       try {
           carro.setAnoFabricacao(anoFab);
       } catch (Exception e) {
            assertEquals("Ano de fabricação não pode ser nulo" , e.getMessage());
       }
       assertNotEquals(anoFab, carro.getAnoFabricacao());
    }

    @Test
    public void definirAnoFabricacaoNegativo(){
        Carro carro = new Carro();
        Integer anoFab = -2012;
        try {
            carro.setAnoFabricacao(anoFab);
        } catch (Exception e) {
            assertEquals("Ano de fabricação não pode ser negativo" , e.getMessage());
        }
        assertNotEquals(anoFab, carro.getAnoFabricacao());
    }

    @Test
    public void definirAnoFabricacaoMenor(){
        Carro carro = new Carro();
        Integer anoFab = 1912;
        try {
            carro.setAnoFabricacao(anoFab);
        } catch (Exception e) {
            assertEquals("Ano de fabricação abaixo de 1960" , e.getMessage());
        }
        assertNotEquals(anoFab, carro.getAnoFabricacao());
    }

    @Test
    public void definirAnoFabricacaoMaior(){
        Carro carro = new Carro();
        Integer anoFab = 3001;
        try {
            carro.setAnoFabricacao(anoFab);
        } catch (Exception e) {
            assertEquals("Ano de fabricação acima de 2999" , e.getMessage());
        }
        assertNotEquals(anoFab, carro.getAnoFabricacao());
    }

    @Test
    public void definirMarca(){
        Carro carro = new Carro();
        String marca = "Audi";
        try {
            carro.setMarca(marca);
        } catch (Exception e) {
            fail("Exceção indevida: "+e.getMessage());
        }
        assertEquals(marca, carro.getMarca());
    }

    @Test
    public void definirMarcaNula(){
        Carro carro = new Carro();
        String marca = "";
        try {
            carro.setMarca(marca);
        } catch (Exception e) {
            assertEquals("Marca não pode ser nula ou vazia" , e.getMessage());
        }
        assertNotEquals(marca, carro.getMarca());
    }

    @Test
    public void definirMarcaComDigitos(){
        Carro carro = new Carro();
        String marca = "Audi 123";
        try {
            carro.setMarca(marca);
        } catch (Exception e) {
            assertEquals("Marca não pode conter dígitos" , e.getMessage());
        }
        assertNotEquals(marca, carro.getMarca());
    }

    @Test
    public void definirTamanhoMarca(){
        Carro carro = new Carro();
        String marca = "Au";
        try {
            carro.setMarca(marca);
        } catch (Exception e) {
            assertEquals("Tamanho da marca menor que 3" , e.getMessage());
        }
        assertNotEquals(marca, carro.getMarca());
    }

    @Test
    public void definirMarcaDespadronizada(){
        Carro carro = new Carro();
        String marca = "audi";
        try {
            carro.setMarca(marca);
        } catch (Exception e) {
            assertEquals("Marca não segue o padrão correto" , e.getMessage());
        }
        assertNotEquals(marca, carro.getMarca());
    }
}
