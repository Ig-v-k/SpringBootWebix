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

require(['views/toolbar', 'views/form'], function (toolbar, form) {
    webix.ready(function () {
        webix.ui({
            id: 'root',
            container: "preloader"
        });
    })

    routie({
        '': buildRoute(toolbar),
        'login_form': buildRoute(form)
    })
})