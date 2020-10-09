var role = {
    systemAdministrator: 1,
    companyAdministrator: 2,
    user: 3
};

var userStatus = {
    active: 1,
    onHold: 2,
    inactive: 3
};

var _listData = JSON
    .parse(webix
        .ajax()
        .sync()
        .get("api/payment").responseText)
    .filter(object => object.id <= 10);

function mainUploadLayout() {
    $$("datview").hide();
}

var successMessage = "Success";

var dependencyMap = [];
var dependency = [];