define(function () {
    return {
        view: "form",
        scroll: false,
        elements: [
            {
                view: "text",
                label: 'Username',
                name: "login",
                invalidMessage: "Login can not be empty"
            },
            {
                view: "text",
                label: 'E-mail address',
                name: "email",
                invalidMessage: "Incorrect e-mail address"
            },
            {
                view: "text",
                label: 'Password',
                name: "password",
                invalidMessage: "Password can not be empty"
            },
            {
                view: "checkbox",
                labelRight: 'I accept terms of use',
                name: "accept",
                invalidMessage: "Must be checked"
            },
            {
                view: "button",
                value: "Submit",
                align: "center",
                width: 150,
                click: function () {
                    routie('datas')
                }
            }
        ],
        width: 350,
        margin: 3,
        rules: {
            "email": webix.rules.isEmail,
            "login": webix.rules.isNotEmpty,
            "password": webix.rules.isNotEmpty,
            "accept": webix.rules.isChecked
        },
        elementsConfig: {
            labelPosition: "center",
            labelWidth: 140,
            bottomPadding: 18
        }
    }
})