layui.define(['layer'], function(exports){

    var escape = {

        dataStream: function (dataStream) {
            switch (dataStream) {
                case 'wendu':
                    return '温度';
                case 'shidu':
                    return '湿度';
                case 'guang':
                    return '光强';
                case 'led':
                    return 'LED';
                case 'fan':
                    return '抽风机';
                case 'heating':
                    return '暖气';
                case 'curtain':
                    return '窗帘';
                default:
                    return '未知';
            }
        },

        currVal: function (currVal, unitSymbol) {
            switch (currVal) {
                case 'curtainON':
                    return 'ON';
                case 'curtainOFF':
                    return 'OFF';
                case 'heatingON':
                    return 'ON';
                case 'heatingOFF':
                    return 'OFF';
                case 'fanON':
                    return 'ON';
                case 'fanOFF':
                    return 'OFF';
                case 'ledON1':
                    return '一档';
                case 'ledON2':
                    return '二档';
                case 'ledON3':
                    return '三档';
                case 'ledOFF':
                    return 'OFF';
                default:
                    return currVal + unitSymbol;
            }
        },

    };

    exports('escape', escape);
});