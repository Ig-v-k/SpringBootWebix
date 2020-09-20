const MENU_STATES = {
    COLLAPSED: 0,
    EXPANDED: 1
};
let menuState = MENU_STATES.COLLAPSED;
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

const menyCompanyAdmin=[
    {
        id:"dashboard",
        icon:"home",
        value:"Home"
    },
    {
        id:"vehicle",
        icon:"car",
        value:"Vehicles"
    },
    {
        id: "logger",
        icon: "history",
        value: "User actions"
    },
    {
        id:"user",
        icon:"user",
        value:"Users"
    },

];

const menuCompanyUser=[
    {
        id:"dashboard",
        icon:"home",
        value:"Home"
    },
    {
        id:"vehicle",
        icon:"car",
        value:"Vehicles"
    },
];

const menuActions = function (id) {
    switch (id) {
        case "logger":
            loggerView.selectPanel();
            break;
        case "dashboard":
            locationView.selectPanel();
            break;
        case "user":
            userView.selectPanel();
            break;
    }
};

const init = function () {
    if (!webix.env.touch && webix.ui.scrollSize) webix.CustomScroll.init();
    webix.i18n.parseFormat = ("%d.%m.%Y.");

    webix.i18n.setLocale("ru-RU");
    webix.Date.startOnMonday = true;

    webix.ui(panel);
    panel = $$("empty");
    const urlQuery=window.location.search;
    if (urlQuery && urlQuery.startsWith('?q=reg')){
        const token=urlQuery.split('=')[2];
        connection.sendAjax("GET","api/user/check/"+token).then(result=> {
            const userId=result.json();
            showRegistration(userId);
        }).fail(err=> {
            util.messages.showErrorMessage("Token has expired or is invalid!");
            checkState();
        });
    }else{
        checkState();
    }

};

const checkState=function(){
    connection.sendAjax("GET","api/user/state").then(data=> {
        userData = data.json();
        showApp();
    }).fail(()=> {
        showLogin();
    });
};

const menuEvents = {
    onItemClick: function (item) {
        menuActions(item);
    }
};

const showLogin = function () {
    const login = webix.copy(loginLayout);
    webix.ui(login, panel);
    panel = $$("login");
};

const showRegistration = function (userId) {
    const registration=webix.copy(registrationLayout);
    webix.ui(registration,panel);
    panel=$$("registration");
    $$("registrationForm").setValues({
        id:userId
    });

};

const showApp = function () {
    // const promise=preloadDependencies();
    const main = webix.copy(mainLayout);
    webix.ui(main, panel);
    // panel = $$("app");
    // let localMenuData = null;
    // webix.ui({
    //     id: "menu-collapse",
    //     view: "template",
    //     template: '<div id="menu-collapse" class="menu-collapse">' +
    //         '<span></span>' +
    //         '<span></span>' +
    //         '<span></span>' +
    //         '</div>',
    //     onClick: {
    //         "menu-collapse": function (e, id, trg) {
    //             const elem = document.getElementById("menu-collapse");
    //             if (menuState == MENU_STATES.COLLAPSED) {
    //                 elem.className = "menu-collapse open";
    //                 menuState = MENU_STATES.EXPANDED;
    //                 $$("mainMenu").toggle();
    //             } else {
    //                 elem.className = "menu-collapse";
    //                 menuState = MENU_STATES.COLLAPSED;
    //                 $$("mainMenu").toggle();
    //             }
    //         }
    //     }
    // });
    // switch (userData.roleId) {
    //     case role.systemAdministrator:
    //         localMenuData = menuSystemAdmin;
    //         break;
    //     case role.companyAdministrator:
    //         localMenuData=menyCompanyAdmin;
    //         $$("showReportBtn").show();
    //         break;
    //     case role.user:
    //         localMenuData=menuCompanyUser;
    //         break;
    // }
    // $$("mainMenu").define("data", localMenuData);
    // $$("mainMenu").define("on", menuEvents);
    // rightPanel = "emptyRightPanel";
    // promise.then(value=> {
    //     if (userData.roleId === role.systemAdministrator) {
    //         companyView.selectPanel();
    //         $$("mainMenu").select("company");
    //     }else{
    //         locationView.selectPanel();
    //         $$("mainMenu").select("dashboard");
    //     }
    // }).fail(err=> {
       // connection.reload();
    // });
};

// const preloadDependencies = function () {
//     const promises=[];
//     promises.push(connection.sendAjax("GET","api/role").then(data=> {
//         const roles = [];
//         const array = [];
//         data.json().forEach(obj=> {
//             roles[obj.id] = obj.value;
//             array.push(obj);
//         });
//         dependencyMap["role"] = roles;
//         dependency["role"] = array;

    // }));
    // promises.push(connection.sendAjax("GET","api/status").then(data=> {
    //     const status = [];
    //     const array = [];

        // data.json().forEach(obj=> {
        //     status[obj.id] = obj.value;
        //     array.push(obj);
        // });
        // dependencyMap["status"] = status;
        // dependency["status"] = array;
    // }));
    // return webix.promise.all(promises);
// };

//main call
window.onload = function () {
    init();
};

