<?php include 'include/doc_head.php' ?>
<?php include 'include/nav_top1.php' ?>
                <ul class="nav navbar-nav">
                    <li><a href="administration.php">用户</a></li>
                    <li><a href="administration_usergroup.php">用户组</a></li>
                    <li><a href="administration_adminshell.php">Admin Shell</a></li>
                    <li><a href="administration_scheduler.php">计划任务</a></li>
                    <li class="active"><a href="administration_auditlog.php">审查日志</a></li>
                </ul>
<?php include 'include/nav_top2.php' ?>
<?php include 'include/nav_side8.php' ?>
    <div class="container-fluid ESB-Layout ESB-Content">
        <div class="row"></div>
        <div class="row">
            <div class="col-md-12">
                <div class="table-header2">
                    活动日志
                </div>                    
            </div>
        </div>
        <div class="row toolbar">
            <div class="col-md-12">
                <form role="form" class="form-inline">
                    <div class="form-group">
                        <label for="_date">时间</label>
                        <input type="text" class="form-control input-filter" id="_date" placeholder="1970/05/05">
                        <span class="esbicon esbicons-calendar-o"></span>
                    </div>
                    <div class="form-group">
                        <label class="" for="type">类型</label>
                        <select class="form-control">
                            <option>所有</option>
                            <option>信息</option>
                            <option>错误</option>
                            <option>警报</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="" for="keyword">包含关键字</label>
                        <input type="text" class="form-control" id="keyword" placeholder="输入关键字">
                    </div>
                    <div class="form-group">
                        <label class="" for="results">结果条数</label>
                        <select class="form-control">
                            <option>10</option>
                            <option>25</option>
                            <option>50</option>
                            <option>100</option>
                            <option>200</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">搜索</button>
                        <button type="reset" class="btn btn-default">重置</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th class="table-th-W135">日期</th>
                            <th>用户</th>
                            <th class="table-th-W48">类型</th>
                            <th>活动</th>
                        </tr>                   
                    </thead>
                    <tbody>
                        <tr>
                            <td>2014/02/02 19:19:19</td>
                            <td>admin</td>
                            <td>info</td>
                            <td>Authenticated user admin; details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 192.168.40.147; SessionId: beqhwu1s2h39iv7ey6bu4o1; InteractiveAuthenticationSuccessEvent</td>
                        </tr>
                        <tr>
                            <td>2014/02/02 19:19:19</td>
                            <td>admin</td>
                            <td>info</td>
                            <td>Authenticated user admin; details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 192.168.40.147; SessionId: beqhwu1s2h39iv7ey6bu4o1; InteractiveAuthenticationSuccessEvent</td>
                        </tr>
                        <tr>
                            <td>2014/02/02 19:19:19</td>
                            <td>admin</td>
                            <td>info</td>
                            <td>Authenticated user admin; details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 192.168.40.147; SessionId: beqhwu1s2h39iv7ey6bu4o1; InteractiveAuthenticationSuccessEvent</td>
                        </tr>
                        <tr>
                            <td>2014/02/02 19:19:19</td>
                            <td>admin</td>
                            <td>info</td>
                            <td>Authenticated user admin; details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 192.168.40.147; SessionId: beqhwu1s2h39iv7ey6bu4o1; InteractiveAuthenticationSuccessEvent</td>
                        </tr>
                        <tr>
                            <td>2014/02/02 19:19:19</td>
                            <td>admin</td>
                            <td>info</td>
                            <td>Authenticated user admin; details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 192.168.40.147; SessionId: beqhwu1s2h39iv7ey6bu4o1; InteractiveAuthenticationSuccessEvent</td>
                        </tr>
                        <tr>
                            <td>2014/02/02 19:19:19</td>
                            <td>admin</td>
                            <td>info</td>
                            <td>Authenticated user admin; details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 192.168.40.147; SessionId: beqhwu1s2h39iv7ey6bu4o1; InteractiveAuthenticationSuccessEvent</td>
                        </tr>
                        <tr>
                            <td>2014/02/02 19:19:19</td>
                            <td>admin</td>
                            <td>info</td>
                            <td>Authenticated user admin; details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 192.168.40.147; SessionId: beqhwu1s2h39iv7ey6bu4o1; InteractiveAuthenticationSuccessEvent</td>
                        </tr>
                        <tr>
                            <td>2014/02/02 19:19:19</td>
                            <td>admin</td>
                            <td>info</td>
                            <td>Authenticated user admin; details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 192.168.40.147; SessionId: beqhwu1s2h39iv7ey6bu4o1; InteractiveAuthenticationSuccessEvent</td>
                        </tr>
                        <tr>
                            <td>2014/02/02 19:19:19</td>
                            <td>admin</td>
                            <td>info</td>
                            <td>Authenticated user admin; details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 192.168.40.147; SessionId: beqhwu1s2h39iv7ey6bu4o1; InteractiveAuthenticationSuccessEvent</td>
                        </tr>
                        <tr>
                            <td>2014/02/02 19:19:19</td>
                            <td>admin</td>
                            <td>info</td>
                            <td>Authenticated user admin; details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 192.168.40.147; SessionId: beqhwu1s2h39iv7ey6bu4o1; InteractiveAuthenticationSuccessEvent</td>
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