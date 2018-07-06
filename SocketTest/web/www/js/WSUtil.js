/**
 * Created by ASUS on 2018/3/14 0014.
 */
(function(window) {
    // var ip = "122.226.15.5";
    var ip = "localhost";
    var port = 8080;

    var webSocket;

    var messageConf = {};

    var onError = function (event) {
        console.log(event.data);
    };

    var onMessage = function (event) {
        console.log(event);
        var data = JSON.parse(event.data);
        var method = data['method'];
        var func = messageConf[method];
        if (typeof func === 'function') {
            func(data);
        }
    };

    var WSUtil = {};

    WSUtil.registerFunction = function (method, func) {
        messageConf[method] = func;
    }


    WSUtil.connect = function (onOpen) {
        webSocket = new WebSocket('ws://' + ip + ':' + port + '/SocketTest/web');
        webSocket.onerror = onError;
        webSocket.onopen = onOpen;
        webSocket.onmessage = onMessage;
    };

    WSUtil.send = function (method, body) {
        webSocket.send(JSON.stringify({
            method : method,
            body : body
        }))
    };

    window.onunload = function() {
        webSocket.close();
    }

    window.WSUtil = WSUtil;

})(window);
