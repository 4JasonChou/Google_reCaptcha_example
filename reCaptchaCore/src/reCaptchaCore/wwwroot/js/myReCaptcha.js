var verifyCallback = function (response) {
    document.getElementById('capRes').innerHTML = response;
};

var widgetId1;
var onloadCallback = function () {
    grecaptcha.render('example', {
        'sitekey': '6LejJSYUAAAAAFsjW8sQciPoC8DNbzMsDSpGLJb2',
        'callback': verifyCallback,
        'theme': 'light'
    });
};