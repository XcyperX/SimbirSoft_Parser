<#macro tableInfo datasites>
    <div class="container">
    <table class="table table-bordered mb-0">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Слово</th>
            <th scope="col">Повторений</th>
        </tr>
        </thead>
        <tbody>
        <#list datasites as data>
            <tr>
                <td>${data.word}</td>
                <td>${data.amount}</td>
            </tr>
        </#list>
    </table>
    </div>
</#macro>