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


<!-- JS TÌM HÓA ĐƠN ĐÃ HOÀN-->
$(document).ready(function () {
    $("#timKiem-DaHoanHang").click(function () {
        let input = $("#search-input-daHoan").val(); // Lấy giá trị từ input

        if (input) {
            // Thay đổi URL và chuyển hướng trang
            window.location.href = "/admin/DonHang/DaHoanHang/timKiem/" + input;
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

$(document).ready(function () {
    $("#timKiemNgay-DaHoanHang").click(function () {
        let selectedDate = $("#search-input-date-DaHoan").val();
        if (selectedDate) {
            let formattedDate = formatDate(selectedDate);
            window.location.href = "/admin/DonHang/DaHoanHang/Ngay/" + formattedDate;
        } else {
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
    $("#btn-tatCaHoaDonDaHoan").click(function () {
        window.location.href = "/admin/DonHang/DaHoanHang/danhSach";
    });
});


<!-- JS xác nhận đơn hàng hoàn trả -->
$(document).ready(function () {
    $('.xacNhanHoanTra').click(function () {
        const hoaDonId = $(this).data('id');

        // Lưu trạng thái tab hiện tại vào sessionStorage
        const activeTab = $('.nav-link.active').attr('href');
        sessionStorage.setItem('activeTab', activeTab);

        // Hiển thị modal xác nhận
        $('.giaoHangTCModal').modal('show');

        // Xử lý sự kiện khi bấm nút Đồng ý
        $('.giaoHangTCModal .btn-dong-y').click(function () {
            // Gửi yêu cầu xác nhận đơn hàng bằng Ajax
            $.get('/xacNhanHoanTraHang/' + hoaDonId, function (response) {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                Swal.fire({
                    icon: 'success',
                    title: 'Xác nhận đơn hoàn trả thành công!',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/DonHang/DangGiaoHang/danhSach";
                });
            });

            // Đóng modal
            $('.giaoHangTCModal').modal('hide');
        });

        // Xử lý sự kiện khi bấm nút Không
        $('.giaoHangTCModal .btn-khong').click(function () {
            // Gửi yêu cầu xác nhận đơn hàng bằng Ajax
            $.get('/xacNhanKhongDongYHoanTraHang/' + hoaDonId, function (response) {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                Swal.fire({
                    icon: 'success',
                    title: 'Bạn đã huỷ yêu cầu hoàn trả của khách hàng',
                    showConfirmButton: false,
                    timer: 2000
                }).then(function () {
                    // Lưu trạng thái đã xác nhận vào sessionStorage
                    sessionStorage.setItem('isConfirmed', true);

                    window.location.href = "/admin/DonHang/DangGiaoHang/danhSach";
                });
            });
            // Đóng modal
            $('.giaoHangTCModal').modal('hide');
        });
    });
});

