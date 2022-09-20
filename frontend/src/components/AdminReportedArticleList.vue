<template>
    <div class="admin-report d-flex flex-column align-items-center w-100">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">신고된 횟수</th>
                    <th scope="col">글 제목</th>
                    <th scope="col">글쓴이</th>
                    <th scope="col">조회수</th>
                    <th scope="col">삭제</th>
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
