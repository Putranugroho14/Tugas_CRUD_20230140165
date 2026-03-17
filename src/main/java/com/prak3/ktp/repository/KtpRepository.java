package com.prak3.ktp.repository;

import com.prak3.ktp.entity.Ktp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KtpRepository extends JpaRepository<Ktp, Integer> {
    boolean existsByNomorKtp(String nomorKtp);

    boolean existsByNomorKtpAndIdNot(String nomorKtp, Integer id);
}
