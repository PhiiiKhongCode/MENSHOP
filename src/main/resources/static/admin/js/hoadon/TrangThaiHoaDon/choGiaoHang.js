function redirectToChoxacNhan() {
    window.location.href = "/admin/DonHang/ChoXacNhanDonHang/danhSach";
}

function redirectToChoGiaoHang() {
    window.location.href = "/admin/DonHang/ChoGiaoHang/danhSach";
}

function redirectToDangGiao() {
    window.location.href = "/admin/DonHang/DangGiaoHang/danhSach";
}

function redirectToDaGiao() {
    window.location.href = "/admin/DonHang/DaGiaoHang/danhSach";
}

function redirectToDahuy() {
    window.location.href = "/admin/DonHang/DaHuyHang/danhSach";
}

function redirectToDaHoan() {
    window.location.href = "/admin/DonHang/DaHoanHang/danhSach";
}

<!-- JS CHỜ GIAO HÀNG -> ĐANG GIAO HÀNG -->
$(document).ready(function () {
    $('.GiaoHang').click(function () {
        let hoaDonId = $(this).data('id');
        let modalId = $(this).data('target');

        // Hiển thị modal xác nhận
        $(modalId).modal('show');

        // Xử lý sự kiện khi bấm nút Đồng ý
        $(modalId + ' .btn-dong-y').click(function () {
            // Gửi yêu cầu giao hàng cho đơn vị vận chuyển bằng Ajax
            $.get('/updateGiaoHang/' + hoaDonId, function (response) {
                // Hiển thị thông báo giao hàng thành công với SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Đã chuyển hàng cho đơn vị vận chuyển thành công',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    // Tải lại trang
                    location.reload();
                });
            });

            // Đóng modal
            $(modalId).modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $(modalId + ' .btn-khong').click(function () {
            // Đóng modal
            $(modalId).modal('hide');
        });
    });
});


<!-- JS GIAO TẤT CẢ-->
$(document).ready(function () {
    $('#GiaoTatCa').click(function () {
        // Kiểm tra số lượng hàng trong bảng
        let rowCount = $('#user-table-ChoLayHang tbody tr').length;

        // Kiểm tra nếu không có dữ liệu
        if (rowCount === 0) {
            Swal.fire({
                icon: 'error',
                title: 'Lỗi',
                text: 'Không có dữ liệu',
                showConfirmButton: false,
                timer: 2000
            });
            return; // Dừng thực hiện các lệnh tiếp theo
        }

        let hoaDonId = $(this).data('id');

        // Hiển thị modal xác nhận
        $('.giaoHangTatCaModal').modal('show');

        // Xử lý sự kiện khi bấm nút Đồng ý
        $('.giaoHangTatCaModal .btn-dong-y').click(function () {
            // Gửi yêu cầu giao hàng tất cả bằng Ajax
            $.get('/updateGiaoHangAll', function (response) {
                // Hiển thị thông báo thành công
                Swal.fire({
                    icon: 'success',
                    title: 'Xác nhận chuyển tất cả đơn cho đơn vị vận chuyển',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    // Tải lại trang
                    location.reload();
                });
            });

            // Đóng modal
            $('.giaoHangTatCaModal').modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $('.giaoHangTatCaModal .btn-khong').click(function () {
            // Đóng modal
            $('.giaoHangTatCaModal').modal('hide');
        });
    });
});

<!-- JS CHỜ CHỜ GIAO HÀNG -> ĐÃ HỦY -->
$(document).ready(function () {
    $('.HuyDon').click(function () {
        let hoaDonId = $(this).data('id');
        let modalId = $(this).data('target');

        // Hiển thị modal xác nhận
        $(modalId).modal('show');

        // Xử lý sự kiện khi bấm nút Đồng ý
        $(modalId + ' .btn-dong-y').click(function () {
            // Gửi yêu cầu hủy đơn hàng bằng Ajax
            $.get('/updateHuyDon/' + hoaDonId, function (response) {
                // Hiển thị thông báo hủy thành công với SweetAlert2
                Swal.fire({
                    icon: 'error',
                    title: 'Đã hủy thành công',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    // Tải lại trang
                    location.reload();
                });
            });

            // Đóng modal
            $(modalId).modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $(modalId + ' .btn-khong').click(function () {
            // Đóng modal
            $(modalId).modal('hide');
        });
    });
});



<!-- JS TÌM HÓA ĐƠN CHỜ LẤY HÀNG-->
$(document).ready(function () {
    $("#timKiem-ChoGiaoHang").click(function () {
        let input = $("#search-input-choLayHang").val(); // Lấy giá trị từ input

        if (input) {
            // Thay đổi URL và chuyển hướng trang
            window.location.href = "/admin/DonHang/ChoGiaoHang/timKiem/" + input;
        } else {
            Swal.fire({
                icon: 'warning',
                title: 'Vui lòng nhập vào ô tìm kiếm',
                showConfirmButton: false,
                timer: 2000
            });
        }
    });
});

<!--TÌM KIẾM THEO NGÀY-->
$(document).ready(function () {
    $("#timKiemThaoNgay-ChoGiaoHang").click(function () {
        let selectedDate = $("#search-input-date-choGiaoHang").val();
        if (selectedDate) {
            let formattedDate = formatDate(selectedDate);
            window.location.href = "/admin/DonHang/ChoGiaoHang/Ngay/" + formattedDate;
        }else {
            Swal.fire({
                icon: 'warning',
                title: 'Vui lòng nhập vào ngày',
                showConfirmButton: false,
                timer: 2000
            });
        }
    });

    function formatDate(dateString) {
        let date = new Date(dateString);
        let year = date.getFullYear();
        let month = ("0" + (date.getMonth() + 1)).slice(-2);
        let day = ("0" + date.getDate()).slice(-2);
        return year + "-" + month + "-" + day;
    }
});

$(document).ready(function () {
    $("#btn-tatCaHoaDonChoGiaoHang").click(function () {
        window.location.href = "/admin/DonHang/ChoGiaoHang/danhSach";
    });
});
