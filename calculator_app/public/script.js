


var input = $("input[type = 'text']");

for(var i = 0; i < 10; i++) {
	$("#btn_" + i).on("click", function() {
		input.val(input.val() + $(this).val());
	})
}

$("#btn_plus").on("click", function() {
	input.val(input.val() + " + ");
});

$("#btn_minus").on("click", function() {
	input.val(input.val() + " - ");
});

$("#btn_mul").on("click", function() {
	input.val(input.val() + " * ");
});

$("#btn_equal").on("click", function() {
	values = input.val().split(" ");
	var result = 0;

	values[0] = Number(values[0]);
	values[2] = Number(values[2]);

	if(values[1] == '+') 
		result = values[0] + values[2];

	else if (values[1] == '-') 
		result = values[0] - values[2];		

	else if (values[1] == '*') 
		result = values[0] * values[2];

	else if (values[1] == '/')
		result = values[0] / values[2];

	input.val(result);
});

$("#btn_div").on("click", function() {
	input.val(input.val() + " / ");
});

$("#btn_clear").on("click", function() {
	input.val("");
})


