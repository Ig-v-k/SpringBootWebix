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

const _dateFormatUsers = webix.Date.dateToStr("%d %M %Y");
var _listDataPayments = webix.ajax().sync().get("api/payment");
var _listDataUsers = webix.ajax().sync().get("api/user");
const _usersDataCollection = new webix.DataCollection({
    data: _listDataUsers.responseText
})


var successMessage = "Success";

var dependencyMap = [];
var dependency = [];