webix.ready(function () {
    webix.ui({
        type: "line",
        container: "app",
        id: "root",
        rows:[
            { template:"Row 1" },
            { template:"Row 2" },
            {
                cols: [
                    { template: "col 1"},
                    { template: "col 2"}
                ]
            }
        ]
    });
})