VirtualSelect.init({
  ele: "select",
});

function showConfirmModalDeleteDialog(id, name) {
  $("#productName").text(name);
  $("#yesOptionDeleteModalId").attr("href", "/admin/product/delete/" + id);
  $("#deleteModalId").modal("show");
}

function changeIsShowProductDetail(url, idHTML, idItem, isCheck) {
  $("#yesOptionChangeIsShow").attr(
    "href",
    "/admin/product/" + url + "/" + idItem + "/" + !isCheck
  );
  $("#isShowModalId").modal("show");
  $("#notChangeIsShow").attr("onclick", notChangeIsShow(idHTML, isCheck));
}

function notChangeIsShow(idHTML, isCheck) {
  document.getElementById(idHTML).checked = isCheck;
}

function showConfirmModalDialogDeleteAllbyId() {
  $("#confirmationDeleteIds").modal("show");
}

function deleteAll() {
  document.getElementById("formDeleteAllByIds").submit();
}

function toggle(source) {
  checkboxes = document.getElementsByName("productIds");
  for (var i = 0, n = checkboxes.length; i < n; i++) {
    checkboxes[i].checked = source.checked;
  }
}

// function openBoxCreateImgs(id) {
//   if (document.getElementById(id).isCheck === true) {
//   }
// }

$("#multiple-select-field").select2({
  theme: "bootstrap-5",
  width: $(this).data("width")
    ? $(this).data("width")
    : $(this).hasClass("w-100")
    ? "100%"
    : "style",
  placeholder: $(this).data("placeholder"),
  closeOnSelect: false,
});
