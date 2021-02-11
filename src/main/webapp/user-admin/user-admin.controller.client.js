var $tableRows
var $createBtn
var $updateBtn
var $refreshBtn

var $usernameFld
var $passwordFld
var $firstNameFld
var $lastNameFld
var $roleFld

var userServices = new AdminUserServiceClient()
var users = []
var selectedUser = null


function renderUsers(users) {
    $tableRows.empty()
    for (var i = 0;  i < users.length; i++) {
        var user = users[i]
        $tableRows.append(`
        <tr>
            <td>${user.username}</td>
            <td></td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.role}</td>
            <td>
                <i id="${i}" class="fas fa-times btn wbdv-delete-btn" title="delete this user"></i>
                <i id="${i}" class="fas fa-pencil-alt btn wbdv-select-btn" title="select this user"></i>
            </td>
        </tr>
        `
        )
    }
    $(".wbdv-delete-btn").click(deleteUser)
    $(".wbdv-select-btn").click(selectUser)
}

function createUser() {
    var newUser = {
        username: $usernameFld.val(),
        password: $passwordFld.val(),
        firstname: $firstNameFld.val(),
        lastname: $lastNameFld.val(),
        role: $roleFld.val()
    }
    userServices.createUser(newUser).then(function (acturalUser) {
        users.push(acturalUser)
        renderUsers(users)
    })
}

function deleteUser(event) {
    var button = $(event.target)
    var index = button.attr("id")
    var id = users[index]._id
    userServices.deleteUser(id).then(function (status) {
        users.splice(index, 1)
        renderUsers(users)
    })
}

function selectUser(event) {
    var button = $(event.target)
    var index = button.attr("id")
    var id = users[index]._id
    selectedUser = users.find(user => user._id == id)
    $usernameFld.val(selectedUser.username)
    $passwordFld.val(selectedUser.password)
    $firstNameFld.val(selectedUser.firstname)
    $lastNameFld.val(selectedUser.lastname)
    $roleFld.val(selectedUser.role)
}

function updateUser() {
    selectedUser.username = $usernameFld.val()
    selectedUser.password = $passwordFld.val()
    selectedUser.firstname = $firstNameFld.val()
    selectedUser.lastname = $lastNameFld.val()
    selectedUser.role = $roleFld.val()
    userServices.updateUser(selectedUser._id, selectedUser).then(status => {
        var index = users.findIndex(user => user._id == selectedUser._id)
        users[index] = selectedUser
        renderUsers(users)
    })
}

function findAllUsers() {
    userServices.findAllUsers().then(function(actualUsers) {
        users = actualUsers
        renderUsers(users)
    })
}

function main() {
    $tableRows = jQuery("#table-rows")

    $createBtn = $(".wbdv-create-btn")
    $updateBtn = $(".wbdv-update-btn")
    $refreshBtn = $(".wbdv-search-btn")

    $createBtn.click(createUser)
    $updateBtn.click(updateUser)
    $refreshBtn.click(findAllUsers)

    $usernameFld = $("#wbdv-username-fld")
    $passwordFld = $("#wbdv-password-fld")
    $firstNameFld = $("#wbdv-firstname-fld")
    $lastNameFld = $("#wbdv-lastname-fld")
    $roleFld = $("#wbdv-role-fld")

    findAllUsers()
}

$(main)

