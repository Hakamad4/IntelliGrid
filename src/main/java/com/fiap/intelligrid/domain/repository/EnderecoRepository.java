package com.fiap.intelligrid.domain.repository;

import com.fiap.intelligrid.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	long deleteEnderecoById(Long id);

	List<Endereco> findByBairroOrLogradouroOrCidadeOrCep(String bairro, String logradouro, String cidade, String cep);

	@Query("SELECT e FROM Endereco e WHERE " +
			"(:logradouro IS NULL OR e.logradouro = :logradouro) AND " +
			"(:bairro IS NULL OR e.bairro = :bairro) AND " +
			"(:cep IS NULL OR e.cep = :cep) AND " +
			"(:cidade IS NULL OR e.cidade = :cidade)")
	List<Endereco> findByRuaBairroCidadeCep(@Param("logradouro") String rua,
										 	@Param("bairro") String bairro,
										 	@Param("cidade") String cidade,
										 	@Param("cep") String cep);

}