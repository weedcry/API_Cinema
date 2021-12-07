package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.PasswordResetToken;
import com.QCINE.Main.Entity.User_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetToken_Repository extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User_Entity user);
}
