package test.negocio;
import main.negocio.ContaCorrente;
import main.negocio.GerenciadoraContas;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(OrderAnnotation.class)
public class GerenciadoraContasTest {

    private GerenciadoraContas gerContas;
    private ContaCorrente conta1;
    private ContaCorrente conta2;

    @BeforeEach
    @Order(1)
    public void init(){
        //Arrange
        conta1 = new ContaCorrente(1, 200, true);
        conta2 = new ContaCorrente(2, 0, true);

        List<ContaCorrente> contas = new ArrayList<>();
        contas.add(conta1);
        contas.add(conta2);

        gerContas = new GerenciadoraContas(contas);
    }

    @Test
    @Order(2)
    @DisplayName("teste transferir valor - (adicionando de uma e removendo de outra) conta, saldo suficiente")
    @Tag("testNegocio")
    public void testTransfereValor(){

        //Arrange
        double expectedSaldoCliente1 = 100;
        double expectedSaldoCliente2 = 100;

        //Act
        gerContas.transfereValor(1, 100, 2);

        //Assert
        Assertions.assertEquals(expectedSaldoCliente1, conta1.getSaldo());
        Assertions.assertEquals(expectedSaldoCliente2, conta2.getSaldo());
    }

    @Test
    @Order(3)
    @DisplayName("teste transferir valor - (adicionando de uma e removendo de outra) conta, saldo insuficiente")
    @Tag("testNegocio")
    public void testTransfereValor_saldoInsuficiente(){

        //Arrange
        double expectedSaldoCliente1 = 200;
        double expectedSaldoCliente2 = 0;

        //Act
        boolean success = gerContas.transfereValor(1, 250, 2);

        //Assert
        Assertions.assertFalse(success);
        Assertions.assertEquals(expectedSaldoCliente1, conta1.getSaldo());
        Assertions.assertEquals(expectedSaldoCliente2, conta2.getSaldo());
    }

    @AfterEach
    @Order(4)
    public void tearDown() {
		System.out.println("Teste executado!");
    }
}