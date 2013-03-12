<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<html>
<head>
	<link rel='stylesheet' type='text/css' media='screen' href='<c:url value="./resources/css/style.css"/>'/>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery-1.6.4.min.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.jstree.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.hotkeys.js"/>'></script>

	<title>User Records</title>
	
	<script type='text/javascript'>
	$(function () {
		// TO CREATE AN INSTANCE
		// select the tree container using jQuery
		$("#demo1")
			// call `.jstree` with the options object
			.jstree({
				// the `plugins` array allows you to configure the active plugins on this instance
				"plugins" : ["themes","html_data","ui","crrm","hotkeys"],
				// each plugin you have included can have its own config object
				"core" : { "initially_close" : [ "phtml_1" ] }
				// it makes sense to configure a plugin only if overriding the defaults
			})
			// EVENTS
			// each instance triggers its own events - to process those listen on the container
			// all events are in the `.jstree` namespace
			// so listen for `function_name`.`jstree` - you can function names from the docs
			.bind("loaded.jstree", function (event, data) {
				// you get two params - event & data - check the core docs for a detailed description
			});
		// INSTANCES
		// 1) you can call most functions just by selecting the container and calling `.jstree("func",`
		setTimeout(function () { $("#demo1").jstree("set_focus"); }, 500);
		// with the methods below you can call even private functions (prefixed with `_`)
		// 2) you can get the focused instance using `$.jstree._focused()`. 
		setTimeout(function () { $.jstree._focused().select_node("#phtml_1"); }, 1000);
		// 3) you can use $.jstree._reference - just pass the container, a node inside it, or a selector
		setTimeout(function () { $.jstree._reference("#phtml_1").close_node("#phtml_1"); }, 1500);
		// 4) when you are working with an event you can use a shortcut
		$("#demo1").bind("open_node.jstree", function (e, data) {
			// data.inst is the instance which triggered this event
			data.inst.select_node("#phtml_2", true);
		});
		setTimeout(function () { $.jstree._reference("#phtml_1").open_node("#phtml_1"); }, 2500);
	});

	</script>
</head>

<body>
	<div id="demo1" class="demo" style="height:100px;">
	<ul>
		<li id="phtml_1">
			<a href="#">Root node 1</a>
			<ul>
				<li id="phtml_2">
					<a href="#">Child node 1</a>
				</li>
				<li id="phtml_3">
					<a href="#">Child node 2</a>
				</li>
			</ul>
		</li>
		<li id="phtml_4">
			<a href="#">Root node 2</a>
		</li>
	</ul>
</div>
	
</body>
</html>