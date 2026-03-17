$(document).ready(function() {
    const API_URL = '/ktp';

    // Initial load
    loadKtpData();

    // Setup Form Submission
    $('#ktpForm').on('submit', function(e) {
        e.preventDefault();
        saveKtp();
    });

    // Reset Form button
    $('#btnCancel').on('click', function() {
        resetForm();
    });

    function loadKtpData() {
        $.ajax({
            url: API_URL,
            type: 'GET',
            success: function(response) {
                if(response.status === 'success' && response.data) {
                    renderTable(response.data);
                } else {
                    showNotification('Gagal memuat data', 'error');
                }
            },
            error: function() {
                showNotification('Terjadi kesalahan saat memuat data dari server', 'error');
            }
        });
    }

    function renderTable(data) {
        const tbody = $('#ktpTable tbody');
        tbody.empty();

        if (data.length === 0) {
            $('#emptyData').removeClass('hidden');
            $('#ktpTable').addClass('hidden');
            return;
        }

        $('#emptyData').addClass('hidden');
        $('#ktpTable').removeClass('hidden');

        data.forEach(function(ktp) {
            const tr = `
                <tr>
                    <td>${ktp.nomorKtp}</td>
                    <td>${ktp.namaLengkap}</td>
                    <td>${ktp.jenisKelamin}</td>
                    <td>${formatDate(ktp.tanggalLahir)}</td>
                    <td>${ktp.alamat}</td>
                    <td>
                        <div class="action-buttons">
                            <button class="btn-edit" onclick="editKtp(${ktp.id})">Edit</button>
                            <button class="btn-delete" onclick="deleteKtp(${ktp.id}, '${ktp.namaLengkap}')">Hapus</button>
                        </div>
                    </td>
                </tr>
            `;
            tbody.append(tr);
        });
    }

    function saveKtp() {
        const id = $('#ktpId').val();
        const requestData = {
            nomorKtp: $('#nomorKtp').val(),
            namaLengkap: $('#namaLengkap').val(),
            alamat: $('#alamat').val(),
            tanggalLahir: $('#tanggalLahir').val(),
            jenisKelamin: $('#jenisKelamin').val()
        };

        const isUpdate = id ? true : false;
        const url = isUpdate ? `${API_URL}/${id}` : API_URL;
        const type = isUpdate ? 'PUT' : 'POST';

        $.ajax({
            url: url,
            type: type,
            contentType: 'application/json',
            data: JSON.stringify(requestData),
            success: function(response) {
                if (response.status === 'success') {
                    showNotification(isUpdate ? 'Data berhasil diperbarui' : 'Data berhasil ditambahkan', 'success');
                    resetForm();
                    loadKtpData();
                } else {
                    showNotification(response.message || 'Gagal menyimpan data', 'error');
                }
            },
            error: function(xhr) {
                let errorMsg = 'Terjadi kesalahan saat menyimpan data';
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMsg = xhr.responseJSON.message;
                }
                showNotification(errorMsg, 'error');
            }
        });
    }

    // Assign to window for onclick from HTML String
    window.editKtp = function(id) {
        $.ajax({
            url: `${API_URL}/${id}`,
            type: 'GET',
            success: function(response) {
                if (response.status === 'success' && response.data) {
                    const ktp = response.data;
                    $('#ktpId').val(ktp.id);
                    $('#nomorKtp').val(ktp.nomorKtp);
                    $('#namaLengkap').val(ktp.namaLengkap);
                    $('#alamat').val(ktp.alamat);
                    $('#tanggalLahir').val(ktp.tanggalLahir);
                    $('#jenisKelamin').val(ktp.jenisKelamin);
                    
                    $('#btnSave').text('Update Data');
                    $('#btnCancel').removeClass('hidden');
                    
                    // Scroll to form
                    $('html, body').animate({ scrollTop: 0 }, 'fast');
                }
            },
            error: function() {
                showNotification('Data tidak ditemukan', 'error');
            }
        });
    };

    window.deleteKtp = function(id, name) {
        if (confirm(`Apakah Anda yakin ingin menghapus data KTP untuk: ${name}?`)) {
            $.ajax({
                url: `${API_URL}/${id}`,
                type: 'DELETE',
                success: function(response) {
                    if (response.status === 'success') {
                        showNotification('Data berhasil dihapus', 'success');
                        loadKtpData();
                        
                        // If current being edited is deleted, reset form
                        if ($('#ktpId').val() == id) {
                            resetForm();
                        }
                    }
                },
                error: function(xhr) {
                    let errorMsg = 'Gagal menghapus data';
                    if (xhr.responseJSON && xhr.responseJSON.message) {
                        errorMsg = xhr.responseJSON.message;
                    }
                    showNotification(errorMsg, 'error');
                }
            });
        }
    };

    function resetForm() {
        $('#ktpForm')[0].reset();
        $('#ktpId').val('');
        $('#btnSave').text('Simpan Data');
        $('#btnCancel').addClass('hidden');
    }

    function showNotification(message, type) {
        const notif = $('#notification');
        notif.removeClass('hidden notify-success notify-error');
        notif.addClass(type === 'success' ? 'notify-success' : 'notify-error');
        notif.text(message);

        // Auto hide after 5 seconds
        setTimeout(function() {
            notif.addClass('hidden');
        }, 5000);
    }

    function formatDate(dateString) {
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(dateString).toLocaleDateString('id-ID', options);
    }
});
