package com.example.loginauthapi.repositories;

import com.example.loginauthapi.domain.user.cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<cadastro, Integer> {
}
