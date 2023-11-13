   document.addEventListener('DOMContentLoaded', function() {
    	function checkIdDuplicate() {

    		var id = $("#user_id").val();
    		if(id == '' || id.length == 0) {
    			alert("ID를 입력해주세요.");
    			return;
    			}

        	//Ajax로 전송
        	$.ajax({
        		url : '/ConfirmId',
        		data : {
        			user_id : id
        		},
        		type : 'POST',
        		dataType : 'json',
        		success : function(result) {

        			if (result === false) {
        				alert("사용가능 ID 입니다.");
        			} else{
        				alert("불가능 ID 입니다.");
        				$("#user_id").val('');
        			}
        		},
        		   error: function(xhr, status, error) {
                   console.error("AJAX Error:", status, error);
                   alert("에러 발생. 더 자세한 정보는 콘솔을 확인하세요.");
                    }
        	}); //End Ajax
    	}   // End function checkIdDuplicate()

    $("#check_button").on("click", function() {
                checkIdDuplicate();
            });
        });

        function passwordCheckFunction() {
        		var userPassword1 = $('#pw').val();
        		var userPassword2 = $('#confirm_password').val();

        		if(userPassword1 != userPassword2) {
        			$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
        		} else {
        			$('#passwordCheckMessage').html('');
        		}
        	}

