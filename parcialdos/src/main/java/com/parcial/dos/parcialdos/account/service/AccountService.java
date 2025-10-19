package com.parcial.dos.parcialdos.account.service;

import com.parcial.dos.parcialdos.account.dto.*;
import com.parcial.dos.parcialdos.account.entity.Account;
import com.parcial.dos.parcialdos.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service
@Transactional
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO dto) {
        Account account = new Account(
                dto.getNumeroCuenta(),
                dto.getDueno(),
                dto.getBalanceActual(),
                true
        );
        Account saved = accountRepository.save(account);
        return mapToResponseDTO(saved);
    }

    @Override
    public List<AccountResponseDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(this::mapToResponseDTO)
                .orElse(null);
    }

    @Override
    public String updateBalance(Long id, AccountRequestDTO dto) {
        return accountRepository.findById(id).map(account -> {
            BigDecimal oldBalance = account.getBalance();
            account.setBalance(dto.getBalanceActual());
            accountRepository.save(account);
            return "La cuenta " + account.getAccountNumber() +
                    " fue actualizada: balanceAnterior=" + oldBalance +
                    ", balanceActual=" + account.getBalance();
        }).orElse("Cuenta no encontrada");
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public AccountOwnerBalanceDTO getByAccountNumber(String numeroCuenta) {
        return accountRepository.findByAccountNumber(numeroCuenta)
                .map(a -> new AccountOwnerBalanceDTO(a.getOwnerName(), a.getBalance()))
                .orElse(null);
    }

    private AccountResponseDTO mapToResponseDTO(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(account.getId());
        dto.setNumeroCuenta(account.getAccountNumber());
        dto.setDueno(account.getOwnerName());
        dto.setBalanceActual(account.getBalance());
        dto.setActive(account.getActive());
        return dto;
    }
}