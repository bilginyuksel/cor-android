cordova.define("cordova-plugin-hms-example.HMSExample", function(require, exports, module) {
const argscheck = require('cordova/argscheck');
const exec = require('cordova/exec');
const cordova = require('cordova');

// const HMSExample = function () {
//       console.log("HMSExample instanced");
// };
//
// HMSExample.prototype.show = function (msg, onSuccess, onError) {
//     const onErr = onError ? onError : () => {};
//     const onSucc = onSuccess ? onSuccess : () => {};
//
//     //argscheck.checkArgs('fF', 'HMSExample.show', [msg]);
//     exec(onSucc, onErr, 'HMSExample', 'showToast', [msg]);
// };

// module.exports = new HMSExample();



function showToast(msg) {
    return new Promise((resolve, reject) => {
        exec(resolve, reject, 'HMSExample', 'test1', ['showToast', msg]);
    });

}
exports.showToast = showToast;

});
