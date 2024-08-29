<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
</head>
<body>
<div class="container">
	<h2>상품등록</h2>
	<form action="write.do" method="post" enctype="multipart/form-data">
		<input name="perPageNum" value = "${param.perPageNum }" type="hidden">
		 
		 <div class="form-group">
		    
		    
		    <label for="title">상품명</label>
			<input id="title" name="title" required 
				class="form-control" maxlength="100"
				pattern="^[^ .].{2,99}$"
				title="상품명을 입력해주세요."
				placeholder="상품명을 입력해주세요.">
		  </div>
		  
		  
		  <div class="form-group">
		    <label for="content">상품내용</label>
			<textarea class="form-control" id="content" name="content"  required
			rows="7" placeholder="상품내용을 입력해주세요."></textarea>
		  </div>
		  
		  
		  <div class="form-group">
		    <label for="price">가격(숫자만 입력해주세요)</label>
			<input id="price" name="price" required 
				class="form-control"  placeholder="가격을 입력하세요">
		  </div>


		 <div class="form-group">
		    <label for="imageFile">첨부 이미지</label>
			<input id="imageFile" name="imageFile" required 
				class="form-control" type="file">
		  </div>
		  
		  
		 
		  
		  
		<button class="btns">등록</button>
		<button type="reset" class="btns">다시입력</button>
		<button type="button" onclick="history.back();" class="btns">취소</button>
	</form>
</div>
</body>
</html>