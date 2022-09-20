<template>
    <div class="admin-user d-flex flex-column align-items-center w-100">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">회원번호</th>
                    <th scope="col">이메일</th>
                    <th scope="col">이름</th>
                    <th scope="col">성별</th>
                    <th scope="col">유저 소개</th>
                    <th scope="col">탈퇴시키기</th>
                </tr>
            </thead>
            <tbody>
                <!-- <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr> -->
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </nav>
    </div>
</template>
<script>
    export default {};
</script>
<style lang="sass">
    thead
        text-align: center
</style>
