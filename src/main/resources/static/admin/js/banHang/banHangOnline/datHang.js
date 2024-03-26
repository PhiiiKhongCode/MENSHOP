function taoHoaDon() {
    // Lấy tất cả các ô checkbox có class "form-checkbox" (lớp dùng cho các ô checkbox giỏ hàng)
    const checkboxes = document.querySelectorAll('.form-checkbox');

    // Kiểm tra xem có checkbox nào được chọn hay không
    let hasCheckedItems = false;
    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            hasCheckedItems = true;
            return;
        }
    });

    if (!hasCheckedItems) {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi',
            text: 'Vui lòng chọn sản phẩm',
            showConfirmButton: false,
            timer: 2000
        });
        return;
    }

    const selectedCartItemIds = [];
    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            selectedCartItemIds.push(checkbox.value);
        }
    });

    fetch('/customer/gio-hang-chi-tiet/tao-hoa-don', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(selectedCartItemIds),
    })
        .then(response => {
            console.log('response: ',response)
            var parts = response.url.split("/");
            var erro_validate = parts[parts.length - 1];
            if(erro_validate == 'erro_validate'){
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi',
                    text: 'Số lượng sản phẩm không hợp lệ! (Tối đa 5 sản phẩm, Mỗi sản phẩm không quá 5 chiếc, Liên hệ: 0971265513)',
                    showConfirmButton: false,
                    timer: 5000
                });
                return;
            }else {
                window.location.href = response.url;
            }
        })
        .catch(error => {
            console.error('Lỗi lưu chi tiết hóa đơn:', error);
        });
}
