<!DOCTYPE html>
<html>
<head>
<title>ChatAno</title>
<link rel="stylesheet" type="text/css" href="/resources/css/mystyle.css">
<link rel="stylesheet" href="/resources/css/bootstrap_min_v3_3_7.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/bootstrap_min_v3_3_7.js"></script>
<meta name="keywords" content="chat, stranger, anonymous, fun, with">
<meta name="description" content="Anonymous stranger chat">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-9 col-xs-12 chatbox" style="background-color: lavender;">
				<table border="0" width="100%">
					<tr>
					<tr>
						<td width="80%"><p class="cls_information"
								id='id_information'>Connecting to server...</p></td>
						<td width="20%" align="right"><button type="button"
								class="btn btn-success" id="btn_command">Connect</button></td>
					</tr>
				</table>
				<div class="col-xs-12 chatlogs chat" id="id_chat_logs">
					<div class="chat-history" id="id_chat_history">
						<!--div class="message me-message float-right">heloegqwgqwg q gqw g w gqw</div>
						<div class="message you-message float-left">gwqegqw qg qwg qw g w g wqg </div>
						<div class="message me-message float-right">heloegqwgqwg q gqw g w gqw</div-->
					</div>
				</div>
				<table class="col-xs-12" border="0" width="100%">
					<tr>
						<td width="100%"><textarea class="form-control" rows="1"
								placeholder="Please type your message here..." id="txt_textbox"
								cols="90%" disabled></textarea></td>
						<td width="20%"><button class="btn btn-success" cols="10%"
								id="btn_send">></button></td>
					</tr>
				</table>
			</div>
			<div class="col-sm-3 col-xs-12 ad"
				style="background-color: lightblue;">ad</div>
		</div>
	</div>

	<div id="ticket" style="display: none;">${ticket}</div>
	<script type="text/javascript">
		var ws = null;
		window.onload = function() {
			;
			txt_textbox = document.getElementById("txt_textbox");
			btn_command = document.getElementById("btn_command");
			btn_send = document.getElementById("btn_send");
			elm_information = document.getElementById("id_information");
			div_chat_history = document.getElementById("id_chat_history");
			div_chat_logs = document.getElementById("id_chat_logs");
			txt_textbox.addEventListener("keyup", keyupHandler, false);
			btn_command.addEventListener('click', commandHandler, false);
			btn_send.addEventListener('click', sendMessageHandler, false);
			ticket = document.getElementById("ticket").innerHTML;
			connect();
		};

		function keyupHandler(event) {
			event.preventDefault();
			if (event.keyCode == 13) {
				addChatSelf(document.getElementById("txt_textbox"));
			}
		}

		function messageHandler(data) {
			console.log(data.data);
			var obj = JSON.parse(data.data);
			if (null != obj) {
				if (obj.from == "System") {
					handleSystemMessage(obj.message);
				} else {
					addChatPartner(obj.message);
				}
			}
		}

		function sendMessageHandler() {
			addChatSelf(document.getElementById("txt_textbox"));
		}

		function handleSystemMessage(message) {
			console.log("into handleSystemMessage " + message);

			if (message == "AuthenticationSuccessful") {
				elm_information.innerHTML = "Looking for partner... ";
			} else if (message == "AuthenticationUnsuccessful") {
				elm_information.innerHTML = "Authentication failed.";
			} else if (message == "PartnerDisconnection") {
				console.log("PartnerDisconnection");
				elm_information.innerHTML = "Partner disconnected.";
				txt_textbox.value = "";
				txt_textbox.disabled = true;
				btn_command.innerHTML = "Connect";
				disconnect();
			} else if (message == "PartnerConnection") {
				console.log("PartnerConnection");
				txt_textbox.disabled = false;
				txt_textbox.focus();
				elm_information.innerHTML = "Connected with someone."
				btn_command.innerHTML = "Disconnect";
			}
		}

		function commandHandler() {
			console.log("into commandHandler  " + btn_command.innerHTML)
			if (btn_command.innerHTML == "Disconnect") {
				elm_information.innerHTML = "You've disconnected";
				txt_textbox.value = "";
				txt_textbox.disabled = true;
				btn_command.innerHTML = "Connect";
				disconnect();
			} else if (btn_command.innerHTML == "Connect") {
				location.reload();
			}
		}

		function addChatSelf(msg) {
			if (null != msg.value && msg.value.trim() != "") {
				div_new = '<div class="message me-message float-right">'
						+ msg.value + '</div>';
				div_chat_history.innerHTML = div_chat_history.innerHTML
						+ div_new;
				ws.send(msg.value);
				msg.value = "";
				div_chat_logs.scrollTop = div_chat_logs.scrollHeight;
			} else {
				msg.value = "";
			}
		}

		function addChatPartner(msg) {
			if (null != msg && msg.trim() != "") {

				div_new = '<div class="message you-message float-left">' + msg
						+ '</div>';
				div_chat_history.innerHTML = div_chat_history.innerHTML
						+ div_new;
				msg.value = "";
				div_chat_logs.scrollTop = div_chat_logs.scrollHeight;
			} else {
				msg.value = "";
			}
		}

		function onOpen(evt) {
			ws.send('sGHFfTKW34fObsRNwLpW-' + ticket);
		}

		function connect() {
			disconnect();
			ws = new WebSocket('ws://chatano.tk:8085/ano');
			ws.onopen = function(evt) {
				onOpen(evt);
			};
			ws.onmessage = function(data) {
				messageHandler(data)
			}
		}

		function disconnect() {
			if (ws != null) {
				ws.close();
				ws = null;
			}
		}
	</script>
</body>
</html>