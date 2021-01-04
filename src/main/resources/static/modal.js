$(document).ready(function(){
    $(".edit").click(function(){
        let user = $(this).val();
        let arr = user.split(" ");
        let user_id = arr[0];
        let user_login = arr[1];
        let user_name = arr[2];
        let user_pass = arr[3];
        let user_email = arr[4];
        let user_role = arr[5];
        $("#editID").val(user_id);
        $("#editLogin").val(user_login);
        $("#editName").val(user_name);
        $("#editPass").val(user_pass);
        $("#editEmail").val(user_email);
        $("#editRole").val(user_role);
        $("#editModal").modal('show');
        $(".editSubmit").click(function () {
            let user_id2 = document.getElementById("editID").value;
            let user_login2 = document.getElementById("editLogin").value;
            let user_name2 = document.getElementById("editName").value;
            let user_pass2 = document.getElementById("editPass").value;
            let user_email2 = document.getElementById("editEmail").value;
            let user_role2 = document.getElementById("editRole").value;
            while (user_pass2.includes("/")) {
                user_pass2 = user_pass2.replace("/", "|");
            }
            $.ajax({
                type: "POST",
                url: "/admin/edit/" + user_id2 + "+" + user_login2 + "+" + user_name2 + "+" + user_pass2 + "+" + user_email2 + "+" + user_role2,
                success: function() {
                    window.location.href = "/admin";
                }
            });
        });
    });
});

$(document).ready(function(){
    $(".delete").click(function(){
        let user = $(this).val();
        let arr = user.split(" ");
        let user_id = arr[0];
        let user_login = arr[1];
        let user_name = arr[2];
        let user_pass = arr[3];
        let user_email = arr[4];
        let user_role = arr[5];
        $("#deleteID").val(user_id);
        $("#deleteLogin").val(user_login);
        $("#deleteName").val(user_name);
        $("#deletePass").val(user_pass);
        $("#deleteEmail").val(user_email);
        $("#deleteRole").val(user_role);
        $("#deleteModal").modal('show');
        $(".deleteSubmit").click(function () {
            $.ajax({
                type: "POST",
                url: "/admin/delete/" + user_id,
                success: function() {
                    window.location.href = "/admin";
                }
            });
        });
    });
});
