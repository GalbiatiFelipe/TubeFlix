package com.tubetv.service;

import com.tubetv.mapper.UserMapper;
import com.tubetv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário ou Senha não encontrado"));
        // findUserByEmail é um optional portanto deve ter um orElse para validação
    }
    /*metodo precisa de um retorno do tipo 'UserDetails' interface que foi implementada na entidade 'User'
    * garantindo que o metodo busque o username corretamente.
    * e para essa busca é necessario a 'Repository' que consegue fazer uma querry para buscar o username por email
    * */
}
