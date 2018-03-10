var system = require('system')
var page = require('webpage').create()
var scriptLoaded = false
page.settings.userAgent =  'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.71 Safari/537.36';
page.onInitialized = function() {
    scriptLoaded = page.injectJs('index.js')
}
page.onResourceError = function(error) {
    console.log("resource error")
    console.log(JSON.stringify(error))
}
if(system.args.length == 2) {
    var url = system.args[1]
    page.open(url, function(status) {
        if(status === "success" && scriptLoaded) {
            var errors = page.evaluate(function(){
                return window.myerrors
            })
            console.log("CONSOLE_ERRORS:"+JSON.stringify(errors))
        }
        else {
            console.log("SCRIPT_ERROR")
        }
    })
}
else {
    console.log("INVALID_ARGUMENT")
    phantom.exit()
}
page.onLoadFinished = function() {
    setTimeout(function() {
        phantom.exit()
    }, 1000)
}
