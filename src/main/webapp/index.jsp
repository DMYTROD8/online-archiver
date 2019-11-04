<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Archiver</title>
    <style>
        html {
            height: 100%;
        }

        body {
            height: 100%;
            margin: 0;
            padding: 0;
            background-color: #FFCC00;
        }

        .click-btn-wrap {
            width: 400px;
            margin: 0 auto;
        }

        .click-butt {
            background-image: -moz-linear-gradient(90deg, rgb(68, 125, 188) 0%, rgb(68, 125, 188) 51%, rgb(51, 147, 207) 52%, rgb(58, 152, 209) 100%);
            background-image: -webkit-linear-gradient(90deg, rgb(68, 125, 188) 0%, rgb(68, 125, 188) 51%, rgb(51, 147, 207) 52%, rgb(58, 152, 209) 100%);
            background-image: -ms-linear-gradient(90deg, rgb(68, 125, 188) 0%, rgb(68, 125, 188) 51%, rgb(51, 147, 207) 52%, rgb(58, 152, 209) 100%);
            width: 400px;
            height: 94px;
            position: relative;
            overflow: hidden;
            cursor: pointer;
            padding-top: 12px;
        }

        .click-butt span:first-child {
            font-size: 30px;
            display: block;
            text-align: center;
        }

        .click-butt > input[type="file"] {
            position: absolute;
            top: -20px;
            left: 0;
            width: 400px;
            height: 124px;
            z-index: 2;
            opacity: 0;
            cursor: pointer !important;
        }

        .click-butt span:last-child {
            display: block;
            font-size: 15px;
            text-align: center;
        }

        .jumbotron {
            margin-bottom: 0 !important;
            padding: 2rem 1rem !important;
        }

        .click-butt > span {
            color: #fff;
            text-shadow: 1px 1px #3d74b5, 2px 2px #3d74b5, 3px 3px #3d74b5, 4px 4px #3d74b5, 5px 5px #3d74b5, 6px 6px #3d74b5, 7px 7px #3d74b5, 8px 8px #3d74b5, 9px 9px #3d74b5, 10px 10px #3d74b5, 11px 11px #3d74b5, 12px 12px #3d74b5, 13px 13px #3d74b5, 14px 14px #3d74b5, 15px 15px #3d74b5, 16px 16px #3d74b5, 17px 17px #3d74b5, 18px 18px #3d74b5, 19px 19px #3d74b5, 20px 20px #3d74b5, 21px 21px #3d74b5, 22px 22px #3d74b5, 23px 23px #3d74b5, 24px 24px #3d74b5, 25px 25px #3d74b5, 26px 26px #3d74b5, 27px 27px #3d74b5, 28px 28px #3d74b5, 29px 29px #3d74b5, 30px 30px #3d74b5, 31px 31px #3d74b5, 32px 32px #3d74b5, 33px 33px #3d74b5, 34px 34px #3d74b5, 35px 35px #3d74b5, 36px 36px #3d74b5, 37px 37px #3d74b5, 38px 38px #3d74b5, 39px 39px #3d74b5, 40px 40px #3d74b5, 41px 41px #3d74b5, 42px 42px #3d74b5, 43px 43px #3d74b5, 44px 44px #3d74b5, 45px 45px #3d74b5, 46px 46px #3d74b5, 47px 47px #3d74b5, 48px 48px #3d74b5, 49px 49px #3d74b5, 50px 50px #3d74b5, 51px 51px #3d74b5, 52px 52px #3d74b5, 53px 53px #3d74b5, 54px 54px #3d74b5, 55px 55px #3d74b5, 56px 56px #3d74b5, 57px 57px #3d74b5, 58px 58px #3d74b5, 59px 59px #3d74b5, 60px 60px #3d74b5, 61px 61px #3d74b5, 62px 62px #3d74b5, 63px 63px #3d74b5, 64px 64px #3d74b5, 65px 65px #3d74b5, 66px 66px #3d74b5, 67px 67px #3d74b5, 68px 68px #3d74b5, 69px 69px #3d74b5;
        }

        .click-butt > span {
            color: #fff;
            text-shadow: 1px 1px #3d74b5, 2px 2px #3d74b5, 3px 3px #3d74b5, 4px 4px #3d74b5, 5px 5px #3d74b5, 6px 6px #3d74b5, 7px 7px #3d74b5, 8px 8px #3d74b5, 9px 9px #3d74b5, 10px 10px #3d74b5, 11px 11px #3d74b5, 12px 12px #3d74b5, 13px 13px #3d74b5, 14px 14px #3d74b5, 15px 15px #3d74b5, 16px 16px #3d74b5, 17px 17px #3d74b5, 18px 18px #3d74b5, 19px 19px #3d74b5, 20px 20px #3d74b5, 21px 21px #3d74b5, 22px 22px #3d74b5, 23px 23px #3d74b5, 24px 24px #3d74b5, 25px 25px #3d74b5, 26px 26px #3d74b5, 27px 27px #3d74b5, 28px 28px #3d74b5, 29px 29px #3d74b5, 30px 30px #3d74b5, 31px 31px #3d74b5, 32px 32px #3d74b5, 33px 33px #3d74b5, 34px 34px #3d74b5, 35px 35px #3d74b5, 36px 36px #3d74b5, 37px 37px #3d74b5, 38px 38px #3d74b5, 39px 39px #3d74b5, 40px 40px #3d74b5, 41px 41px #3d74b5, 42px 42px #3d74b5, 43px 43px #3d74b5, 44px 44px #3d74b5, 45px 45px #3d74b5, 46px 46px #3d74b5, 47px 47px #3d74b5, 48px 48px #3d74b5, 49px 49px #3d74b5, 50px 50px #3d74b5, 51px 51px #3d74b5, 52px 52px #3d74b5, 53px 53px #3d74b5, 54px 54px #3d74b5, 55px 55px #3d74b5, 56px 56px #3d74b5, 57px 57px #3d74b5, 58px 58px #3d74b5, 59px 59px #3d74b5, 60px 60px #3d74b5, 61px 61px #3d74b5, 62px 62px #3d74b5, 63px 63px #3d74b5, 64px 64px #3d74b5, 65px 65px #3d74b5, 66px 66px #3d74b5, 67px 67px #3d74b5, 68px 68px #3d74b5, 69px 69px #3d74b5;
        }
    </style>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/open-iconic-bootstrap.css"/>
