package test.negocio;
import main.negocio.Cliente;
import main.negocio.GerenciadoraClientes;
import main.negocio.IdadeNaoPermitidaException;
import org.junit.jupiter.api.*;

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

        //Arrange
        int expectedIdCliente = c1.getId();

        //Act
        Cliente searchResult = gerClientes.pesquisaCliente(1);

        //Assert
        Assertions.assertEquals(searchResult.getId(), expectedIdCliente);
    }

    @Test
    @DisplayName("Teste pesquisa cliente com base no seu ID - INEXISTENTE")
    @Tag("testNegocio")
    public void testPesquisaClienteInexistente(){

        //Arrange

        //Act
        Cliente searchResult = gerClientes.pesquisaCliente(3);

        //Assert
        Assertions.assertNull(searchResult);
    }

    @Test
    @DisplayName("teste remove cliente")
    @Tag("testNegocio")
    public void testRemoveCliente(){
        //Arrange
        int expectedValueOfClientes = 1;

        //Act
        boolean removeCliente = gerClientes.removeCliente(1);

        //Assert
        Assertions.assertTrue(removeCliente);
        Assertions.assertEquals(expectedValueOfClientes, gerClientes.getClientesDoBanco().size());
        Assertions.assertNull(gerClientes.pesquisaCliente(1));
    }

    @Test
    @DisplayName("teste remove cliente - INEXISTENTE")
    @Tag("testNegocio")
    public void testRemoveClienteInexistente(){
        //Arrange
        int expectedValueOfClientes = 2;

        //Act
        boolean removeCliente = gerClientes.removeCliente(3);

        //Assert
        Assertions.assertFalse(removeCliente);
        Assertions.assertEquals(expectedValueOfClientes, gerClientes.getClientesDoBanco().size());
    }

    @Test
    @DisplayName("teste valida idade - IDADE PERMITIDA")
    @Tag("testNegocio")
    public void testValidaIdade() throws IdadeNaoPermitidaException{
        //Arrange
        Cliente cliente = new Cliente(3, "Joao", 18, "jptruchinski@gmail.com", 1, true);
        Cliente cliente2 = new Cliente(4, "Jose", 65, "jptruchinski@gmail.com", 1, true);

        //Act
        boolean validaIdade = gerClientes.validaIdade(cliente.getIdade());
        boolean validaIdade2 = gerClientes.validaIdade(cliente.getIdade());

        //Assert
        Assertions.assertTrue(validaIdade);
        Assertions.assertTrue(validaIdade2);
    }

    //@Test(expected = IdadeNaoPermitidaException.class)
    @Test
    @DisplayName("teste valida idade - IDADE NÃO PERMITIDA")
    @Tag("testNegocio")
    public void testValidaIdadeInvalida() throws IdadeNaoPermitidaException{
        //Arrange
        Cliente cliente = new Cliente(3, "Joao", 18, "jptruchinski@gmail.com", 1, true);
        Cliente cliente2 = new Cliente(4, "Jose", 66, "jptruchinski@gmail.com", 1, true);
        String expectedException = IdadeNaoPermitidaException.MSG_IDADE_INVALIDA;

        //Act
        try{
            gerClientes.validaIdade(cliente.getIdade());
            gerClientes.validaIdade(cliente.getIdade());
            Assertions.assertTrue(false);
        }catch (Exception e){
            //Assert
            Assertions.assertEquals(expectedException, e.getMessage());
        }

    }

    @AfterEach
    public void tearDown() {
        gerClientes.removeAll();
    }
}
