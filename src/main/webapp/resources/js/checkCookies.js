(function are_cookies_enabled()
{
    var cookieEnabled = (navigator.cookieEnabled) ? true : false;

    if (typeof navigator.cookieEnabled == "undefined" && !cookieEnabled)
    {
        document.cookie="testcookie";
        cookieEnabled = (document.cookie.indexOf("testcookie") != -1) ? true : false;
    }
    if(!cookieEnabled){
        var complementButton = document.getElementById("complementButtonBox");
        if(complementButton){
            complementButton.style.display = "None";
        }
    }
})();
