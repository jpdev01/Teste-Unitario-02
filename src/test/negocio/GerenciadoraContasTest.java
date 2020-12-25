package test.negocio;

import main.negocio.ContaCorrente;
import main.negocio.GerenciadoraContas;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadoraContasTest {

    private GerenciadoraContas gerContas;
    private ContaCorrente conta1;
    private ContaCorrente conta2;

    @BeforeEach
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
    @DisplayName("teste transferir valor - (adicionando de uma e removendo de outra) conta, saldo suficiente")
    @Tag("testNegocio")
    public void testTransfereValor(){

        //Arrange

        //Act
        gerContas.transfereValor(1, 100, 2);

        //Assert
        Assertions.assertEquals(100, conta1.getSaldo());
        Assertions.assertEquals(100, conta2.getSaldo());
    }

    @Test
    @DisplayName("teste transferir valor - (adicionando de uma e removendo de outra) conta, saldo insuficiente")
    @Tag("testNegocio")
    public void testTransfereValor_saldoInsuficiente(){

        //Arrange

        //Act
        boolean success = gerContas.transfereValor(1, 250, 2);

        //Assert
        Assertions.assertFalse(success);
        Assertions.assertEquals(200, conta1.getSaldo());
        Assertions.assertEquals(0, conta2.getSaldo());
    }
}