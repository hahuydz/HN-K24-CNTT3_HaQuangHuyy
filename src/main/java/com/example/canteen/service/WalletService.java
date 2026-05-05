package com.example.canteen.service;

import com.example.canteen.entity.Wallet;
import com.example.canteen.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    // ==================================================
    // CHUYEN TIEN
    // ==================================================

    @Transactional
    public void transferMoney(
            Long fromWalletId,
            Long toWalletId,
            BigDecimal amount
    ) {

        // Lay vi A
        Wallet walletA =
                walletRepository.findById(fromWalletId)
                        .orElseThrow();

        // Lay vi B
        Wallet walletB =
                walletRepository.findById(toWalletId)
                        .orElseThrow();

        // Tru tien vi A
        walletA.setBalance(
                walletA.getBalance().subtract(amount)
        );

        walletRepository.save(walletA);

        // Gia lap loi he thong
        if (true) {
            throw new RuntimeException("Loi chuyen tien");
        }

        // Cong tien vi B
        walletB.setBalance(
                walletB.getBalance().add(amount)
        );

        walletRepository.save(walletB);
    }

    // ==================================================
    // LUU LOG HE THONG
    // ==================================================

    @Transactional(
            propagation = Propagation.REQUIRES_NEW
    )
    public void saveSystemLog(String message) {

        System.out.println("SYSTEM LOG: " + message);

        // Gia lap save log vao database
    }
}