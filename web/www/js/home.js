/**
 * Created by ASUS on 2018/3/14 0014.
 */

function Home() {
}

var home;

$(function () {
    home = new Home();
    console.log(home)
    home.init();
});

Home.prototype = {
    /**
     * session
     */
    sessionKey : "",

    /**
     * 设备列表
     */
    deviceList : [],

    /**
     * 界面初始化
     */
    init : function () {
        WSUtil.registerFunction("home", this.refreshList);
        WSUtil.registerFunction("deviceStateChange", this.deviceStateChange);
        WSUtil.connect(function () {
            WSUtil.send("home", {})
        });
    },

    /**
     * 刷新列表
     * @param data
     */
    refreshList : function (data) {
        var $table = $(".table");
        this.sessionKey = data["sessionKey"];
        home.deviceList = data['deviceList'];
        for (var i in home.deviceList) {
            var device = home.deviceList[i];
            var $tr = $("<tr>").attr("id", "device" + i).appendTo($table);
            $("<th scope='row'>1</th>").html(i + 1).appendTo($tr);
            $("<td>").html(device["deviceId"]).appendTo($tr);
            $("<td>").addClass("version").html(device["version"]).appendTo($tr);
            if (typeof device["online"] !== "undefined" && device["online"] == 1) {
                $("<td>").addClass("online").html("在线").appendTo($tr);
            } else {
                $("<td>").addClass("online").html("离线").appendTo($tr);
            }
            var $td = $("<td>").appendTo($tr);
            var $switch1, $switch2;
            if (typeof device["switch1"] !== "undefined" && device["switch1"] == 1) {
                $switch1 = $("<button type='button' class='btn btn-success switch1'>关闭开关1</button>").attr("type", "offSwitch1").appendTo($td);
            } else {
                $switch1 = $("<button type='button' class='btn btn-success switch1'>打开开关1</button>").attr("type", "onSwitch1").appendTo($td);
            }
            if (typeof device["switch2"] !== "undefined" && device["switch2"] == 1) {
                $switch2 = $("<button type='button' class='btn btn-success switch2'>关闭开关2</button>").attr("type", "offSwitch2").appendTo($td);
            } else {
                $switch2 = $("<button type='button' class='btn btn-success switch2'>打开开关2</button>").attr("type", "onSwitch2").appendTo($td);
            }
            var $restart = $("<button type='button' class='btn btn-danger'>重启</button>").appendTo($td);
            var $update = $("<button type='button' class='btn btn-success'>更新</button>").appendTo($td);
            $switch1.click(home.switch);
            $switch2.click(home.switch);
            $restart.click(home.restart)
            $update.click(home.update);
        }

    },

    /**
     * 开关操作
     */
    switch : function () {
        console.log(home)
        console.log(this);
        var id = this.parentNode.parentNode.id.substr(6);
        var device = home.deviceList[id];
        var type = this.getAttribute("type");
        if (type == "offSwitch1") {
            device["switch1"] = 0;
        } else if (type == "offSwitch2") {
            device["switch2"] = 0;
        } else if (type == "onSwitch1") {
            device["switch1"] = 1;
        } else if (type == "onSwitch2") {
            device["switch2"] = 1;
        }
        var body = {
            sessionKey: this.sessionKey,
            deviceId: device["deviceId"],
            switch1: device["switch1"],
            switch2: device["switch2"]
        };
        WSUtil.send("switch", body);
    },

    /**
     * 重启设备
     */
    restart : function () {
        var id = this.parentNode.parentNode.id.substr(6);
        var device = home.deviceList[id];
        var body = {
            sessionKey: this.sessionKey,
            deviceId: device["deviceId"]
        };
        WSUtil.send("restart", body);
    },

    /**
     * 更新的命令
     */
    update : function () {
        var id = this.parentNode.parentNode.id.substr(6);
        var device = home.deviceList[id];
        var body = {
            sessionKey: this.sessionKey,
            deviceId: device["deviceId"],
            type: device["type"]
        };
        WSUtil.send("update", body);
    },

    /**
     * 设备信息发生改变
     * @param data
     */
    deviceStateChange: function (data) {
        var device = data["device"];
        for (var i in home.deviceList) {
            if (home.deviceList[i]["deviceId"] == device.deviceId) {
                var $tr = $("#device" + i);
                $tr.find(".version").html(device["version"]);
                if (typeof device["online"] !== "undefined" && device["online"] == 1) {
                    $(".online").html("在线");
                } else {
                    $(".online").html("离线");
                }
                var $switch1 = $(".switch1");
                var $switch2 = $(".switch2");
                if (typeof device["switch1"] !== "undefined" && device["switch1"] == 1) {
                    $switch1.html("关闭开关1").attr("type", "offSwitch1");
                } else {
                    $switch1.html("打开开关1").attr("type", "onSwitch1");
                }
                if (typeof device["switch2"] !== "undefined" && device["switch2"] == 1) {
                    $switch2.html("关闭开关2").attr("type", "offSwitch2");
                } else {
                    $switch2.html("打开开关2").attr("type", "onSwitch2");
                }
            }
        }
    }


}


