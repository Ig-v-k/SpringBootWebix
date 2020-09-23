var connection = {
    showSEM: true,

    sendAjax:function(method,url,object){
        switch (method) {
            case "GET":
                return webix.ajax().get(url);
            case "POST":
                return webix.ajax().headers({
                    "Content-type": "application/json"
                }).post(url,JSON.stringify(object));
            case "PUT":
                return webix.ajax().headers({
                    "Content-type": "application/json"
                }).put(url,JSON.stringify(object));
            case "DELETE":
                return webix.ajax().del(url);
        }
    },
    reload: function () {
        setTimeout(function () {
            window.location.reload();
        }, 1500);

        throw "Session expired exception";
    },
};

webix.proxy.hub = {
    $proxy: true,
    load: function (view, callback, url) {
        util.preloader.inc();
        webix.ajax(this.source, callback, view).then(function () {
            util.preloader.dec();
        }).fail(function (err) {
            if (err.status === 401) {
                if (connection.showSEM) {
                    util.messages.showSessionExpiredError();
                    connection.showSEM = false;
                }
                connection.reload();
            }
            util.messages.showErrorMessage("Unable to collect data from server.");
            util.preloader.dec();
        });

    }
};