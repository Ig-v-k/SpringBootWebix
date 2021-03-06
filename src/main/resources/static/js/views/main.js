var menu_data = [
    {
        id: "dashboard", icon: "mdi mdi-view-dashboard", value: "Dashboards", data: [
            {id: "transactions", value: "Transactions"}
        ]
    },
    // {
    //     id: "layouts", icon: "mdi mdi-view-column", value: "Layouts", data: [
    //         {id: "accordions", value: "Accordions"},
    //         {id: "portlets", value: "Portlets"}
    //     ]
    // },
    {
        id: "tables", icon: "mdi mdi-table", value: "Data Tables", data: [
            {id: "tables1", value: "Datatable"},
            // {id: "tables2", value: "TreeTable"},
            // {id: "tables3", value: "Pivot"}
        ]
    },
    // {
    //     id: "uis", icon: "mdi mdi-puzzle", value: "UI Components", data: [
    //         {id: "dataview", value: "DataView"},
    //         {id: "list", value: "List"},
    //         {id: "menu", value: "Menu"},
    //         {id: "tree", value: "Tree"}
    //     ]
    // },
    // {
    //     id: "tools", icon: "mdi mdi-calendar", value: "Tools", data: [
    //         {id: "kanban", value: "Kanban Board"},
    //         {id: "pivot", value: "Pivot Chart"},
    //         {id: "scheduler", value: "Calendar"}
    //     ]
    // },
    // {
    //     id: "forms", icon: "mdi mdi-pencil", value: "Forms", data: [
    //         {id: "buttons", value: "Buttons"},
    //         {id: "selects", value: "Select boxes"},
    //         {id: "inputs", value: "Inputs"}
    //     ]
    // },
    // {id: "demo", icon: "mdi mdi-book", value: "Documentation"}
];

