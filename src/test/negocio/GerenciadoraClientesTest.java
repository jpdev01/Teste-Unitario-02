package test.negocio;
import org.junit.jupiter.api.*;
import main.negocio.*;

import java.util.ArrayList;
import java.util.List;

// OBS: USO DE DADOS FAKES PARA TESTE
public class GerenciadoraClientesTest {

    @BeforeEach
    public void init(){

    }

    @Test
    @DisplayName("Testa se a função pesquisa cliente está trazendo o cliente correto com base no ID")
    @Tag("testCalculator")
    public void testPesquisaCliente(){

        //Arrange
        Cliente c1 = new Cliente (1, "João Pedro", 17, "joao@neo", 1, true);
        Cliente c2 = new Cliente (2, "Victor Eduardo", 18, "victor@neo", 1, true);

        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(c1);
        clientesDoBanco.add(c2);

        GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientesDoBanco);

        //Act
        Cliente searchResult = gerClientes.pesquisaCliente(1);

        //Assert
        Assertions.assertEquals(searchResult.getId(), c1.getId());
    }


}
