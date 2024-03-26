

$('#checkAll').click(function (event) {
    if (this.checked) {
        $('.form-checkbox').each(function () {
            this.checked = true;
        });
    } else {
        $('.form-checkbox').each(function () {
            this.checked = false;
        });
    }
    tinhTienHoaDon();
});


$('.form-checkbox').click(function (event) {
    tinhTienHoaDon();
});

function formatNumber(nStr, decSeperate, groupSeperate) {
    nStr += '';
    x = nStr.split(decSeperate);
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + groupSeperate + '$2');
    }
    return x1 + x2;
}

function tinhTienHoaDon() {
    //Lấy id của giỏ hàng thì cứ gõ lại cái lệnh var này là được
    var gioHangChiTietIds = $('tbody input[type=checkbox]:checked').map(function name() {
        return $(this).val();
    }).get();
    console.log(gioHangChiTietIds)

    $.ajax({
        url: 'http://localhost:8080//customer/api/hoa-don/tinh-tien',
        type: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify(gioHangChiTietIds),
        success: function (result) {
            //Tổng tiền khi chọn combobox trả về ở đây muốn lấy thì var tongTien = $('#tongTienGioHang').val();
            $('#tongTienGioHang').text(formatNumber(result, '.', ','));
        },
        error: function (error) {
            console.log(error);
        }
    });
};

