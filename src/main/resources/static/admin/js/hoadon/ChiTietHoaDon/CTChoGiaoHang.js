<!-- JS CHỜ GIAO HÀNG -> ĐANG GIAO HÀNG -->
$(document).ready(function () {
    $('.GiaoHang').click(function () {
        const hoaDonId = $(this).data('id');

        // Hiển thị modal xác nhận
        $('.giaoHangModal').modal('show');

        // Xử lý sự kiện khi bấm nút Đồng ý
        $('.giaoHangModal .btn-dong-y').click(function () {
            // Gửi yêu cầu xác nhận đơn hàng bằng Ajax
            $.get('/updateGiaoHang/' + hoaDonId, function (response) {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                Swal.fire({
                    icon: 'success',
                    title: 'Đã chuyển hàng cho đơn vị vận chuyển thành công',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/DonHang/ChoGiaoHang/danhSach";
                });
            });

            // Đóng modal
            $('.giaoHangModal').modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $('.giaoHangModal .btn-khong').click(function () {
            // Đóng modal
            $('.giaoHangModal').modal('hide');
        });
    });
});

<!-- JS CHỜ CHỜ GIAO HÀNG -> ĐÃ HỦY -->
$(document).ready(function () {
    $('.HuyDon').click(function () {
        const hoaDonId = $(this).data('id');

        $('.huyModal').modal('show');

        $('.huyModal .btn-dong-y').click(function () {
            $.get('/updateHuyDon/' + hoaDonId, function (response) {
                Swal.fire({
                    icon: 'error', title: 'Đã hủy thành công', showConfirmButton: false, timer: 2000
                }).then(function () {
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/DonHang/ChoXacNhanDonHang/danhSach";
                });
            });

            // Đóng modal
            $('.huyModal').modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $('.huyModal .btn-khong').click(function () {
            // Đóng modal
            $('.huyModal').modal('hide');
        });
    });
});

function quayLai() {
    window.location.href = "/admin/DonHang/ChoGiaoHang/danhSach";
}

$(document).ready(function () {
    // Xử lý sự kiện khi trang được tải lần đầu
    $('.quantity-input').each(function () {
        $(this).data('initial-value', $(this).val());
    });

    $('.quantity-input').on('input', function () {
        const saveBtn = $(this).siblings('.save-btn');
        const cancelBtn = $(this).siblings('.cancel-btn');
        const soLuongSPCT = $(this).siblings('.soLuongSPCT');
        soLuongSPCT.show();
        saveBtn.show();
        cancelBtn.show();

        let soLuongCapNhatTable = parseInt($(this).val());
        let initialValue = parseInt($(this).data('initial-value'));
        let soLuongHienCoBanDau = parseInt($(this).data('so-luong-hien-co'));

        // Lấy số lượng hiện có từ phần tử span
        let soLuongHienCoTable = parseInt($('.soLuongHienCoTable').text());

        if (soLuongCapNhatTable > (initialValue + soLuongHienCoBanDau)) {
            Swal.fire({
                icon: 'error',
                title: 'Số lượng nhập vào vượt quá số lượng hiện có của sản phẩm.',
                showConfirmButton: false,
                timer: 2000
            });

            // Đặt lại giá trị trong ô input là số lượng hiện có
            $(this).val(initialValue);

            // Cập nhật giá trị số lượng nhập vào trong log
            soLuongCapNhatTable = soLuongHienCoTable;
        } else if (soLuongCapNhatTable < 1) {
            // Nếu số lượng nhập vào không phải là một số hợp lệ hoặc nhỏ hơn 1, đặt lại giá trị trong ô input là 1
            Swal.fire({
                icon: 'error', title: 'Số lượng nhập vào không được nhỏ hơn 0', showConfirmButton: false, timer: 2000
            });

            $(this).val(initialValue);
        }
    });

    // Xử lý sự kiện khi nhấn nút lưu
    $('.save-btn').on('click', function () {
        const hoaDonCTId = $(this).attr('data-id');
        const quantityInput = $(this).siblings('.quantity-input');
        const quantity = parseInt(quantityInput.val());

        // Gửi Ajax request để lưu thay đổi số lượng
        $.ajax({
            url: '/admin/chinhSuaHoaDon/update-SoLuong/' + hoaDonCTId,
            type: 'POST',
            data: {quantity: quantity},
            success: function (response) {
                // Xử lý thành công
                Swal.fire({
                    icon: 'success', title: 'Cập nhật số lượng thành công', showConfirmButton: false, timer: 2000
                }).then(function () {
                    sessionStorage.setItem('isConfirmed', true);
                    location.reload();
                });
            },
            error: function (error) {
                // Xử lý lỗi
                console.log('Lỗi khi lưu số lượng');
            }
        });
    });

    // Xử lý sự kiện khi nhấn nút hủy
    $('.cancel-btn').on('click', function () {
        const hoaDonCTId = $(this).attr('data-id');
        const quantityInput = $(this).siblings('.quantity-input');
        const saveBtn = $(this).siblings('.save-btn');
        const cancelBtn = $(this);
        const soLuongSPCT = $(this).siblings('.soLuongSPCT');

        // Trả lại giá trị ban đầu của ô input
        const initialValue = quantityInput.data('initial-value');
        quantityInput.val(initialValue);
        soLuongSPCT.hide();
        saveBtn.hide();
        cancelBtn.hide();
    });
});

$(document).ready(function () {
    $(".inHoaDonChiTiet").click(function () {
        $('.inHoaDonModal').modal('show');
        $('.inHoaDonModal .btn-dong-y').click(function () {
            let hoaDonID = $("#idChiTietHoaDon").val();
            printHoaDon(hoaDonID);
            $('.inHoaDonModal').modal('hide');
        });

        $('.inHoaDonModal .btn-khong').click(function () {
            $('.inHoaDonModal').modal('hide');
        });
    });
});

function printHoaDon(hoaDonId) {
    // Tạo tên file PDF mới bằng UUID
    let pdfFileName = generateUuid() + '.pdf';

    // Gọi API để in hóa đơn và lưu file PDF vào thư mục dự án
    fetch('/in-hoa-don-don-dat-hang/' + hoaDonId + '?pdfFileName=' + pdfFileName)
        .then(response => response.blob())
        .then(pdfBlob => {
            let pdfUrl = URL.createObjectURL(pdfBlob);
            let newWindow = window.open(pdfUrl, '_blank'); // Mở trang mới chứa file PDF
            if (newWindow) {
                newWindow.document.title = 'Hóa đơn của bạn';
            } else {
                alert('Vui lòng cho phép trình duyệt mở popup để xem và lưu hóa đơn.');
            }
        })
        .catch(error => console.error('Lỗi khi tạo file PDF:', error));
}

function generateUuid() {
    let uuid = '', i, random;
    for (i = 0; i < 32; i++) {
        random = Math.random() * 36 | 0; // Thay đổi thành toString(36)
        if (i === 8 || i === 12 || i === 16 || i === 20) {
            uuid += '-';
        }
        uuid += (i === 12 ? 4 : (i === 16 ? (random & 3 | 8) : random))
            .toString(36);
    }
    return uuid;
}