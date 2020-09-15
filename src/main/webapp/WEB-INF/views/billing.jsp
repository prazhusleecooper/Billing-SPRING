<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style>
		* {
			box-sizing: border-box;
		}

		/* Add padding to containers */
		.container {
			padding: 16px;
			background-color: lightgreen;
		}

		/* Full-width input fields */
		#cus_name, #phno, #email, #search {
			width: 18%;
			padding: 15px;
			margin: 5px 0 22px 0;
			display: inline-block;
			border: none;
			background: #f1f1f1;
		}

		input[type=text] {
			width: 100%;
			padding: 15px;
			margin: 5px 0 22px 0;
			display: inline-block;
			border: none;
			background: #f1f1f1;
		}

		input[type=text]:focus, input[type=password]:focus {
			background-color: #ddd;
			outline: none;
		}

		/* Overwrite default styles of hr */
		hr {
			border: 1px solid #f1f1f1;
			margin-bottom: 25px;
		}

		/* Set a style for the submit button */
		.registerbtn {
			background-color: red;
			color: white;
			padding: 16px 20px;
			margin: 8px 0;
			border: none;
			cursor: pointer;
			width: 10%;
			opacity: 0.9;
		}

		.registerbtn:hover {
			opacity: 1;
		}

		/* Add a blue text color to links */
		a {
			color: dodgerblue;
		}

		h5 {
			color: blue;
			text-align: right;
			font-size: 20px;
			font-family: verdana;
		}

		/* Set a grey background color and center the text of the "sign in" section */
		table {
			width: 100%;
		}

		table, th, td {
			border: 1px solid black;
			border-collapse: collapse;
		}

		th, td {
			padding: 15px;
			text-align: left;
		}

		#t01 tr:nth-child(even) {
			background-color: #eee;
		}

		#t01 tr:nth-child(odd) {
			background-color: white;
		}

		#t01 th {
			background-color: black;
			color: white;
		}
		//Plus minus button
			  /* -- quantity box -- */

		  .quantity {
			  display: inline-block; }

		.quantity .input-text.qty {
			width: 35px;
			height: 39px;
			padding: 0 5px;
			text-align: center;
			background-color: transparent;
			border: 1px solid #efefef;
		}

		.quantity.buttons_added {
			text-align: left;
			position: relative;
			white-space: nowrap;
			vertical-align: top; }

		.quantity.buttons_added input {
			display: inline-block;
			margin: 0;
			vertical-align: top;
			box-shadow: none;
		}

		.quantity.buttons_added .minus,
		.quantity.buttons_added .plus {
			padding: 7px 10px 8px;
			height: 41px;
			background-color: #ffffff;
			border: 1px solid #efefef;
			cursor:pointer;}

		.quantity.buttons_added .minus {
			border-right: 0; }

		.quantity.buttons_added .plus {
			border-left: 0; }

		.quantity.buttons_added .minus:hover,
		.quantity.buttons_added .plus:hover {
			background: #eeeeee; }

		.quantity input::-webkit-outer-spin-button,
		.quantity input::-webkit-inner-spin-button {
			-webkit-appearance: none;
			-moz-appearance: none;
			margin: 0; }

		.quantity.buttons_added .minus:focus,
		.quantity.buttons_added .plus:focus {
			outline: none; }


	</style>
	<script data-require="jquery@3.1.1" data-semver="3.1.1"
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>


<div class="container">
	<h1>
		<center>Gokul Toy Shop</center>
	</h1>


	<form>

		<label for="cus_name"><b>Customer Name</b></label> <input type="text"
																  placeholder="Enter name" name="cus_name" id="cus_name" required>
		<label for="phno"><b>Phone Number</b></label> <input type="text"
															 placeholder="Enter phone number" name="phno" id="phno" required>
		<label for="email"><b>Email</b></label> <input type="text"
													   placeholder="Enter email" name="email" id="email" required>
		<hr>
		<label for="search"><b>Search Products</b></label> <input type="text"
																  name="search" id="search">


		<table>
			<tr>
				<th>Sno</th>
				<th>Product ID</th>
				<th>Product Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>GST</th>
				<th>Discount</th>
				<th>Amount</th>
				<th>Delete</th>
			</tr>
			<tr>
				<td><input type="text" name="sno" id="sno"></td>
				<td><input type="text" name="particle" id="particle">
				</td>

				<td><input type="text" name="pname" id="pname"></td>
				</td>
				<td><input type="text" name="rate" id="rate"></td>
				<td><div class="quantity buttons_added">
					<input type="button" value="-" class="minus"><input
						type="number" step="1" min="1" max="" name="quantity" value="1"
						title="Qty" class="input-text qty text" size="4" pattern=""
						inputmode=""><input type="button" value="+" class="plus"></td>
				<td><input type="text" name="gst" id="gst"></td>
				<td><input type="text" name="dis" id="dis"></td>
				<td><input type="text" name="amt" id="amt"></td>
				<td><input type="button" value="Delete Row"
						   onclick="SomeDeleteRowFunction()"></td>
			</tr>
		</table>
		<br> <br> <input type="reset" value="Clear all">
		<h5>
			<button type="submit" class="registerbtn">Save</button>
		</h5>
	</form>


	<br> <br>




</div>


</form>

<script>
	function SomeDeleteRowFunction() {
		// event.target will be the input element.
		var td = event.target.parentNode;
		var tr = td.parentNode; // the row to be removed
		tr.parentNode.removeChild(tr);
	}
	function wcqib_refresh_quantity_increments() {
		jQuery(
				"div.quantity:not(.buttons_added), td.quantity:not(.buttons_added)")
				.each(
						function(a, b) {
							var c = jQuery(b);
							c.addClass("buttons_added"),
									c
											.children()
											.first()
											.before(
													'<input type="button" value="-" class="minus" />'),
									c
											.children()
											.last()
											.after(
													'<input type="button" value="+" class="plus" />')
						})
	}
	String.prototype.getDecimals
	|| (String.prototype.getDecimals = function() {
		var a = this, b = ("" + a)
				.match(/(?:\.(\d+))?(?:[eE]([+-]?\d+))?$/);
		return b ? Math.max(0, (b[1] ? b[1].length : 0)
				- (b[2] ? +b[2] : 0)) : 0
	}),
			jQuery(document).ready(function() {
				wcqib_refresh_quantity_increments()
			}),
			jQuery(document).on("updated_wc_div", function() {
				wcqib_refresh_quantity_increments()
			}),
			jQuery(document)
					.on(
							"click",
							".plus, .minus",
							function() {
								var a = jQuery(this).closest(".quantity")
										.find(".qty"), b = parseFloat(a
										.val()), c = parseFloat(a
										.attr("max")), d = parseFloat(a
										.attr("min")), e = a.attr("step");
								b && "" !== b && "NaN" !== b
								|| (b = 0),
								"" !== c && "NaN" !== c || (c = ""),
								"" !== d && "NaN" !== d || (d = 0),
								"any" !== e && "" !== e
								&& void 0 !== e
								&& "NaN" !== parseFloat(e)
								|| (e = 1),
										jQuery(this).is(".plus") ? c
												&& b >= c ? a.val(c)
												: a
														.val((b + parseFloat(e))
																.toFixed(e
																		.getDecimals()))
												: d && b <= d ? a.val(d)
												: b > 0
												&& a
														.val((b - parseFloat(e))
																.toFixed(e
																		.getDecimals())),
										a.trigger("change")
							});
</script>

</body>
</html>
