$(document).ready(function () {
    let currentHoaDonId;

    $('.TraHangCTCus').click(function () {
        currentHoaDonId = $(this).data('id');
        $('.traHangModalCustomer[data-id="' + currentHoaDonId + '"]').modal('show');
    });

    $('.traHangModalCustomer .btn-dong-y').click(function () {
        let lyDo = $("#lyDo").val();
        console.log("ly do tra hang: "+lyDo)
        $.get('/updateGiaoHangHoan/' + currentHoaDonId+"?lyDo="+lyDo,
            function (response) {
            Swal.fire({
                icon: 'success', title: 'Đã gửi yêu cầu hoàn trả vui lòng chờ cửa hàng phản hồi!', showConfirmButton: false, timer: 2000
            }).then(function () {
                sessionStorage.setItem('isConfirmed', true);
                location.reload();
            });
        });

        $('.traHangModalCustomer').modal('hide');
    });

    $('.traHangModalCustomer .btn-khong').click(function () {
        $('.traHangModalCustomer').modal('hide');
    });
});



