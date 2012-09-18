$(function() {
	   $("#priceForm").validate({
	        errorClass : "validate_error",
	        highlight : function(element, errorClass) {
	            $(element).addClass(errorClass);
	        },
	        unhighlight : function(element, errorClass, validClass) {
	            $(element).removeClass(errorClass);
	        },
	        invalidHandler : function(form, validator) {
	            $.each(validator.invalid, function(key, value) {
	                alert(value);
	                $("[name='" + key + "']").focus();
	                return false;
	            });
	        },
	        rules : {
	            "productDTO.vtRatio" : {
	                required : true,
	                number : true
	            },
	            "productDTO.btRatio" : {
	                required : true,
	                number : true
	            }
	        },
	        messages : {
	            "productDTO.vtRatio" : {
	                required : "Please enter the vtRatio" ,
	                number : "This 'vtRatio' must be a digit!"
	            },
	            "productDTO.btRatio" : {
	                required : "Please enter the btRatio",
	                number : "This 'btRatio' must be a digit!"
	            }
	        },
	        errorPlacement : function(error, element) {
	        }
	    });
	   
});