</head>
<body>
<div class="container h-100">
    <div class="row h-100">
        <div class="col-sm-8 col-md-8 col-xl-8 my-5 mx-auto">
            <h1 class="text-center">
                <span class="oi oi-file"></span>
            </h1>
            <hr>
            <div class="jumbotron">
                <p class="lead" style="text-align: center;">Select all the files you wish to archive, wait a few moments
                    and click "Download archive" to save the archive to your local drive</p>
                <hr class="my-4">
                <div id="btn-wrap" class="click-btn-wrap">
                    <div class="click-butt">
                        <span>Click here</span>
                        <input id="your-files" type="file" multiple class="form-control"
                               placeholder="">
                        <span>to select the files from your computer</span>
                    </div>
                </div>
                <div id="res-wrap" align="center">
                </div>
            </div>
            <hr>
        </div>
    </div>
</div>
<script type="text/javascript">
    var control = document.getElementById("your-files");
    var url = window.location.href + "download?file=";

    function linkCreate(response) {
        var placeToInsert = document.getElementById("res-wrap");
        document.getElementById("your-files").value = "";

        if (response.response == 0) {
            var htmlInsert = '<hr class="my-4"><a href="' + url + response.link + '">Download archive</a>';
            placeToInsert.innerHTML = "";
            placeToInsert.insertAdjacentHTML('afterbegin', htmlInsert);
        } else if (response.response == 2) {
            var htmlInsert = '<hr class="my-4"><span class="oi oi-warning">Something went wrong! Try Again.</span>';
            placeToInsert.innerHTML = "";
            placeToInsert.insertAdjacentHTML('afterbegin', htmlInsert);
        }
    }

    function waitLinkCreate() {
        var placeToInsert = document.getElementById("res-wrap");
        placeToInsert.innerHTML = "";
        var htmlInsert = '<hr class="my-4"><span class="oi oi-clock"></span>';
        placeToInsert.insertAdjacentHTML('afterbegin', htmlInsert);
    }

    control.addEventListener("change", function (event) {
        waitLinkCreate();
        var i = 0;
        var files = control.files;
        var len = files.length;
        var req = new FormData();

        for (; i < len; i++) {
            req.append("file", control.files[i]);
        }

        var xhr = new XMLHttpRequest();
        xhr.onload = function () {
        }
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                linkCreate(xhr.response);
            }
        }
        xhr.open("post", "/upload", true);
        xhr.responseType = "json";
        xhr.send(req);

    }, false);
</script>
</body>
</html>