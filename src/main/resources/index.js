window.myerrors = []
window.onerror = function(message, source, lineno, colno, error) {
    var errorObj = {errorMessage:message, fileName : source, lineNumber : lineno, colNumber : colno}
    window.myerrors.push(errorObj)
    return true
}
