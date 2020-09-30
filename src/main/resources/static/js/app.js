let userData = null;
let panel = {id: "empty"};
let rightPanel = null;

const menuSystemAdmin = [
    {
        id: "company",
        icon: "briefcase",
        value: "Companies"
    },
    {
        id: "logger",
        icon: "history",
        value: "User actions"
    }
];

const menyCompanyAdmin = [
    {
        id: "logger",
        icon: "history",
        value: "User actions"
    },
    {
        id: "user",
        icon: "user",
        value: "Users"
    },

];

const menuCompanyUser = [
    {
        id: "dashboard",
        icon: "home",
        value: "Home"
    },
    {
        id: "vehicle",
        icon: "car",
        value: "Vehicles"
    },
];

// const menuActions = function (id) {
//     switch (id) {
//         case "logger":
//             loggerView.selectPanel();
//             break;
//         case "user":
//             userView.selectPanel();
//             break;
//     }
// };

const init = function () {
    if (!webix.env.touch && webix.ui.scrollSize) webix.CustomScroll.init();
    webix.i18n.parseFormat = ("%d.%m.%Y.");

    webix.i18n.setLocale("ru-RU");
    webix.Date.startOnMonday = true;

    webix.ui(panel);
    panel = $$("empty");
    const urlQuery = window.location.search;
    if (urlQuery && urlQuery.startsWith('?q=reg')) {
        const token = urlQuery.split('=')[2];
        connection.sendAjax("GET", "api/user/check/" + token).then(result => {
            const userId = result.json();
            showRegistration(userId);
        }).fail(err => {
            util.messages.showErrorMessage("Token has expired or is invalid!");
            checkState();
        });
    } else {
        checkState();
    }

};

const checkState = function () {
    connection.sendAjax("GET", "api/user/state").then(data => {
        userData = data.json();
        showApp();
    }).fail(() => {
        showLogin();
    });
};

// const menuEvents = {
//     onItemClick: function (item) {
//         menuActions(item);
//     }
// };

const showRegistration = function (userId) {
    const registration = webix.copy(registrationLayout);
    webix.ui(registration, panel);
    panel = $$("registration");
    $$("registrationForm").setValues({
        id: userId
    });

};

const showLogin = function () {
    const login = webix.copy(loginLayout);
    webix.ui(login, panel);
    panel = $$("login");
};

const showApp = function () {
    const main = webix.copy(mainLayout);
    webix.ui(main, panel);
    hideIconsOfTheRole();
};

const preloadDependencies = function () {
    const promises = [];
    promises.push(connection.sendAjax("GET", "api/role").then(data => {
        const roles = [];
        const array = [];
        data.json().forEach(obj => {
            roles[obj.id] = obj.value;
            array.push(obj);
        });
        dependencyMap["role"] = roles;
        dependency["role"] = array;

    }));
    promises.push(connection.sendAjax("GET", "api/status").then(data => {
        const status = [];
        const array = [];
        data.json().forEach(obj => {
            status[obj.id] = obj.value;
            array.push(obj);
        });
        dependencyMap["status"] = status;
        dependency["status"] = array;
    }));
    return webix.promise.all(promises);
};

//main call
window.onload = function () {
    init();
};

