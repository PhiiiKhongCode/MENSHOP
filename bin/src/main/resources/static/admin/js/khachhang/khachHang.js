

$('#timKiemSoDienThoai').click(function(){
	var soDienThoai = $('#soDienThoai').val();
	if(soDienThoai == ""){
		alert("Vui lòng nhập số điện thoại");
	}else{
		window.location.href="http://localhost:8080/admin/khach-hang/danh-sach/tim-kiem/1?soDienThoai="+soDienThoai;
	}
	
	
});


function fun()
{
    if(document.getElementById("trangThaiSelect").value!="")
        document.getElementById("loc").disabled=false;
    else
    document.getElementById("loc").disabled=true;
}


$('#trangThaiSelect').on('change',function (e) {
				var conceptName = $('#trangThaiSelect').find(":selected").val();
				$('#trangThai').val(conceptName);
				
});


$('#checkAll').click(function(event) {   
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





function xacNhanChuyenDoiTrangThaiThanhHoatDong() {
	Swal.fire({
		  title: 'Xác nhận chuyển đổi',
		  text: "Bạn có chắc chắn muốn chuyển đổi các trạng thái ?",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Chuyển đổi'
		}).then((result) =>
		{
		  if (result.isConfirmed) {
			  var ids = $('tbody input[type=checkbox]:checked').map(function name() {
				return $(this).val();
			  }).get();
			  if(ids != ''){
				  if(result.value){
					  capNhatTrangThaiThanhHoatDong(ids);
				  }
			  }else{
				  window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/1?message=change_error" ;
			  }
		  }
		})
};
function capNhatTrangThaiThanhHoatDong(ids) {
	$.ajax({
		url : 'http://localhost:8080/admin/api/khach-hang/trang-thai-dang-hoat-dong',
		type : 'DELETE' ,
		contentType : 'application/json' ,
		data : JSON.stringify(ids),
		success : function(result) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/1?message=change_success" ;
		},
		error : function (error) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/1?message=change_error" ;
		}
	});
}




function xacNhanChuyenDoiTrangThaiThanhKhongHoatDong() {
	Swal.fire({
		  title: 'Xác nhận chuyển đổi',
		  text: "Bạn có chắc chắn muốn chuyển đổi các trạng thái ?",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Chuyển đổi'
		}).then((result) =>
		{
		  if (result.isConfirmed) {
			  var ids = $('tbody input[type=checkbox]:checked').map(function name() {
				return $(this).val();
			  }).get();
			  if(ids != ''){
				  if(result.value){
					  capNhatTrangThaiThanhKhongHoatDong(ids);
				  }
			  }else{
				  window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/1?message=change_error" ;
			  }
		  }
		})
};

function capNhatTrangThaiThanhKhongHoatDong(ids) {
	$.ajax({
		url : 'http://localhost:8080/admin/api/khach-hang/trang-thai-khong-hoat-dong',
		type : 'DELETE' ,
		contentType : 'application/json' ,
		data : JSON.stringify(ids),
		success : function(result) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/1?message=change_success" ;
		},
		error : function (error) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/1?message=change_error" ;
		}
	});
}
$('#formSubmitKhachHang').validate({
	rules: {
		email : "required" ,
		matKhau : "required",
		hoTen : "required",
		soDienThoai : "required"
	},
	messages: {
		email : "Vui lòng nhập email ! ",
		matKhau : "Vui lòng nhập mật khẩu !",
		hoTen : "Vui lòng nhập họ tên !",
		soDienThoai : "Vui lòng nhập số điện thoại !"
	}
});

$('#btnCapNhatHoacThemMoiKhachHang').click(function (e) {
	//Ko co cai nay la no se submit vao url no dang dung
	if($('#formSubmitKhachHang').valid()){
		e.preventDefault();
		var data = {};
		var formData = $('#formSubmitKhachHang').serializeArray();
		$.each(formData,function(i,v){
			data[""+v.name+""] = v.value;
		});
		var id  = $('#khachHangId').val();
		if(id == ""){
			themKhachHang(data);
		}else{
			capNhatKhachHang(data);
		}
	}
	
	
});



function themKhachHang(data) {
	$.ajax({
		url : 'http://localhost:8080/admin/api/khach-hang' ,
		type : 'POST' ,
		contentType : 'application/json' ,
		data : JSON.stringify(data) ,
		dataType : 'json' ,
		success : function(result) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/chinh-sua?id="+result.id+"&message=create_success" ;
		},
		error : function (error) {
			console.log(error);
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/chinh-sua?message=duplicate_email&email="+data.email ;
		}
	});
};
function capNhatKhachHang(data) {
	$.ajax({
		url : 'http://localhost:8080/admin/api/khach-hang' ,
		type : 'PUT' ,
		contentType : 'application/json' ,
		data : JSON.stringify(data) ,
		dataType : 'json' ,
		success : function(result) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/chinh-sua?id="+result.id+"&message=update_success"  ;
		},
		error : function (error) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/chinh-sua?id="+data.id+"&message=update_duplicate_email&email="+data.email ;
		}
	});
}


$('#btnThemMoiDiaChi').click(function (e) {
	//Ko co cai nay la no se submit vao url no dang dung
	e.preventDefault();
	var data = {};
	var formData = $('#formSubmitDiaChi').serializeArray();
	$.each(formData,function(i,v){
		data[""+v.name+""] = v.value;
	});
	themDiaChi(data);
});
function themDiaChi(data) {
	$.ajax({
		url : 'http://localhost:8080/admin/api/dia-chi' ,
		type : 'POST' ,
		contentType : 'application/json' ,
		data : JSON.stringify(data) ,
		dataType : 'json' ,
		success : function(result) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/chinh-sua?id="+result.khachHangId+"&message=create_address_success" ;
		},
		error : function (error) {
			window.location.href = "http://localhost:8080/admin/khach-hang/danh-sach/1?message=error_system" ;
		}
	});
};

