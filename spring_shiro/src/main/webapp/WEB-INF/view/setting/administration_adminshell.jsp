<?php include 'include/doc_head.php' ?>
<?php include 'include/nav_top1.php' ?>
                <ul class="nav navbar-nav">
                    <li><a href="administration.php">用户</a></li>
                    <li><a href="administration_usergroup.php">用户组</a></li>
                    <li class="active"><a href="administration_adminshell.php">Admin Shell</a></li>
                    <li><a href="administration_scheduler.php">计划任务</a></li>
                    <li><a href="administration_auditlog.php">审查日志</a></li>
                </ul>
<?php include 'include/nav_top2.php' ?>
<?php include 'include/nav_side8.php' ?>
    <div class="container-fluid ESB-Layout ESB-Content">
        <div class="row"></div>
        <div class="row">
            <div class="col-md-12">
                <div class="table-header2">
                    Admin Shell
                </div>                    
            </div>
        </div>
        <div class="col-md-12">
            <h5>输入或复制一个Groovy脚本并在服务器上执行。返回的值将显示下方的区域。</h5>
            <div class="panel panel-primary">
                <div class="panel-heading">提示</div>
                <div class="panel-body">
                    <p><strong></strong>Spring's context is available as an 'applicationContext' variable</p>
                    <p><strong></strong>Logger (commons-logging) is available as a 'log' variable</p>
                    <p><strong></strong>Only String return values are supported (or null).</p>
                </div>
            </div>
        </div>
        <form role="form">
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <textarea class="form-control" rows="10"></textarea>
                    </div>
                </div>
                <div class="col-md-4">
                    <h5>已保存的脚本</h5>
                    <table class="table table-hover">
                        <tbody>
                            <tr>
                                <td class="table-th-W30"><span class="esbicon esbicons-file-xml"></span></td>
                                <td><a href="#">ScriptName</a></td>
                            </tr>
                            <tr>
                                <td class="table-th-W30"><span class="esbicon esbicons-file-xml"></span></td>
                                <td><a href="#">ScriptName</a></td>
                            </tr>
                            <tr>
                                <td class="table-th-W30"><span class="esbicon esbicons-file-xml"></span></td>
                                <td><a href="#">ScriptName</a></td>
                            </tr>
                            <tr>
                                <td class="table-th-W30"><span class="esbicon esbicons-file-xml"></span></td>
                                <td><a href="#">ScriptName</a></td>
                            </tr>
                        </tbody>
                    </table>    
                </div>            
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-inline">
                        <div class="form-group">
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary">执行</button>
                                <button type="button" class="btn btn-default">重置</button>
                            </div>
                        </div>
                        <div class="form-group">
                            　
                            <label class="checkbox-inline">
                                <input type="checkbox" id="inlineCheckbox2" value="option2"> 启动时运行
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="inlineCheckbox2" value="option2"> 保存为
                            </label>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="exampleInputEmail2" placeholder="请输入脚本名">
                        </div>
                        <button type="submit" class="btn btn-default">保存</button>
                        <button type="submit" class="btn btn-default">删除</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
<?php include 'include/doc_foot.php' ?>