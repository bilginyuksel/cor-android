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

function asyncExec(reference, args) {
    return new Promise((resolve, reject) => {
        exec(resolve, reject, 'HMSExample', reference, args);
    });
}

function showToast(msg) {
    return asyncExec('Test1', ['showToast', msg]);
}
exports.showToast = showToast;
