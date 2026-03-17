package com.prak3.ktp.controller;

import com.prak3.ktp.dto.KtpRequest;
import com.prak3.ktp.dto.KtpResponse;
import com.prak3.ktp.model.WebResponse;
import com.prak3.ktp.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ktp")
@CrossOrigin(origins = "*")
public class KtpController {

    @Autowired
    private KtpService ktpService;

    @PostMapping
    public WebResponse<KtpResponse> create(@RequestBody @Validated KtpRequest request) {
        KtpResponse response = ktpService.create(request);
        return WebResponse.<KtpResponse>builder()
                .status("success")
                .message("Data KTP berhasil ditambahkan")
                .data(response)
                .build();
    }

    @GetMapping
    public WebResponse<List<KtpResponse>> getAll() {
        List<KtpResponse> responses = ktpService.getAll();
        return WebResponse.<List<KtpResponse>>builder()
                .status("success")
                .message("Berhasil mengambil data")
                .data(responses)
                .build();
    }

    @GetMapping("/{id}")
    public WebResponse<KtpResponse> getById(@PathVariable("id") Integer id) {
        KtpResponse response = ktpService.getById(id);
        return WebResponse.<KtpResponse>builder()
                .status("success")
                .message("Berhasil mengambil data " + id)
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    public WebResponse<KtpResponse> update(@PathVariable("id") Integer id, @RequestBody @Validated KtpRequest request) {
        KtpResponse response = ktpService.update(id, request);
        return WebResponse.<KtpResponse>builder()
                .status("success")
                .message("Data KTP berhasil diperbarui")
                .data(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public WebResponse<String> delete(@PathVariable("id") Integer id) {
        ktpService.delete(id);
        return WebResponse.<String>builder()
                .status("success")
                .message("Data KTP berhasil dihapus")
                .data("OK")
                .build();
    }
}
