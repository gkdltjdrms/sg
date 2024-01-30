<!-- table.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 생략된 부분 -->
<tbody>
    <!-- 이 부분이 수정되었습니다. -->
    <c:choose>
        <c:when test="${not empty listOfPosts}">
            <c:forEach items="${listOfPosts}" var="post">
                <tr>
                    <td>${post.seq}</td>
                    <td>${post.mem_id}</td>
                    <td>${post.mem_name}</td>
                    <td>
                        <a href="listinfo?seq=${post.seq}">${post.board_subject}</a>
                    </td>
                    <td class="date-cell">${(post.reg_date)}</td>
                    <td class="date-cell">${(post.upt_date)}</td>
                    <td>${post.view_cnt}</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <!-- 데이터가 없는 경우 메시지 추가 -->
            <tr>
                <td colspan="7" class="no-data-message">데이터가 없습니다.</td>
            </tr>
        </c:otherwise>
    </c:choose>
</tbody>

