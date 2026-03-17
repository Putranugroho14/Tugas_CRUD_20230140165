package com.prak3.ktp.impl;

import com.prak3.ktp.dto.KtpRequest;
import com.prak3.ktp.dto.KtpResponse;
import com.prak3.ktp.entity.Ktp;
import com.prak3.ktp.mapper.KtpMapper;
import com.prak3.ktp.repository.KtpRepository;
import com.prak3.ktp.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Autowired
    private KtpMapper ktpMapper;

    @Override
    public KtpResponse create(KtpRequest request) {
        if (ktpRepository.existsByNomorKtp(request.getNomorKtp())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nomor KTP sudah terdaftar!");
        }

        Ktp ktp = ktpMapper.toEntity(request);
        ktp = ktpRepository.save(ktp);
        return ktpMapper.toResponse(ktp);
    }

    @Override
    public List<KtpResponse> getAll() {
        return ktpRepository.findAll().stream()
                .map(ktpMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public KtpResponse getById(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "KTP tidak ditemukan!"));
        return ktpMapper.toResponse(ktp);
    }

    @Override
    public KtpResponse update(Integer id, KtpRequest request) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "KTP tidak ditemukan!"));

        if (ktpRepository.existsByNomorKtpAndIdNot(request.getNomorKtp(), id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nomor KTP sudah terpakai oleh data lain!");
        }

        ktp.setNomorKtp(request.getNomorKtp());
        ktp.setNamaLengkap(request.getNamaLengkap());
        ktp.setAlamat(request.getAlamat());
        ktp.setTanggalLahir(request.getTanggalLahir());
        ktp.setJenisKelamin(request.getJenisKelamin());

        ktp = ktpRepository.save(ktp);
        return ktpMapper.toResponse(ktp);
    }

    @Override
    public void delete(Integer id) {
        if (!ktpRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "KTP tidak ditemukan!");
        }
        ktpRepository.deleteById(id);
    }
}
