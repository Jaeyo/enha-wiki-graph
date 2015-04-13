<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>

<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="/js/vis.min.js"></script>

<link href="/css/vis.min.css" rel="stylesheet" />

<title>${testword}</title>

</head>
<body>

<p>${testword}</p>

<div id="mynetwork"></div>

<script type="text/javascript">
/**
var nodes = [
	{ id : 1, label : 'Node 1' },
	{ id : 2, label : 'Node 2' },
	{ id : 3, label : 'Node 3' },
	{ id : 4, label : 'Node 4' },
	{ id : 5, label : 'Node 5' }
];
*/

var nodes = new vis.DataSet();
nodes.add([
	{ id : 1, label : 'Node1'},
	{ id : 2, label : 'Node2'},
	{ id : 3, label : 'Node3'},
	{ id : 4, label : 'Node4'},
	{ id : 5, label : 'Node5'},
]);

var edges = new vis.DataSet();
edges.add([
	{ from : 1, to : 2 },
	{ from : 1, to : 3 },
	{ from : 2, to : 4 },
	{ from : 2, to : 5 },
]);

var container = document.getElementById('mynetwork');
var data = {
	nodes : nodes,
	edges : edges
};
var options = {
	width : '100%',
	height : '400px',
	edges : {
		color : 'red',
		width : 2,
		style : 'arrow'
	},
	nodes : {
		shape : 'box',
		radius : 24
	}
};

var network = new vis.Network(container, data, options);
</script>

</body>
</html>
