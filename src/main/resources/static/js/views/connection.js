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
};