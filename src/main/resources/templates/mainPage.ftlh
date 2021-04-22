<#import "parts/common.ftlh" as c>
<#import "parts/formPage.ftlh" as fp>

<@c.page>
    <div class="container">
        <div class="row mb-2">
            <div class="col">
                <input type="text" class="form-control url_site" id="url_site" name="url_site"
                       placeholder="Введите ссылку">
            </div>
            <div class="col-auto">
                <button type="button" class="btn btn-primary" onclick="submitNewSite()">Парсить
                </button>
            </div>
        </div>
    </div>
    <div class="container">
        <table class="table table-bordered mb-0">
            <thead class="thead-dark">
            <tr>
                <th scope="col">URL</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <#list websites as website>
                <tr>
                    <td>${website.url}</td>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#form_${website.id?c}">
                            Подробности
                        </button>
                        <!-- Modal -->
                        <div class="modal fade" id="form_${website.id?c}" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <@fp.tableInfo website.dataSiteDTOS/>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                    <td>
                        <a type="button" class="btn btn-primary" href="/pdf/request/${website.id?c}">
                            Сохранить
                        </a>
                    </td>
                </tr>
            </#list>
        </table>
    </div>
</@c.page>