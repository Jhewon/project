<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
</head>
<body>
<div class="card" style="width:600px; height : 600px; margin: 0 auto; ">
	   <form action="update.do">
	    	<c:if test="${ !empty vo.photo}">
			<img src="${vo.photo }" alt="Card image" style="width:100%">
			</c:if>
			<c:if test="${ empty vo.photo}">
			<i class="fa fa-user-circle-o" style="font-size:100px"></i>
			</c:if>
	    <div class="card-body">
	      <h4 class="card-title">이름 :  <input name="name" value="${vo.name }"></h4>
	      <h6>
	       아이디 : ${vo.id }<br>  성별 : <input name="gender" value="${vo.gender }"><br>
	       </h6>
	      <p class="card-text">생년월일 : <input name="birth" value="${vo.birth }"> <br>
	      <div style="margin-top: 20px;" class="form-group">
		 	<label for="tel">전화번호 입력 : </label>
		  	<input type="text" class="txtPhone" name="tel" id="tel" style="text-align:center;" 
		  	 maxlength="13" 
 		  	placeholder="000-0000-00000" 
			pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" required/>
			</div>
	      <br>  회원 등급 : ${vo.gradeName }<br>
	      <p class="card-text"> 가입일 : ${vo.writeDate } <br>
	      이메일 : <input name="email" value="${vo.email }"><br>  회원 상태 : ${vo.status }  </p><br>
	   </div>
	   	<button class="btn btn-dark">수정완료</button>
	   </form>
	  		 <form action="changeStatus.do"  style="width: 200px;" class="text-right">
				<input name="id" value="${vo.id }" type="hidden">
				<div class="input-group">
				<select class="form-control status" name="status" id="status" data-data="${vo.status }">
				<option  ${(vo.status == "탈퇴")?"selected":"" }>탈퇴</option>
				</select>	
				<div class="input-group-append">
				<button class="btn btn-dark " type="submit">탈퇴</button>
				</div>
				</div>
			</form>
</div>
</body>
</html>