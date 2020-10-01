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

var formatDate = webix.Date.dateToStr("%j %F, %H:%i");
var stringDate = formatDate(new Date());

var successMessage = "Success";

var dependencyMap = [];
var dependency = [];