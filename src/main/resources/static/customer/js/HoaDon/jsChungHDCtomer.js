function redirectToChoxacNhanCustomer() {
    window.location.href = "/customer/DonHang/ChoXacNhan";
}

function redirectToChoGiaoHangCustomer() {
    window.location.href = "/customer/DonHang/ChoGiaoHang";
}

function redirectToDangGiaoCustomer() {
    window.location.href = "/customer/DonHang/DangGiaoHang";
}

function redirectToDaGiaoCustomer() {
    window.location.href = "/customer/DonHang/DaGiaoHang";
}

function redirectToDahuyCustomer() {
    window.location.href = "/customer/DonHang/DaHuy";
}

function redicrectToTraHangCustomer(){
    window.location.href = "/customer/DonHang/TraHang";
}

$(document).ready(function () {
    let currentHoaDonId;

    $('.TraHangCus').click(function () {
        currentHoaDonId = $(this).data('id');
        $('.traHangModalCustomer[data-id="' + currentHoaDonId + '"]').modal('show');
    });
});