<template>
<form class="d-flex flex-column pt-5" action="report" method="post">
<h2>신고 관리</h2>
<table class="table table-hover">
  <thead>
    <tr class="table-header">
      <th scope="col">No</th>
      <th scope="col">신고 제목</th>
      <th scope="col">신고 내용</th>
      <th scope="col">신고 날짜</th>
      <th scope="col">수정</th>
      <th scope="col">삭제</th>
    </tr>
  </thead>
  <tbody class="table-group-divider">
    <tr>
      <th scope="row">3</th>
      <td>지영누나</td>
      <td>신고 ㅎㅎ</td>
      <td>2022-08-10</td>
      <td><a href="#">수정</a></td>
      <td class="text-primary">삭제</td>

    </tr>
    <tr>
      <th scope="row">2</th>
      <td>아이고난1</td>
      <td>아이고난2</td>
      <td>2022-08-09</td>
      <td><a href="#">수정</a></td>
      <td class="text-primary">삭제</td>
    </tr>
    <tr>
      <th scope="row">1</th>
      <td>로또</td>
      <td>1등 당첨 주세요</td>
      <td>2022-08-08</td>
      <td><a href="#">수정</a></td>
      <td class="text-primary">삭제</td>
    </tr>
  </tbody>
</table>
</form>
</template>