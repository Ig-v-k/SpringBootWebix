var menu_data = [
    {
        id: "dashboard", icon: "mdi mdi-view-dashboard", value: "Dashboards", data: [
            {id: "dashboard1", value: "Dashboard 1"}
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

var menu_data_multi = [
    {
        id: "structure",
        icon: "mdi mdi-view-column",
        value: "Structuring",
        data: [
            {
                id: "layouts",
                icon: "mdi mdi-circle",
                value: "Layouts",
                data: [
                    {
                        id: "layout",
                        icon: "mdi mdi-circle-outline",
                        value: "Layout"
                    },
                    {
                        id: "flexlayout",
                        icon: "mdi mdi-circle-outline",
                        value: "Flex Layout"
                    },
                    {
                        id: "strict",
                        icon: "mdi mdi-circle-outline",
                        value: "Precise Positioning",
                        data: [
                            {
                                id: "gridlayout",
                                icon: "mdi mdi-circle-outline",
                                value: "Grid Layout"
                            },
                            {
                                id: "dashboard",
                                icon: "mdi mdi-circle-outline",
                                value: "Dashboard"
                            },
                            {
                                id: "abslayout",
                                icon: "mdi mdi-circle-outline",
                                value: "Abs Layout"
                            }
                        ]
                    },
                    {
                        id: "datalayouts",
                        icon: "mdi mdi-circle-outline",
                        value: "Data Layouts",
                        data: [
                            {
                                id: "datalayout",
                                icon: "mdi mdi-circle-outline",
                                value: "Data Layout"
                            },
                            {
                                id: "flexdatalayout",
                                icon: "mdi mdi-circle-outline",
                                value: "Flex Data Layout"
                            },
                        ]
                    }
                ]
            },
            {
                id: "multiviews",
                icon: "mdi mdi-circle",
                value: "Multiviews",
                data: [
                    {
                        id: "multiview",
                        icon: "mdi mdi-circle-outline",
                        value: "MultiView"
                    },
                    {
                        id: "tabview",
                        icon: "mdi mdi-circle-outline",
                        value: "TabView"
                    },
                    {
                        id: "accordion",
                        icon: "mdi mdi-circle-outline",
                        value: "Accordion"
                    },
                    {
                        id: "carousel",
                        icon: "mdi mdi-circle-outline",
                        value: "Carousel"
                    }
                ]
            }
        ]
    },
    {
        id: "tools",
        icon: "mdi mdi-calendar",
        value: "Tools",
        data: [
            {
                id: "kanban",
                icon: "mdi mdi-circle",
                value: "Kanban Board"
            },
            {
                id: "pivot",
                icon: "mdi mdi-circle",
                value: "Pivot Chart"
            },
            {
                id: "scheduler",
                icon: "mdi mdi-circle",
                value: "Calendar"
            }
        ]
    },
    {
        id: "forms",
        icon: "mdi mdi-pencil",
        value: "Forms",
        data: [
            {
                id: "buttons",
                icon: "mdi mdi-circle",
                value: "Buttons",
                data: [
                    {
                        id: "button",
                        icon: "mdi mdi-circle-outline",
                        value: "Buttons"
                    },
                    {
                        id: "segmented",
                        icon: "mdi mdi-circle-outline",
                        value: "Segmented"
                    },
                    {
                        id: "toggle",
                        icon: "mdi mdi-circle-outline",
                        value: "Toggle"
                    },
                ]
            },
            {
                id: "texts",
                icon: "mdi mdi-circle",
                value: "Text Fields",
                data: [
                    {
                        id: "text",
                        icon: "mdi mdi-circle-outline",
                        value: "Text"
                    },
                    {
                        id: "textarea",
                        icon: "mdi mdi-circle-outline",
                        value: "Textarea"
                    },
                    {
                        id: "richtext",
                        icon: "mdi mdi-circle-outline",
                        value: "RichText"
                    }
                ]
            },
            {
                id: "selects",
                icon: "mdi mdi-circle",
                value: "Selectors",
                data: [
                    {
                        id: "single",

                        icon: "mdi mdi-circle-outline",
                        value: "Single value",
                        data: [
                            {
                                id: "combo",
                                icon: "mdi mdi-circle-outline",
                                value: "Combo"
                            },
                            {
                                id: "richselect",
                                icon: "mdi mdi-circle-outline",
                                value: "RichSelect"
                            },
                            {
                                id: "select",
                                icon: "mdi mdi-circle-outline",
                                value: "Select"
                            }
                        ]
                    },
                    {
                        id: "multi",
                        icon: "mdi mdi-circle-outline",
                        value: "Multiple values",
                        data: [
                            {
                                id: "multicombo",
                                icon: "mdi mdi-circle-outline",
                                value: "MultiCombo"
                            },
                            {
                                id: "multiselect",
                                icon: "mdi mdi-circle-outline",
                                value: "MultiSelect"
                            }
                        ]
                    }
                ]
            }
        ]
    },
    {
        id: "demo",
        icon: "mdi mdi-book",
        value: "Documentation"
    }
];

const mainLayout = {
    id: "app",
    type: "space",
    rows: [
        {
            view: "toolbar",
            padding: 3,
            elements: [
                {
                    view: "button",
                    type: "icon",
                    icon: "mdi mdi-menu",
                    width: 37,
                    align: "left",
                    css: "app_button",
                    click: function () {
                        $$("$sidebar1").toggle();
                    }
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
            autoheight: true,
            type: "wide",
            cols: [
                {
                    view: "sidebar",
                    data: menu_data,
                    on: {
                        onAfterSelect: function (id) {
                            webix.message("Selected: " + this.getItem(id).value);
                        }
                    }
                },
                {
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
                                        // onChange:newv => this.app.callEvent("tactions:filter",[newv])
                                    }
                                },
                                {width: 6}
                            ]
                        },
                        {
                            view: "datatable",
                            localId: "grid",
                            id: "datview",
                            select: true,
                            tooltip: true,
                            footer: true,
                            save: {
                                url: "rest->api/payment",
                                autoupdate: true
                            },
                            url: "rest->api/payment",
                            scheme: {
                                $init: function (grid) {

                                }
                            },
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
                                    id: "date", header: "Date",
                                    fillspace: 2, minWidth: 150,
                                    sort: "date",
                                    // template: data => {
                                    //     const my_format = webix.Date.strToDate(new Date());
                                    //     data.pay_date = my_format(data.pay_date)
                                    //     return `${data.pay_date}`
                                    // }
                                    template: data => {
                                        return `${data.pay_date}`
                                    }
                                    // format: dateFormat
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
                                    id: "left", header: "Left",
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
                    type: "wide",
                    width: 250,
                    rows: [
                        {
                            template: "row 1"
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