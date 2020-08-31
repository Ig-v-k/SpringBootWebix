requirejs.config({
    baseUrl: 'js'
})

function buildRoute(view) {
    return function () {
        webix.ui({
            id: 'root',
            rows: [
                view
            ]
        }, $$("root"))
    }
}

require(['views/form', 'views/custom_list_data_view'], function (main, datas) {
    webix.ready(function () {
        webix.ui({
            id: 'root',
            container: "app"
        });
    })

    routie({
        '': buildRoute(main),
        'datas': buildRoute(datas)
    })
})