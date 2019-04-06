var mockServerClient = require('mockserver-client').mockServerClient;


var res =  {
    title: 'Mr'
}

var mrstring = JSON.stringify(res);

mockServerClient("localhost", 1080).mockAnyResponse({
    "httpRequest": {
        "path": "/title"
    },
    "httpResponse": {
        "body": mrstring
    }
}).then(
    function () {
        console.log("expectation created");
    },
    function (error) {
        console.log(error);
    }
);