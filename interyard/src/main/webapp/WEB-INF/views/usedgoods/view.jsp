<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.NumberFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세보기</title>
<style type="text/css">
.btns {
    margin-bottom: 20px; /* 버튼 아래 여백 */
    background-color: #000; /* 배경색 검정 */
    color: #fff; /* 텍스트 색상 흰색 */
    border: none; /* 테두리 없음 */
    padding: 10px 20px; /* 위아래 10px, 좌우 20px 여백 */
    text-align: center; /* 텍스트 가운데 정렬 */
    text-decoration: none !important; /* 텍스트 밑줄 없음 (강제로 적용) */
    display: inline-block; /* 버튼을 인라인 블록으로 표시 */
    font-size: 16px; /* 글자 크기 */
    border-radius: 5px; /* 모서리 둥글게 */
    transition: background-color 0.3s, transform 0.2s; /* 배경색과 트랜스폼 애니메이션 */
}

.btns:hover {
    background-color: #333; /* 호버 시 배경색 변경 */
    transform: scale(1.05); /* 호버 시 크기 확대 */
}

.btns:active {
    background-color: #555; /* 클릭 시 배경색 변경 */
    transform: scale(0.95); /* 클릭 시 크기 축소 */
}

.carousel-inner img {
    width: 70%;
    height: 70%;
    object-fit: cover;
}
        
.carousel-item.active {
    display: flex;
    justify-content: center;
    align-items: center;
}

.carousel-control-prev-icon,
.carousel-control-next-icon {
    background-color: transparent; /* 배경색 투명 */
    background-size: 100% 100%;
}

.carousel-control-prev-icon {
    transform: scaleX(-1);
    background-image: url('data:image/svg+xml;charset=UTF-8,%3csvg xmlns=%22http://www.w3.org/2000/svg%22 fill=%22%23000%22 viewBox=%220 0 8 8%22%3e%3cpath d=%22M4.5 0L3.5 1 6 3.5 0 3.5 0 4.5l6 0-2.5 2.5 1 1 4-4-4-4z%22/%3e%3c/svg%3e');
}

.carousel-control-next-icon {
    transform: scaleX(-1);
    background-image: url('data:image/svg+xml;charset=UTF-8,%3csvg xmlns=%22http://www.w3.org/2000/svg%22 fill=%22%23000%22 viewBox=%220 0 8 8%22%3e%3cpath d=%22M3.5 0L4.5 1 2 3.5 8 3.5 8 4.5l-6 0 2.5 2.5-1 1-4-4 4-4z%22/%3e%3c/svg%3e');
}

</style>
</head>
<body>
<div class="container mt-5">
    <h1>상품상세보기</h1>
    <div class="card mb-3">
        <div class="card-header">
            <b>${vo.title}</b> <!-- 번호 제거 -->
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <div id="demo" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ul class="carousel-indicators">
                            <li data-target="#demo" data-slide-to="0" class="active"></li>
                            <li data-target="#demo" data-slide-to="1"></li>
                            <li data-target="#demo" data-slide-to="2"></li>
                        </ul>
                        
                        <!-- The slideshow -->
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img class="img-fluid" src="${pageContext.request.contextPath}${vo.image}" alt="Chania"  width="400" height="400">
                            </div>
                            <div class="carousel-item">
                                <img class="img-fluid" src="${pageContext.request.contextPath}${vo.image}" alt="Chania"  width="400" height="400">
                            </div>
                            <div class="carousel-item">
                                <img class="img-fluid" src="${pageContext.request.contextPath}${vo.image}" alt="Chania"  width="400" height="400">
                            </div>
                        </div>
                        
                        <!-- Left and right controls -->
                        <a class="carousel-control-prev" href="#demo" data-slide="prev">
                            <span class="carousel-control-prev-icon"></span>
                        </a>
                        <a class="carousel-control-next" href="#demo" data-slide="next">
                            <span class="carousel-control-next-icon"></span>
                        </a>
                    </div>
                </div>
                <div class="col-md-6">
                    <h5 class="card-title">${vo.title}</h5>
                    <p class="card-text"><pre>${vo.content}</pre></p>
                    <p class="card-text"><strong></strong></p>
                    <p class="card-text title">
                        <fmt:formatNumber value="${vo.price}" pattern="#,###"/>원
                    </p>
                    <p class="card-text"><strong>상태: ${vo.ugstatus}</strong></p> <!-- 상태 표시 부분 -->
                    <a href="/message/writeForm.do?title=${vo.title}&id=${vo.id}&page=${param.page}
                    &perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}" class="btns">메시지</a>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${!empty login && login.id == vo.id}">
        <a href="updateForm.do?ugno=${param.ugno}&page=${param.page}&no=${param.ugno}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}" 
           class="btns" title="수정합니다" data-toggle="tooltip" data-placement="top" id="updateBtn">수정</a>
        <a class="btns" id="deleteBtn" href="delete.do?ugno=${vo.ugno}&deleteFileName=${vo.image}&perPageNum=${param.perPageNum}&no=${param.ugno}">삭제</a>
    </c:if>
        <a href="list.do?page=${param.page }&perPageNum=${param.perPageNum}
						&key=${param.key}&word=${param.word}" class="btns">리스트</a>
    
    <div id="reply"></div>
    <!-- 댓글 처리 시작 -->
    <jsp:include page="reply.jsp" />
    <!-- 댓글 처리 끝 -->
</div>

<!-- The Modal -->
<div class="modal fade" id="updateModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">바꿀 이미지 선택하기</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <form action="update.do" method="post" enctype="multipart/form-data">
                <input name="ugno" value="${vo.ugno}" type="hidden">
                <input name="deleteFileName" value="${vo.image}" type="hidden">
                <input name="page" value="${param.page}" type="hidden">
                <input name="perPageNum" value="${param.perPageNum}" type="hidden">
                <input name="key" value="${param.key}" type="hidden">
                <input name="word" value="${param.word}" type="hidden">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="imageFile">첨부 이미지</label>
                        <input id="imageFile" name="imageFile" required class="form-control" type="file">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btns">바꾸기</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
