package test.negocio;
import org.junit.jupiter.api.*;
import main.negocio.*;

import java.util.ArrayList;
import java.util.List;

// OBS: USO DE DADOS FAKES PARA TESTE
public class GerenciadoraClientesTest {

    private Cliente c1;
    private Cliente c2;
    private GerenciadoraClientes gerClientes;

    @BeforeEach
    public void init(){
        //Arrange
        c1 = new Cliente (1, "João Pedro", 17, "joao@neo", 1, true);
        c2 = new Cliente (2, "Victor Eduardo", 18, "victor@neo", 1, true);

        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(c1);
        clientesDoBanco.add(c2);

        gerClientes = new GerenciadoraClientes(clientesDoBanco);
    }

    @Test
    @DisplayName("Teste pesquisa cliente com base no seu ID")
    @Tag("testNegocio")
    public void testPesquisaCliente(){

        //Act
        Cliente searchResult = gerClientes.pesquisaCliente(1);

        //Assert
        Assertions.assertEquals(searchResult.getId(), c1.getId());
    }

    @Test
    @DisplayName("teste remove cliente")
    @Tag("testNegocio")
    public void removeCliente(){

        //Act
        boolean removeCliente = gerClientes.removeCliente(1);

        //Assert
        Assertions.assertEquals(removeCliente, true);
        Assertions.assertEquals(gerClientes.getClientesDoBanco().size(), 1);
        Assertions.assertNull(gerClientes.pesquisaCliente(1));
    }

    @AfterEach
    public void tearDown() {
        gerClientes.removeAll();
    }
}
