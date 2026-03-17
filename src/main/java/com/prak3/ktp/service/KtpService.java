package com.prak3.ktp.service;

import com.prak3.ktp.dto.KtpRequest;
import com.prak3.ktp.dto.KtpResponse;

import java.util.List;

public interface KtpService {
    KtpResponse create(KtpRequest request);

    List<KtpResponse> getAll();

    KtpResponse getById(Integer id);

    KtpResponse update(Integer id, KtpRequest request);

    void delete(Integer id);
}
