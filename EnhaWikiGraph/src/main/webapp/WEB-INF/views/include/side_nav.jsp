<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-lg-4">
	<div class="well">
		<div class="form-horizontal">
			<c:choose>
				<c:when test="${user.email==null}">
					<script src="/laswiki/las/resource/js/crypto/jsbn.js"></script>
					<script src="/laswiki/las/resource/js/crypto/rsa.js"></script>
					<script src="/laswiki/las/resource/js/crypto/prng4.js"></script>
					<script src="/laswiki/las/resource/js/crypto/rng.js"></script>
					
					<div class="form-group">
						<label for="email" class="col-lg-2 control-label">email</label>
						<div class="col-lg-10">
							<input type="text" class="form-control input-sm" id="input_email" placeholder="enter email" 
								onkeydown="javascript:if(event.keyCode==13){$('#button_signin').click();}"/>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-lg-2 control-label">pw</label>
						<div class="col-lg-10">
							<input type="password" class="form-control input-sm" id="input_password" placeholder="enter password" 
								onkeydown="javascript:if(event.keyCode==13){$('#button_signin').click();}"/>
						</div>
						
					</div>
					
					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-10">
						<div class="checkbox">
							<label> <input type="checkbox" id="input_remember_me" />remember me
							</label>
						</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-10">
							<input type="button" id="button_signin" class="btn btn-sm" value="sign in" /> 
							<input type="button" id="button_signup" class="btn btn-sm" value="sign up" />
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="well">
						<input type="button" id="button_signout" class="btn btn-sm" value="sign out" />
					</div>
				</c:otherwise>
			</c:choose>
		</div>



		<h4>Wiki Search</h4>
		<div class="input-group">
			<input id="text_search" type="text" class="form-control" onkeydown="javascript:if(event.keyCode==13){$('#button_search').click();}"/>
				<span class="input-group-btn">
					<button id="button_search" class="btn btn-default" type="button">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</span>
		</div>
		<!-- /input-group -->
	</div>
	<!-- /well -->
	<div class="well">
		<h4>wiki</h4>
		<div class="row">
			<div class="col-lg-6">
				<ul class="list-unstyled">
					<li><a href="/laswiki/las/loadAllWikiTitle">전체문서 보기</a></li>
					<li><a href="/laswiki/las/loadRecentModifiedWiki">최근 변경문서</a></li>
				</ul>
			</div>
			<div class="col-lg-6">
				<ul class="list-unstyled">
					<li><a href="/laswiki/las/loadRandomWiki">임의 문서 보기</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<!-- /well -->
	<div class="well">
		<h4>wiki document</h4>
		<div>
			<ul class="list-unstyled">
				<c:forEach var="displayWiki" items="${displayWiki}">
					<li><a href="/laswiki/las/wiki/${displayWiki}">${displayWiki}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	
	<!-- /well -->
	<div class="well">
		<h4>blog document</h4>
		<c:if test="${isAdmin==true}">
			<h5>Administration</h5>
			<ul class="list-unstyled">
				<li><a href="/laswiki/las/editPost">post</a></li>
			</ul>
		</c:if>
	</div>
</div>

<script>
	$("#button_signin").click(function() {
		var email = $("#input_email").val();
		var password = $("#input_password").val();
		var rememberMe= $("#input_remember_me").is(":checked") == true;
		
		var rsa=new RSAKey();
		rsa.setPublic("${rsaPublicKeyModulus}", "${rsaPublicKeyExponent}");
		var encryptedEmail=rsa.encrypt(email);
		var encryptedPassword=rsa.encrypt(password);
		location.replace("/laswiki/las/login/" + encryptedEmail + "/" + encryptedPassword + "/" + rememberMe);
	});

	$("#button_signout").click(function() {
		signout();
	});

	$("#button_signup").click(function() {
		location.replace("/laswiki/las/signup");
	});
	
	$("#button_search").click(function(){
		location.replace("/laswiki/las/wiki/" + encodeURI($("#text_search").val()));
	});
</script>