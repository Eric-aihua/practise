<?php include 'include/doc_head.php' ?>
<?php include 'include/nav_top1.php' ?>
                <ul class="nav navbar-nav">
                    <li><a href="administration.php">用户</a></li>
                    <li><a href="administration_usergroup.php">用户组</a></li>
                    <li><a href="administration_adminshell.php">Admin Shell</a></li>
                    <li class="active"><a href="administration_scheduler.php">计划任务</a></li>
                    <li><a href="administration_auditlog.php">审查日志</a></li>
                </ul>
<?php include 'include/nav_top2.php' ?>
<?php include 'include/nav_side8.php' ?>
    <div class="container-fluid ESB-Layout ESB-Content">
        <div class="row"></div>
        <div class="row">
            <div class="col-md-12">
                <div class="table-header2">
                    计划任务
                </div>                    
            </div>
        </div>
        <div class="row toolbar">
            <div class="col-md-12">
                <form role="form" class="form-inline">
                    <div class="form-group">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default">新建计划任务</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail2" class="sr-only">输入关键字过滤</label>
                        <input type="email" placeholder="输入关键字过滤" id="exampleInputEmail2" class="form-control input-filter">
                        <span class="esbicon esbicons-search"></span>
                    </div>
                    <div class="form-group">
                        <span class="esbicon esbicons-help"></span>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th class="table-td-border-blue table-th-ids">#</th>
                            <th>名称</th>
                            <th>脚本</th>
                            <th>计划任务表达式</th>
                            <th>描述</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="table-td-border-blue">1</td>
                            <td><a href="administration_scheduler_edit.php">Cleanup Tracking Job</a></td>
                            <td>Cleanup Tracking DB</td>
                            <td>0 0 0 * * ?</td>
                            <td>Cleanup Tracking DB</td>
                        </tr>
                        <tr>
                            <td class="table-td-border-blue">1</td>
                            <td><a href="administration_scheduler_edit.php">Cleanup Tracking Job</a></td>
                            <td>Cleanup Tracking DB</td>
                            <td>0 0 0 * * ?</td>
                            <td>Cleanup Tracking DB</td>
                        </tr>
                        <tr>
                            <td class="table-td-border-blue">1</td>
                            <td><a href="administration_scheduler_edit.php">Cleanup Tracking Job</a></td>
                            <td>Cleanup Tracking DB</td>
                            <td>0 0 0 * * ?</td>
                            <td>Cleanup Tracking DB</td>
                        </tr>
                        <tr>
                            <td class="table-td-border-blue">1</td>
                            <td><a href="administration_scheduler_edit.php">Cleanup Tracking Job</a></td>
                            <td>Cleanup Tracking DB</td>
                            <td>0 0 0 * * ?</td>
                            <td>Cleanup Tracking DB</td>
                        </tr>
                        <tr>
                            <td class="table-td-border-blue">1</td>
                            <td><a href="administration_scheduler_edit.php">Cleanup Tracking Job</a></td>
                            <td>Cleanup Tracking DB</td>
                            <td>0 0 0 * * ?</td>
                            <td>Cleanup Tracking DB</td>
                        </tr>
                        <tr>
                            <td class="table-td-border-blue">1</td>
                            <td><a href="administration_scheduler_edit.php">Cleanup Tracking Job</a></td>
                            <td>Cleanup Tracking DB</td>
                            <td>0 0 0 * * ?</td>
                            <td>Cleanup Tracking DB</td>
                        </tr>
                        <tr>
                            <td class="table-td-border-blue">1</td>
                            <td><a href="administration_scheduler_edit.php">Cleanup Tracking Job</a></td>
                            <td>Cleanup Tracking DB</td>
                            <td>0 0 0 * * ?</td>
                            <td>Cleanup Tracking DB</td>
                        </tr>
                        <tr>
                            <td class="table-td-border-blue">1</td>
                            <td><a href="administration_scheduler_edit.php">Cleanup Tracking Job</a></td>
                            <td>Cleanup Tracking DB</td>
                            <td>0 0 0 * * ?</td>
                            <td>Cleanup Tracking DB</td>
                        </tr>
                        <tr>
                            <td class="table-td-border-blue">1</td>
                            <td><a href="administration_scheduler_edit.php">Cleanup Tracking Job</a></td>
                            <td>Cleanup Tracking DB</td>
                            <td>0 0 0 * * ?</td>
                            <td>Cleanup Tracking DB</td>
                        </tr>
                        <tr>
                            <td class="table-td-border-blue">1</td>
                            <td><a href="administration_scheduler_edit.php">Cleanup Tracking Job</a></td>
                            <td>Cleanup Tracking DB</td>
                            <td>0 0 0 * * ?</td>
                            <td>Cleanup Tracking DB</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-12">
            <ul class="pagination">
                <li class="disabled"><a href="#"><span class="esbicon esbicons-arrow-left5"></span></a></li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#"><span class="esbicon esbicons-arrow-right5"></span></a></li>
            </ul>
        </div>
    </div>
<?php include 'include/doc_foot.php' ?>