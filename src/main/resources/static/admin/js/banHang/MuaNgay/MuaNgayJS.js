$(document).ready(function () {
    let tenKichCo = "";
    let mauSacId = "";
    let soLuongInput = $(".daucatmoi").val();

    $(".chonKichCo").click(function () {
        tenKichCo = $(this).text().trim();

        $(".chonKichCo").removeClass("active");
        $(this).addClass("active");

        const sanPhamId = $(".product__details__option").data("id");

        if (mauSacId != "") {
            getSoLuongSanPhamChiTiet(tenKichCo, mauSacId, sanPhamId);
        }

        if (soLuongInput > 5 && soLuongInput >= soLuongHienCoCus) {
            soLuongInput = soLuongHienCoCus;
        }
    });

    $(".chonMauSac").click(function () {
        mauSacId = $(this).find("input[type=radio]").val();
        const sanPhamId = $(".product__details__option").data("id");

        if (tenKichCo != "") {
            getSoLuongSanPhamChiTiet(tenKichCo, mauSacId, sanPhamId);
        }

        const soLuongHienCoCus = $(".soLuongHienCoCus").val();
        if (soLuongInput > 5 && soLuongInput > soLuongHienCoCus) {
            soLuongInput = soLuongHienCoCus;
        }
    });

    $(".daucatmoi").on("input", function () {
        let soLuongInput = parseInt($(this).val());
        let sanPhamId = $(".product__details__option").data("id");
        let soLuongHienCo = parseInt($("#soLuongHienCoCus" + sanPhamId).text());

        if (soLuongInput > 5 || soLuongInput > soLuongHienCo) {
            Swal.fire({
                icon: "error",
                title: "Nếu bạn muốn mua số lượng lớn.Vui lòng liên hệ với SĐT: 0363439953",
                showConfirmButton: false,
                timer: 2000,
            });
            if (soLuongHienCo > 5) {
                $(this).val(5);
                soLuongInput = 5;
            } else {
                $(this).val(soLuongHienCo);
                soLuongInput = soLuongHienCo;
            }
        } else if (soLuongInput < 1) {
            Swal.fire({
                icon: "error",
                title: "Số lượng nhập vào không được nhỏ hơn 0",
                showConfirmButton: false,
                timer: 2000,
            });
            $(this).val(1);
        }
    });
});

function getSoLuongSanPhamChiTiet(tenKichCo, mauSacId, sanPhamId) {
    $.ajax({
        type: "GET",
        url: "/customer/SoLuongSanPhamChiTiet",
        data: {
            tenKichCo: tenKichCo,
            mauSacId: mauSacId,
            sanPhamId: sanPhamId,
        },
        success: function (response) {
            let soLuongSanPhamChiTiet = response.soLuongSanPhamChiTiet;

            if (soLuongSanPhamChiTiet == null || isNaN(soLuongSanPhamChiTiet)) {
                soLuongSanPhamChiTiet = 0;
            }

            $("#soLuongHienCoCus" + sanPhamId).text(soLuongSanPhamChiTiet);
        },
        error: function () {
            alert("Đã xảy ra lỗi khi gửi yêu cầu đến server.");
        },
    });
}

$(document).ready(function () {
    $("#muaNgaySanPham").click(function () {
        const sanPhamID = $(this).data("id");
        const mauSacId = $("input[name='mauSacId']:checked").val();
        const kichCoId = $("input[name='kichCoId']:checked").val();
        const soLuong = $(".daucatmoi").val();

        if (soLuong <= 0) {
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Vui lòng nhập lại số lượng",
            });
            return;
        }

        if (mauSacId == null) {
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Bạn chưa chọn màu sắc",
            });
            return;
        }
        if (kichCoId == null) {
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Bạn chưa chọn kích cỡ",
            });
            return;
        }
        if (soLuong == null) {
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Bạn chưa chọn số lượng",
            });
            return;
        }

        DatHangNgay(sanPhamID, mauSacId, kichCoId, soLuong);
    });
});

function DatHangNgay(sanPhamID, mauSacId, kichCoId, soLuong) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/MuaNgay/checkout",
        data: {
            sanPhamId: sanPhamID,
            mauSacId: mauSacId,
            kichCoId: kichCoId,
            soLuong: soLuong
        },
        success: function (response) {
            Swal.fire({
                icon: "success",
                title: "Thành công",
                text: "Đang chuyển hướng đến trang đặt hàng",
                showConfirmButton: false,
                timer: 2000
            }).then(function () {
                // Lưu trạng thái đã xác nhận vào sessionStorage
                sessionStorage.setItem('isConfirmed', true);

                // Tải lại trang
                window.location.href = "/MuaNgaySanPham/checkout/" + response;
            });
        },
        error: function (error) {
            Swal.fire({
                icon: "error",
                title: "Lỗi",
                text: "Thêm sản phẩm không thành công",
            });
            console.log(error.responseText);
        },
    });
}

