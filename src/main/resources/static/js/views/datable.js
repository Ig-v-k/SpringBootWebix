webix.ready(function () {
    webix.ui({
        container:"app",
        view:"datatable",
        columns:[
            { id:"id",	header:"", css:"rank",  		width:50},
            { id:"name",	header:"Country",width:200},
            { id:"rev",	header:"Revenue" , width:180},
            { id:"exp",	header:"Expenditures", 	width:180},
            { id:"dif_math", header: "Deficit/surplus", width: 180},
            { id:"date", header: "Date", width: 80}
        ],
        autoheight:true,
        autowidth:true,
        math: true,
        data: budget
    });
});