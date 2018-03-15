/**
 * Created by ASUS on 2018/3/14 0014.
 */

$(function () {
    var home = new Home();
    home.init();
});

function Home() {

}

Home.prototype = {
    sessionKey : "",
    init : function () {
        WSUtil.registerFunction("home", this.refreshList);
        WSUtil.connect(function () {
            WSUtil.send("home", {})

        });
    },

    refreshList : function (data) {
        var $table = $(".table");
        this.sessionKey = data["sessionKey"];
        var deviceList = data['deviceList'];
        for (var i in deviceList) {
            var device = deviceList[i];
            var $tr = $("<tr>").attr("id", "device" + device.id).appendTo($table);
            $("<th scope='row'>1</th>").html(device.id).appendTo($tr);
            $("<td>").html(device["deviceId"]).appendTo($tr);
            if (typeof device["online"] !== "undefined" && device["online"] == 1) {
                $("<td>").html("在线").appendTo($tr);
            } else {
                $("<td>").html("离线").appendTo($tr);
            }
            var $td = $("<td>").appendTo($tr);
            var $switch1, $switch2;
            if (typeof device["switch1"] !== "undefined" && device["switch1"] == 1) {
                $switch1 = $("<button type='button' class='btn btn-success'>关闭开关1</button>").addClass("offSwitch1").appendTo($td);
            } else {
                $switch1 = $("<button type='button' class='btn btn-success'>打开开关1</button>").addClass("onSwitch1").appendTo($td);
            }
            if (typeof device["switch2"] !== "undefined" && device["switch2"] == 1) {
                $switch2 = $("<button type='button' class='btn btn-success'>关闭开关2</button>").addClass("offSwitch2").appendTo($td);
            } else {
                $switch2 = $("<button type='button' class='btn btn-success'>打开开关2</button>").addClass("onSwitch2").appendTo($td);
            }
            $switch1.click(Home.prototype.switch);
            $switch2.click(Home.prototype.switch);

        }

    },

    switch : function () {
        console.log(this);
    }
}


