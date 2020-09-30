const transactionalLayout = {
    view: "toolbar",
    width: 300,
    height: 100,
    elements: [
        {
            width: 4
        },
        {
            view: "label",
            label: "Transactions",
            width: 150
        },
        {
            minWidth: 4
        },
        {
            view: "segmented",
            minWidth: 333,
            tooltip: obj => {
                return `"Display" ${obj.value.toLowerCase()} ${obj.value.indexOf("Payments") === 0 ? "" : "transactions"}`;
            },
            options: [
                {
                    id: "all",
                    value: "All"
                },
                {
                    id: "0",
                    value: "Payments"
                },
                {
                    id: "1",
                    value: "Incoming"
                }
            ],
            on: {
                // onChange: newv => this.app.callEvent("tactions:filter", [newv])
            }
        },
        {
            width: 6
        }
    ]
};