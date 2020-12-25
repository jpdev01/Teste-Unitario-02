package test.negocio;

import main.negocio.ContaCorrente;
import main.negocio.GerenciadoraContas;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadoraContasTest {

    private GerenciadoraContas gerContas;

    @BeforeEach
    public void init(){

    }

    @Test
    @DisplayName("teste transferir valor - (adicionando de uma e removendo de outra) conta")
    @Tag("testNegocio")
    public void testTransfereValor(){

        //Arrange
        ContaCorrente conta1 = new ContaCorrente(1, 200, true);
        ContaCorrente conta2 = new ContaCorrente(2, 0, true);

        List<ContaCorrente> contas = new ArrayList<>();
        contas.add(conta1);
        contas.add(conta2);

        gerContas = new GerenciadoraContas(contas);

        //Act
        gerContas.transfereValor(1, 100, 2);

        //Assert
        Assertions.assertEquals(conta1.getSaldo(), 100);
        Assertions.assertEquals(conta2.getSaldo(), 100);
    }
}