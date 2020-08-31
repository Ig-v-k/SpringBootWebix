define(function () {
    return {
        view: "layout",
        rows: [
            {
                view: "button",
                value: "Home",
                width: 150,
                click: function () {
                    routie('')
                },
            },
            {
                view: 'list',
                autoheight: true,
                autowidth: true,
                data: [
                    'AAAAAAA',
                    'BBBBBBB',
                    'CCCCCCC'
                ]
            }
        ]
    }
})