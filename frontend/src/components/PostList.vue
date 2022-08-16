<template>
<form class="d-flex flex-column pt-5" action="postlist" method="post">
<table class="table table-hover success ">
  <thead>
    <tr class="table-primary">
      <th scope="col">No</th>
      <th scope="col">글 제목</th>
      <th scope="col">글 내용</th>
      <th scope="col">좋아요</th>
      <th scope="col">싫어요</th>
      <th scope="col">작성자</th>
      <th scope="col">작성일</th>
      <th scope="col">수정</th>
      <th scope="col">삭제</th>
    </tr>
  </thead>
  <tbody class="table-group-divider">
    <tr>
      <th scope="row">3</th>
      <td>지영누나</td>
      <td>何書いたの？ ㅎㅎ</td>
      <td>921732</td>
      <td>1</td>
      <td>qwaszx1823</td>
      <td>2022-08-08</td>
      <td><a href="#">수정</a></td>
      <td class="text-primary">삭제</td>

    </tr>
    <tr>
      <th scope="row">2</th>
      <td>아</td>
      <td>호볼랄라지나</td>
      <td>5000000</td>
      <td>-3000</td>
      <td>hyns123</td>
      <td>2022-08-07</td>
      <td><a href="#">수정</a></td>
      <td class="text-primary">삭제</td>
    </tr>
    <tr>
      <th scope="row">1</th>
      <td>좀 더</td>
      <td>예쁘게 만들 방법이..</td>
      <td>42121</td>
      <td>5</td>
      <td>jiyoung42</td>
      <td>2022-08-06</td>
      <td><a href="#">수정</a></td>
      <td class="text-primary">삭제</td>
    </tr>
  </tbody>
</table>
</form>
</template>