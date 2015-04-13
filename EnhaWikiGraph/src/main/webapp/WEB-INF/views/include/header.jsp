<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/laswiki/las/">laswiki</a>
		</div>

		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<c:forEach var="menu" items="${headerMenu}">
					<c:choose>
						<c:when test="${menu.getClass().name=='org.laswiki.common.bean.bootstrap.LinkBean'}">
							<li><a href="${menu.link}">${menu.name}</a></li>
						</c:when>
						<c:when test="${menu.getClass().name=='org.laswiki.common.bean.bootstrap.DropdownBean'}">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">${menu.name}<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<c:forEach var="linkItem" items="${menu.linkList}">
										<li><a href="${linkItem.link}">${linkItem.name}</a></li>
									</c:forEach>
								</ul>
							</li>
						</c:when>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	</div>
</nav>