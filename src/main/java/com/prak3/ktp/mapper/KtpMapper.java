package com.prak3.ktp.mapper;

import com.prak3.ktp.dto.KtpRequest;
import com.prak3.ktp.dto.KtpResponse;
import com.prak3.ktp.entity.Ktp;
import org.springframework.stereotype.Component;

@Component
public class KtpMapper {
    public Ktp toEntity(KtpRequest request) {
        Ktp ktp = new Ktp();
        ktp.setNomorKtp(request.getNomorKtp());
        ktp.setNamaLengkap(request.getNamaLengkap());
        ktp.setAlamat(request.getAlamat());
        ktp.setTanggalLahir(request.getTanggalLahir());
        ktp.setJenisKelamin(request.getJenisKelamin());
        return ktp;
    }

    public KtpResponse toResponse(Ktp ktp) {
        return new KtpResponse(
                ktp.getId(),
                ktp.getNomorKtp(),
                ktp.getNamaLengkap(),
                ktp.getAlamat(),
                ktp.getTanggalLahir(),
                ktp.getJenisKelamin());
    }
}
