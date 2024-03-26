function flexUrlSubmit(url, method, formName) {
    $("#flexUrlTableForm" + formName).attr("action", "/admin/thuong-hieu/" + url);
    $("#flexUrlTableForm" + formName).attr("method", method);
    document.getElementById("flexUrlTableForm" + formName).submit();
}

function openPopupCreateOrUpdate(createOrUpdateType, chatLieuId, tenChatLieu) {
    if (createOrUpdateType === "create") {
        $('#CreateOrUpdateTitle').text("Thêm mới thương hiệu");
        $('#thuongHieuIdCreateOrUpdate').val(-1);
        $('#tenThuongHieuCreateOrUpdate').val("");
        $('#yesOptionCreateOrUpdateModalId').text("Thêm mới");
    } else if (createOrUpdateType === "update") {
        $('#CreateOrUpdateTitle').text("Cập nhật thương hiệu");
        $('#thuongHieuIdCreateOrUpdate').val(chatLieuId);
        $('#tenThuongHieuCreateOrUpdate').val(tenChatLieu);
        $('#yesOptionCreateOrUpdateModalId').text("Cập nhật");
    } else {
        return;
    }
    $('#createOrUpdateModalId').modal("show");
}

function showConfirmModalDeleteDialog(id, name) {
    $("#productName").text(name);
    $("#yesOptionDeleteModalId").attr("cl-id", id);
    $("#deleteModalId").modal("show");
}

function submitDeleteProduct() {
    var productId = $("#yesOptionDeleteModalId").attr("cl-id");
    flexUrlSubmit("delete/" + productId, "get", "ThuongHieu");
}

function submitCreateOrUpdate() {
    $('#formCreateOrUpdate').submit();
}