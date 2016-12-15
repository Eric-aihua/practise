<?php include 'include/doc_head.php' ?>
<?php include 'include/nav_top1.php' ?>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="server_add.php">新建计划任务</a></li>
                    <li><a href="javascript:history.go(-1)">返回</a></li>
                </ul>
<?php include 'include/nav_top2.php' ?>
<?php include 'include/nav_side8.php' ?>
    <div class="container-fluid ESB-Layout ESB-Content">
        <div class="row"></div>
        <div class="row">
            <div class="col-md-12">
                <div class="table-header2">
                    新建计划任务
                </div>                    
            </div>
        </div>
        <div class="row">
            <form role="form">
                <div class="col-md-6 col-xm-12">
                    <div class="form-group">
                        <label for="servername">脚本</label>
                        <select class="form-control">
                            <option>类型1</option>
                            <option>类型2</option>
                            <option>类型3</option>
                            <option>类型4</option>
                            <option>类型5</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="serverid">名称</label>
                        <input type="text" class="form-control" id="servername" placeholder="名称">
                    </div>
                    <div class="form-group">
                        <label for="version">描述</label>
                        <textarea class="form-control" rows="5"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="serverid">计划任务命令</label>
                        <input type="text" class="form-control" id="servername" placeholder="名称">
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <label class="checkbox-inline">
                                <input type="checkbox" id="inlineCheckbox1" value="option1"> 允许并发执行
                            </label>
                        </div>
                    </div>
                </div>
                <div class="col-md-12 col-xs-12">
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">添加</button>
                        <button type="button" class="btn btn-default" id="btn_cancel">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
<?php include 'include/doc_foot.php' ?>