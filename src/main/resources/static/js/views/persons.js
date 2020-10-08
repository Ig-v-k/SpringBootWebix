personsLayout = {
    id: "usrlayout",
    rows:[
        {
            view:"toolbar", css: this.config.theme,
            elements:[
                { width:4 },
                { view:"label", label:_("Persons"), localId:"label" },
                { width:4 },
                {
                    view:"text", localId:"search", hidden:true,
                    on:{
                        onTimedKeyPress(){
                            const input = this.getValue().toLowerCase();
                            this.$scope.$$("list").filter(obj => {
                                const name = obj.fname + " " + obj.lname;
                                return name.toLowerCase().indexOf(input) !== -1;
                            });
                        }
                    }
                },
                {
                    view:"icon", icon:"mdi mdi-magnify",
                    state:"closed", localId:"search_icon",
                    tooltip:_("Search for a client"),
                    click:function(){
                        if (this.config.state === "closed"){
                            this.$scope.$$("label").hide();
                            this.$scope.$$("search").show();
                            this.$scope.$$("search").focus();
                            this.config.state = "open";
                        }
                        else if (this.config.state === "open"){
                            this.$scope.$$("label").show();
                            this.$scope.$$("search").hide();
                            this.config.state = "closed";
                        }
                    }
                }
            ]
        },
        {
            view:"list",
            localId:"list",
            css:"persons_list",
            width:(screen !== "small") ? 250 : 230,
            select:true,
            tooltip:{
                template:obj => {
                    let result = "<div>" + obj.lname + ", " + obj.fname + "</div>";
                    result += `<div>${_("Born")} ${dateFormat(obj.birthday)}</div>`;
                    result += `<p align="center" style="margin:0px;"><img src="data/photos/${obj.photo}_1.jpg" width="200px" height="200px"></p>`;
                    result += `<div>${_("Click twice to see more goodies")}</div>`;
                    return result;
                }
            },
            type:{
                template:obj => `<image class="userphoto" src="data/photos/${obj.photo}_1.jpg" />
							<div class="text">
						  		<span class="username">${obj.fname} ${obj.lname}</span>
						  		<span class="money">$${obj.money}</span>
							</div>`,
                height:66
            },
            on:{
                onAfterSelect:id => {
                    const person = persons.getItem(id);
                    this.app.callEvent("person:select",[person]);
                },
                onItemDblClick:id => {
                    if (this.getUrl()[0].page !== "customers")
                        this.show("customers?user="+id+"/information");
                    else this.show("information");
                }
            }
        }
    ]
}
