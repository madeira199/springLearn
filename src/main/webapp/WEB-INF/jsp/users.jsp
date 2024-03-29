<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../layout/taglib.jsp" %>

<script type="text/javascript">
$(document).ready(function(){
	$(".triggerRemove").click(function(e){
		e.preventDefault();
		$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
		$("#modalRemove").modal();
	});
});
</script>

<table class="table table-bordered table-hover table-striped">
	<thead>
	<tr>
	<th>User name</th>
	<th>Operations</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${users}" var="user">
		<tr>
			<td>
			    <a href="<spring:url value="/users/${user.id}.html" />" >${user.name} </a>
			</td>
			<td>
			    <a href="<spring:url value="/users/remove/${user.id}.html" />" class="btn btn-danger triggerRemove">Remove</a>
			</td>
		</tr>
	</c:forEach>
	
	</tbody>
</table>


<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove Blog</h4>
      </div>
      <div class="modal-body">
        Really Remove
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>
