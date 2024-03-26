function flexUrlSubmit(url, method, formName) {
    $("#flexUrlTableForm" + formName).attr("action", "/admin/hoa-tiet/" + url);
    $("#flexUrlTableForm" + formName).attr("method", method);
    document.getElementById("flexUrlTableForm" + formName).submit();
}

function openPopupCreateOrUpdate(createOrUpdateType, chatLieuId, tenChatLieu) {
    if (createOrUpdateType === "create") {
        $('#CreateOrUpdateTitle').text("Thêm mới họa tiết");
        $('#hoaTietIdCreateOrUpdate').val(-1);
        $('#tenHoaTietCreateOrUpdate').val("");
        $('#yesOptionCreateOrUpdateModalId').text("Thêm mới");
    } else if (createOrUpdateType === "update") {
        $('#CreateOrUpdateTitle').text("Cập nhật họa tiết");
        $('#hoaTietIdCreateOrUpdate').val(chatLieuId);
        $('#tenHoaTietCreateOrUpdate').val(tenChatLieu);
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
    var kcId = $("#yesOptionDeleteModalId").attr("cl-id");
    flexUrlSubmit("delete/" + kcId, "get", "HoaTiet");
}

function submitCreateOrUpdate() {
    $('#formCreateOrUpdate').submit();
}