const mainLayout = {
    id: "app",
    // type: "space",
    rows: [
        {
            view: "toolbar",
            padding: 3,
            elements: [
                {
                    id: "asd",
                    view: "button",
                    type: "icon",
                    icon: "mdi mdi-menu",
                    width: 37,
                    align: "left",
                    css: "app_button",
                    collapsed: true,
                    click: function () {
                        $$("$sidebar1").toggle();
                    },
                    tooltip: "Click to collapse / expand the sidebar"
                },
                {
                    view: "label",
                    label: "Logistic"
                },
                // {
                //     id: "1_User",
                //     view: "button",
                //     type: "icon",
                //     width: 45,
                //     css: "app_button",
                //     icon: "mdi mdi-comment",
                //     badge: 4
                // },
                {
                    id: "2_User",
                    view: "button",
                    type: "icon",
                    width: 45,
                    css: "app_button",
                    icon: "mdi mdi-bell",
                    badge: 3,
                    tooltip: "Notifications",
                },
                {
                    id: "3_User",
                    view: "button",
                    type: "icon",
                    width: 45,
                    css: "app_button",
                    icon: "mdi mdi-logout",
                    tooltip: "Logout",
                    click: function () {
                        logout();
                    }
                },
            ],

        },
        {
            id: "app_horizontal",
            // minHeight: 860,
            autoheight: true,
            type: "wide",
            cols: [
                {
                    view: "sidebar",
                    data: menu_data,
                    width: 190,
                    on: {
                        onAfterSelect: function (id) {
                            webix.message("Selected: " + this.getItem(id).value);
                        }
                    }
                },
                {
                    id: "containerMain",
                    paddingY: 10,
                    rows: [
                        {
                            view: "toolbar",
                            elements: [
                                {width: 4},
                                {view: "label", label: "Transactions", width: 150},
                                {minWidth: 4},
                                {
                                    view: "segmented", minWidth: 333,
                                    tooltip: obj => {
                                        return `${"Display"} ${obj.value.toLowerCase()} ${obj.value.indexOf("Payments") === 0 ? "" : "transactions"}`;
                                    },
                                    options: [
                                        {id: "all", value: "All"},
                                        {id: "0", value: "Payments"},
                                        {id: "1", value: "Incoming"}
                                    ],
                                    on: {
                                        onChange: function (id) {
                                            if (id === "all")
                                                $$("datview").filter();
                                            else
                                                $$("datview").filter(object => object.type != id)
                                        }
                                    }
                                },
                                {width: 6}
                            ]
                        },
                        {
                            id: "datview",
                            view: "datatable",
                            localId: "grid",
                            select: "row",
                            multiselect: true,
                            tooltip: true,
                            footer: true,
                            data: JSON.parse(_listDataPayments.responseText).filter(object => object.id <= 10),
                            on: {
                                onAfterLoad: function () {
                                    if (!this.count())
                                        this.showOverlay("Sorry, there is no data");
                                    else
                                        this.hideOverlay();
                                }

                            },
                            columns: [
                                {
                                    id: "id",
                                    header: "#",
                                    width: 50,
                                    sort: "int",
                                    tooltip: false,
                                    footer: {
                                        text: "Total:",
                                        colspan: 1
                                    }
                                },
                                {
                                    id: "status", header: "", width: 40,
                                    css: "status", sort: "text", tooltip: false,
                                    template: data => {
                                        let icon = "";
                                        if (data.status === "success")
                                            icon = "check-circle";
                                        else if (data.status === "failed")
                                            icon = "alert-box";
                                        else
                                            icon = "clock";
                                        return `<span class='webix_icon mdi mdi-${icon} ${data.status}'></span>`;
                                    }
                                },
                                {
                                    id: "pay_date", header: "Date",
                                    fillspace: 2, minWidth: 150,
                                    sort: "date"
                                },
                                {
                                    id: "", header: "Payment", fillspace: 3, minWidth: 240, sort: "text",
                                    tooltip: "The card with which the payment was made",
                                    template: data => {
                                        return `<img class="method" src="../../img/${data.method}.svg" />${data.method} ${data._number || ""}`;
                                    }
                                },
                                {
                                    id: "", header: {
                                        text: "Purchase",
                                        tooltip: "Click to sort the list by shops"
                                    },
                                    fillspace: 4, minWidth: 200, sort: "text",
                                    template: data => `${data.name} / ${data.city} / ${data.country}`
                                },
                                {
                                    id: "type", header: "+/-", sort: "int",
                                    css: "type", fillspace: 1, minWidth: 30,
                                    template: data => {
                                        let type = data.type ? "plus incoming" : "minus payment";
                                        return `<span class='webix_icon mdi mdi-${type}'></span>`;
                                    },
                                    tooltip: obj => {
                                        return (obj.type ? "Incoming" : "Outgoing") + " payment";
                                    }
                                },
                                {
                                    id: "sum", header: "Sum", sort: "int",
                                    fillspace: 1, minWidth: 70,
                                    format: webix.i18n.priceFormat,
                                    footer: {
                                        content: "summColumn",
                                        tooltip: obj => {
                                            const sum = this.$$("grid").getHeaderContent(obj.contentId).getValue();
                                            return "Total money flow: " + sum;
                                        }
                                    }
                                },
                                {
                                    id: "leftPay", header: "Left",
                                    fillspace: 1, minWidth: 70,
                                    sort: "int", format: webix.i18n.priceFormat,
                                    footer: {
                                        content: "summColumn",
                                        tooltip: obj => {
                                            const sum = this.$$("grid").getHeaderContent(obj.contentId).getValue();
                                            return "Total money left: " + sum;
                                        }
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    paddingY: 10,
                    type: "wide",
                    width: 250,
                    rows: [
                        {
                            rows: [
                                {
                                    view: "toolbar", css: webix.storage.local.get("bank_app_theme"),
                                    elements: [
                                        {width: 4},
                                        {view: "label", label: "Persons", localId: "label", id: "labell"},
                                        {width: 4},
                                        {
                                            view: "text", localId: "search", id: "searchh", hidden: true,
                                            on: {
                                                "onTimedKeyPress": function () {
                                                    var value = this.getValue().toLowerCase();
                                                    $$("listt").filter(function (obj) {
                                                        const name = obj.firstName + " " + obj.lastName;
                                                        return name.toLowerCase().indexOf(value) !== -1;
                                                    })
                                                }
                                            }
                                        },
                                        {
                                            view: "icon", icon: "mdi mdi-magnify",
                                            state: "closed", localId: "search_icon",
                                            tooltip: "Search for a client",
                                            click: function () {
                                                if (this.config.state === "closed") {
                                                    $$("searchh").show();
                                                    $$("labell").hide();
                                                    $$("searchh").focus();
                                                    this.config.state = "open";
                                                } else if (this.config.state === "open") {
                                                    $$("labell").show();
                                                    $$("searchh").hide();
                                                    this.config.state = "closed";
                                                }
                                            }
                                        }
                                    ]
                                },
                                {
                                    view: "list",
                                    localId: "list",
                                    id: "listt",
                                    css: "persons_list",
                                    width: (screen === "small") ? 230 : 250,
                                    select: true,
                                    data: JSON.parse(_listDataUsers.responseText),
                                    tooltip: {
                                        template: obj => {
                                            let result = "<div>" + obj.lastName + ", " + obj.firstName + "</div>";
                                            result += `<div>${"Born"} 1975-01-06</div>`;
                                            result += `<p align="center" style="margin:0px;"><img src="../../img/tommie_1.jpg" width="200px" height="200px"></p>`;
                                            result += `<div>${"Click twice to see more goodies"}</div>`;
                                            return result;
                                        }
                                    },
                                    type: {
                                        template: obj => `<image class="userphoto" src="../../img/tommie_1.jpg" />
                                                            <div class="text">
                                                                <span class="username">${obj.firstName} ${obj.lastName}</span>
                                                                <span class="money">$667.16</span>
                                                            </div>`,
                                        height: 66
                                    },
                                    on: {
                                        onAfterSelect: id => {
                                            const user = _usersDataCollection.getItem(id);
                                            const datview = $$("datview");
                                            datview.eachRow(function (row) {
                                                if(datview.getItem(row).paymentUser.firstName.toLowerCase() === user.firstName.toLowerCase()) {
                                                    datview.select(datview.getItem(row).id, true);
                                                }
                                            })
                                        }
                                    }
                                }
                            ]
                        },
                        {
                            template: "row 2"
                        }
                    ]
                }
            ]
        }
    ]
};

const registrationLayout = {
    id: "registration",
    width: "auto",
    height: "auto",
    userId: null,
    rows: [
        {},
        {
            cols: [
                {},
                {
                    view: "template",
                    borderless: true,
                    height: 500,
                    width: 500,
                },
                {
                    rows: [
                        {
                            height: 50,
                            view: "label",
                            css: "registration-label",
                            label: "Registration"
                        },
                        {},
                        {
                            view: "form",
                            id: "registrationForm",
                            borderless: true,
                            width: 400,
                            elementsConfig: util.elementsConfig,
                            elements: [
                                {},
                                {
                                    id: "username",
                                    name: "username",
                                    view: "text",
                                    label: "User name:",
                                    invalidMessage: "Username is required!",
                                    required: true
                                },
                                {
                                    id: "firstName",
                                    name: "firstName",
                                    view: "text",
                                    label: "Ime:",
                                    invalidMessage: "Username is required...!",
                                    required: true
                                },
                                {
                                    id: "lastName",
                                    name: "lastName",
                                    view: "text",
                                    label: "Surname:",
                                    invalidMessage: "Last name is required!",
                                    required: true
                                },
                                {
                                    id: "password",
                                    name: "password",
                                    view: "text",
                                    type: "password",
                                    label: "Password:",
                                    invalidMessage: "Password is required!",
                                    required: true
                                },
                                {
                                    id: "registrationBtn",
                                    view: "button",
                                    value: "Register",
                                    type: "form",
                                    click: "register",
                                    align: "right",
                                    hotkey: "enter",
                                    width: 150
                                }
                            ]
                        },
                        {}

                    ]
                },
                {}
            ]
        }
    ]
};

const loginLayout = {
    id: "login",
    // type:
    //     {
    //         width: "auto",
    //         height: "auto",
    //     },
    // sizeToContent: true,
    rows: [
        {
            gravity: 0.1
        },
        {
            cols: [
                {},
                {
                    view: "template",
                    borderless: true,
                    height: 500,
                    width: 500
                },
                {
                    rows: [
                        {
                            height: 50,
                        },
                        {
                            view: "form",
                            id: "loginForm",
                            borderless: true,
                            width: 400,
                            elementsConfig: util.elementsConfig,
                            elements: [
                                {
                                    id: "username",
                                    name: "username",
                                    view: "text",
                                    label: 'Username',
                                    invalidMessage: "Login can not be empty",
                                    required: true
                                },
                                {
                                    id: "password",
                                    name: "password",
                                    view: "text",
                                    label: 'Password',
                                    invalidMessage: "Password is required",
                                    required: true
                                },
                                {
                                    id: "loginBtn",
                                    view: "button",
                                    value: "Submit",
                                    type: "form",
                                    click: "login",
                                    align: "right",
                                    hotkey: "enter",
                                    width: 150
                                }
                            ]
                        },
                        {}
                    ]
                },
                {}

            ]
        },
        {
            gravity: 0.1
        }
    ]
};

const custom_form = {
    id: "login",
    width: "auto",
    height: "auto",
    rows: [
        {
            gravity: 0.1
        },
        {
            cols: [
                {},
                {
                    view: "template",
                    borderless: true,
                    height: 500,
                    width: 500
                },
                {
                    rows: [
                        {
                            height: 50,
                        },
                        {
                            view: "form",
                            id: "loginForm",
                            borderless: true,
                            width: 400,
                            elementsConfig: util.elementsConfig,
                            elements: [
                                {
                                    id: "username",
                                    name: "username",
                                    view: "text",
                                    label: "User name:",
                                    invalidMessage: "Username is required!",
                                    required: true
                                },
                                {
                                    id: "password",
                                    name: "password",
                                    view: "text",
                                    type: "password",
                                    label: "Password:",
                                    invalidMessage: "Password is required!",
                                    required: true
                                },
                                {
                                    id: "companyName",
                                    name: "companyName",
                                    view: "text",
                                    label: "Company:"
                                },
                                {
                                    id: "loginBtn",
                                    view: "button",
                                    value: "Sign up",
                                    type: "form",
                                    click: "login",
                                    align: "right",
                                    hotkey: "enter",
                                    width: 150
                                }
                            ]
                        },
                        {}
                    ]
                },
                {}
            ]
        },
        {
            gravity: 0.1
        }
    ]
};

const login = function () {
    const form = $$("loginForm");
    if (form.validate()) {
        connection.sendAjax("POST", "/api/user/login", form.getValues()).then(data => {
            userData = data.json();
            showApp();
        }).fail(err => {
            util.messages.showErrorMessage("Login failed!");
        });
    }
};

const logout = function () {
    webix.ajax().get("/api/user/logout", function (xhr) {
        if (xhr.status === "200") {
            userData = null;
            util.messages.showLogoutMessage();
            connection.reload();
        } else {
            util.messages.showLogoutErrorMessage();
            connection.reload();
        }
    });
};

const hideIconsOfTheRole = function () {
    if (userData.statusId === 0 || userData.statusId === null) {
        $$("1_User").$view.style.visibility = 'hidden';
        $$("2_User").$view.style.visibility = 'hidden';

        // $$("1_User").$view.style.display = 'none';
        // $$("2_User").$view.style.display = 'none';
    }
    return false;
}