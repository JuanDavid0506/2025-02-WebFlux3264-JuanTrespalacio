package com.parcial.dos.parcialdos.account.service;

import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import java.util.List;

public interface IAccountService {
    AccountResponseDTO createAccount(AccountRequestDTO dto);
    List<AccountResponseDTO> getAllAccounts();
    AccountResponseDTO getAccountById(Long id);
    String updateBalance(Long id, AccountRequestDTO dto);
    void deleteAccount(Long id);
    AccountOwnerBalanceDTO getByAccountNumber(String numeroCuenta);
}