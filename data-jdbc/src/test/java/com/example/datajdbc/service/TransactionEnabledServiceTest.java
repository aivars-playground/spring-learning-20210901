package com.example.datajdbc.service;

import com.example.datajdbc.repository.CustomJdbcRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionEnabledServiceTest {

    @Autowired
    private CustomJdbcRepository customJdbcRepository;

    @Autowired
    private TransactionEnabledService transactionEnabledService;

    @Test
    void testNonTransactional() {
        assertThat(customJdbcRepository.getFlights()).hasSize(0);
        assertThrows(DataIntegrityViolationException.class, () -> {
            transactionEnabledService.savingFailingNonTransactional();
        });
        assertThat(customJdbcRepository.getFlights()).hasSize(1);
    }

    @Test
    void testTransactional() {
        assertThat(customJdbcRepository.getFlights()).hasSize(0);
        assertThrows(DataIntegrityViolationException.class, () -> {
            transactionEnabledService.savingFailingTransactional();
        });
        assertThat(customJdbcRepository.getFlights()).hasSize(0);
    }
}