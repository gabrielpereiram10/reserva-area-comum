package com.gabriel.reservaareacomum.domain.valueObjects;

import com.gabriel.reservaareacomum.domain.exceptions.InvalidCPFException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class CPFTest {

    @Test
    @DisplayName("Deve conter apenas os 11 dígitos")
    void deveConterApensOsOnzeDigitos() {
        assertThrows(InvalidCPFException.class, () -> new CPF("000.000.000-00"));
    }

    @Test
    @DisplayName("Deve ser um CPF válido")
    void deveSerUmCPFValido() {
        assertThrows(InvalidCPFException.class, () -> new CPF("00000000000"));
        assertThrows(InvalidCPFException.class, () -> new CPF("30110923150"));
        assertEquals("73460221577", new CPF("73460221577").getValue());
    }
}
