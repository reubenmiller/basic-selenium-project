#!/bin/sh

sleep 10

curl -X POST 'http://selenoid:4444/wd/hub/session' -d '{ 
    "desiredCapabilities":{
        "browserName":"chrome",
        "version": "61.0",
        "platform":"ANY",
        "enableVNC": true,
        "name": "this.test.is.launched.by.curl",
        "sessionTimeout": "1m"
    }
}'

curl -X POST 'http://selenoid:4444/wd/hub/session' -d '{ 
    "desiredCapabilities":{
        "browserName":"firefox",
        "version": "57.0",
        "enableVNC": true,
        "platform":"ANY",
        "name": "this.test.is.launched.by.curl",
        "sessionTimeout": "30s"
    }
}'
