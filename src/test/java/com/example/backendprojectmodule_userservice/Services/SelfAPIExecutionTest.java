package com.example.backendprojectmodule_userservice.Services;

import com.example.backendprojectmodule_userservice.Models.Token;
import com.example.backendprojectmodule_userservice.Repositories.TokenRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SelfAPIExecutionTest {
    @Autowired
    private SelfAPIExecution selfAPIExecution;
    @MockBean
    private TokenRepository tokenRepository;
    @Captor
    private ArgumentCaptor<Token> tokenArgumentCaptor;

    @Test
    void testForTokenValidityWhenUserLogout(){
        Token token = new Token();
        token.setValue("TestToken");
        token.setDeleted(false);
        when(tokenRepository.findByValueAndDeleted(token.getValue(), false)).
                thenReturn(Optional.of(token));
        selfAPIExecution.userLogout(token.getValue());
        verify(tokenRepository).save(tokenArgumentCaptor.capture());
        assertTrue(tokenArgumentCaptor.getValue().isDeleted());
    }
}