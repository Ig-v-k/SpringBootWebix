var util = {
    messages: {
        showMessage: function (message) {
            webix.message({
                type: "default",
                text: message
            });
        },
        showErrorMessage: function (message) {
            webix.message({
                type: "error",
                text: message
            });
        },
        showWarningMessage: function (message, expire) {
            webix.message({
                type: "warning",
                text: message,
                expire: expire || 0
            });
        },
        showSessionExpiredError: function () {
            webix.message({
                type: "error",
                text: "Your session has timed out. Sign in again..."
            });
        },
        showLogoutMessage: function () {
            webix.message({
                type: "default",
                text: "You have successfully logged out."
            });
        },
        showLogoutErrorMessage: function () {
            webix.message({
                type: "default",
                text: "Error signing out. The application will be reloaded..."
            });
        }
    },

    preloader: {
        state: 0,

        inc: function () {
            if (this.state === 0) {
                document.getElementById("preloader").style.display = "block";
            }
            this.state++;
        },

        dec: function () {
            if (this.state === 0) return;
            this.state--;
            if (this.state === 0) {
                document.getElementById("preloader").style.display = "none";
            }
        },

        reset: function () {
            this.state = 0;
            document.getElementById("preloader").style.display = "none";
        }
    },

    elementsConfig: {
        labelWidth: 140,
        bottomPadding: 18
    },
}