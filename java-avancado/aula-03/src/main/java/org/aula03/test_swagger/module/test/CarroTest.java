package org.aula03.test_swagger.module.test;

import org.aula03.test_swagger.module.Carro;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
       String dataFabricacao = ""
    }
}
