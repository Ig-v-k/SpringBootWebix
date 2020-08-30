requirejs.config({
    baseUrl: 'js'
})

require(['views/form', 'views/custom_list_data_view'], function (main, cars) {
    webix.ready(function () {
        webix.ui({
            id: 'root',
            container: "app",
            rows: [
                main
            ]
        });
    })
})