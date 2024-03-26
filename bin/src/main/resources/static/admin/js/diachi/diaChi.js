
$('#checkAllDiachi').click(function(event) {   
    if(this.checked) {
        // Iterate each checkbox
        $(':checkbox').each(function() {
            this.checked = true;                        
        });
    } else {
        $(':checkbox').each(function() {
            this.checked = false;                       
        });
    }
});



function xacNhanXoaDiaChi() {
	Swal.fire({
		  title: 'Xác nhận xóa địa chỉ',
		  text: "Bạn có chắc chắn muốn xóa các địa chỉ đã chọn ?",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Xóa'
		}).then((result) =>
		{
		  if (result.isConfirmed) {
			  var ids = $('tbody input[type=checkbox]:checked').map(function name() {
				return $(this).val();
			  }).get();
			  if(ids != ''){
				  if(result.value){
					  xoaDiaChi(ids);
				  }
			  }else{
				  window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach?message=error_system" ;
			  }
		  }
		})
};
function xoaDiaChi(ids) {
	$.ajax({
		url : 'http://localhost:8080/admin/api/dia-chi' ,
		type : 'DELETE' ,
		contentType : 'application/json' ,
		data : JSON.stringify(ids),
		success : function(result) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/1?message=delete_address_success" ;
		},
		error : function (error) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach?message=error_system" ;
		}
	});
}