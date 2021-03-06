<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Pit A Pet</title>
    <link rel="icon" type="image/png" sizes="152x152" href="${ path }/images/logo.png" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link rel="stylesheet" href="${ path }/css/plugin/jquery.fullPage.css" />
    <link rel="stylesheet" href="${ path }/css/passwordEdit.css" />
    <link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/91b5983e4b.js" crossorigin="anonymous"></script>
    <script src="${ path }/js/plugin/jquery-3.6.0.min.js"></script>
    <script src="${ path }/js/plugin/jquery.fullPage.js"></script>
    <script src="${ path }/js/passwordEdit.js"></script>
  </head>
  <body>
    <header id="header">
      <logo class="header__logo">
        <img src="${ path }/images/logo.png" alt="logo" class="logo__img" />
      </logo>
      <ul class="header__menu">
        <li data-menuanchor="section1"><a href="${ path }/">Home</a></li>
        <li data-menuanchor="section2"><a href="${ path }/product/product">Product</a></li>
        <li data-menuanchor="section3"><a href="${ path }/community/list">Community</a></li>
        <li data-menuanchor="section4"><a href="${ path }/organization/organization">Organization</a></li>
        <li data-menuanchor="section5"><a href="${ path }/member/mypage">Mypage</a></li>
        <li data-menuanchor="section6"><a href="${ path }/logout">Logout</a></li>
      </ul>
    </header>
    <main id="fullpage">
      <!-- Section 1 MyPage -->
      <section class="section">
        <main id="main">
          <section class="main_user">
            <form class="form sign-up" name="memberEnrollFrm" id="enrollFrm" action="${ path }/member/doPasswordEdit" enctype="multipart/form-data" method="post">
              <!--  <img class="profileimage" src="./img/profile.jpg" />
              <div class="filebox">
                <label for="ex_file">????????? ??????</label>
                <input type="file" name="profile" id="ex_file" title="???????????????" value="" required />
              </div>-->
              <div class="input-group up">
                <i class="fas fa-lock"></i>
                <input type="password" name="oldPw" id="oldPw" placeholder="?????? ????????????" required />
              </div>
              <div class="input-group up">
                <i class="fas fa-lock"></i>
                <input type="password" name="newPw" id="newPw" onchange="check_pw()" placeholder="???????????? (??????????????? ????????? 8??? ??????)" required />
              </div>
              <div class="input-group up">
                <i class="fas fa-lock"></i>
                <input type="password" name="newPwCheck" id="newPwCheck" onchange="check_pw()" placeholder="???????????? ??????" required />&nbsp;<span id="check"></span> 
              </div>
              
              <button class="btn sign-ups">???????????? ??????</button>
            </form>
          </section>
        </main>
      </section>
    </main>
    <script>
	// ???????????? ??????	
	function check_pw(){
	       var pw = document.getElementById('newPw').value;
	       var SC = ["!","@","#","$","%", "^", "&", "*", "(", ")", "_", "+", "-", "`", "~", "="];
	       var check_SC = 0;
	       
	 // ???????????? ?????? ??????
	       if(pw.length < 8){
	           alert('??????????????? 8?????? ????????? ?????? ???????????????.');
	           document.getElementById('newPw').value='';
	       }
	 // ???????????? ??????
	       for(var i=0;i<SC.length;i++){
	           if(pw.indexOf(SC[i]) != -1){
	               check_SC = 1;
	           }
	       }
	       if(check_SC == 0){
	           window.alert('??????????????? ????????? ?????? ????????????.')
	           document.getElementById('newPw').value='';
	       }
	 // ???????????? ???????????? ??????
	       if(document.getElementById('newPw').value !='' && document.getElementById('newPwCheck').value!=''){
	           if(document.getElementById('newPw').value==document.getElementById('newPwCheck').value){
	               document.getElementById('check').innerHTML='??????????????? ???????????????.'
	               document.getElementById('check').style.color='green';
	           }
	           else{
	               document.getElementById('check').innerHTML='??????????????? ???????????? ????????????.';
	               document.getElementById('check').style.color='red';
	           }
	       }
	   }
    </script>
  </body>
</html>