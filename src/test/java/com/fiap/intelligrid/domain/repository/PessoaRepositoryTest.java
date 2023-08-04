package com.fiap.intelligrid.domain.repository;

import com.fiap.intelligrid.domain.entity.Genero;
import com.fiap.intelligrid.domain.entity.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testFindById() {
        Optional<Pessoa> pessoa = pessoaRepository.findById(1L);
        assertNotNull(pessoa);
    }

    @Test
    public void testSave() {
        Pessoa pessoa = new Pessoa(
                null,
                "Enrico",
                "12341234123",
                LocalDate.now(),
                Genero.FEMININO,
                Collections.emptyList()
        );

        final Pessoa saved = pessoaRepository.save(pessoa);
        assertNotNull(saved.getId());
    }

    @Test
    public void testFindAll() {
        var pessoas = pessoaRepository.findAll();
        assertNotNull(pessoas);
    }

    @Test
    public void testDelete() {
        long deleted = pessoaRepository.deletePessoaById(2L);
        assertEquals(1, deleted);
    }

}