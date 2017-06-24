<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>ESB</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-responsive.css" />" rel="stylesheet">
	<!--[if lt IE 9]>
		<script src="<c:url value="/resources/js/html5shiv.min.js" />"></script>
		<script src="<c:url value="/resources/js/respond.min.js" />"></script>
	<![endif]-->
    <style>
        body {
            background-color: #476baa;
            color: #FFF;
            background-image: -moz-linear-gradient(top, #476baa, #6786bc);
            background-image: -webkit-linear-gradient(top, #476baa, #6786bc);
            background-image: -ms-linear-gradient(top, #476baa, #6786bc);
        }
        body, html {
            height: 100%;
            overflow: hidden;
        }
        .esbicons-ESBlogo2 {
            font-size:30em;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
        img {
            width:inherit;
        }
        .error {
	color: #ff0000;
		}
        .logo_box {
            position: absolute;
            left: 50%;
            top: 50%;
           margin: -15.5% 0 0 -21%;
            text-align: center;
            display: none;
        }
        @media (max-width: 767px) {
            .logo_box {
                top: 35%;
                margin: -25% 0 0 -25%;
            }
        }
        @media (max-width: 979px) {
            .logo_box {
                margin: -25% 0 0 -41.5%;
            }
        }
        .form-group {
            width: 35%;
            margin-right:15px; 
        }
        .form-control {
            background-color: #FFFFFF;
            background-image: none;
            border: 1px solid #CCCCCC;
            border-radius: 4px;
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
            color: #555555;
            display: block;
            font-size: 14px;
            height: 36px;
            line-height: 1.42857;

            transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
            width: 100%;
        }
        .form-inline .form-control {
            display: inline-block;
            vertical-align: middle;
            width: 100%;
        }
        .btn {
            -moz-user-select: none;
            background-image: none;
            border: 1px solid rgba(0, 0, 0, 0);
            border-radius: 4px;
            cursor: pointer;
            display: inline-block;
            font-size: 12px;
            font-weight: normal;
            line-height: 1.42857;
            margin-bottom: 0;
            padding: 9px 18px;
            text-align: center;
            vertical-align: middle;
            white-space: nowrap;
        }
        @media (max-width: 767px) {
            .form-group {
                width: 100%;
                margin-right:0; 
            }
            .btn {
                width: 100%;
            }
        }
        .logo_box img {
            width: 50%;
        }
        .logo_login {
            /*display: none;*/
        }
    </style>
</head>

<body>
