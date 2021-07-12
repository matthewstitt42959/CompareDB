$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:features/compareMySql.feature");
formatter.feature({
  "name": "Attempt to compare MySQL databases",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Connect to two databases and compare results",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Connection to Database \u003cfirst\u003e, \u003csecond\u003e, \u003cusername\u003e, \u003cpassword\u003e",
  "keyword": "Given "
});
formatter.step({
  "name": "\u003ctable\u003e is compared to \u003ctable\u003e",
  "keyword": "When "
});
formatter.step({
  "name": "Results should match",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "first",
        "second",
        "username",
        "password"
      ]
    },
    {
      "cells": [
        "orgchart_api",
        "orgchart_api_copy",
        "mstitt",
        "root"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Connect to two databases and compare results",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Connection to Database orgchart_api, orgchart_api_copy, mstitt, root",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "\u003ctable\u003e is compared to \u003ctable\u003e",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Results should match",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});