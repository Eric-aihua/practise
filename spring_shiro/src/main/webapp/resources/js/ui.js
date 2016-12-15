//弹出警报函数

function alert_message(alert_type, alert_title, alert_content) {
    $("body").append('<div class="alert alert-' + alert_type + ' alertExt1"> <button type="button" class="close" aria-hidden="true">×</button> <h4>' + alert_title + '</h4> <p>' + alert_content + '</p> </div> ');
    $(".alert").animate({
        right: "20px"
    }, 500).find('.close').click(function(event) {
        $(".alert").animate({
            right: "-320px"
        }, 500, function() {
            $(this).remove();
        });
    });
}

//弹窗函数

function esbWindow(esbWindow_title, esbWindow_body_URL) {
    $(".ESB-Content").append('<div class="modal fade" id="esbWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> <div class="modal-dialog"> <div class="modal-content"> <div id="loading"> <img src="img/loading.gif" class="img-responsive" alt="玩儿命加载中..."> </div> <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> <h4 class="modal-title" id="myModalLabel">' + esbWindow_title + '</h4> </div> <div id="modal_body_box"> </div> </div> </div> </div>');

    var esbWindow = $('#esbWindow'),
        btn_primary = $('.btn-primary'),
        loading = $('#loading'),
        modal_body_box = $('#modal_body_box'),
        modal_header = $('.modal-header'),
        modal_content = $('.modal-content');

    $.ajax({
        url: esbWindow_body_URL,
        cache:false,
        type: 'get',
        dataType: 'html',
    })
        .done(function(data) {
            // 加载成功
            esbWindow.find('#modal_body_box').html(data);
            loading.fadeOut(200, function() {
                $(this).remove();
                modal_header.fadeIn(300);
                modal_body_box.fadeIn(300);
            });
        })
        .fail(function() {
            // 加载失败
            loading.append('<h4>加载失败</h4>').css("margin", "150px auto 68px auto");
            modal_content.append(' <div class="modal-footer"> <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> </div>');
        })
        .always(function() {
            //
        });

    esbWindow.modal('show');
    esbWindow.on('hidden.bs.modal', function(event) {
        $(this).remove()
    });
}

//关闭窗口

function esbwindow_close() {
    $('#esbWindow').modal('hide');
}
//图标组点击后变色

function icoGroupSwitch() {
    var groupIndex = $("#groupIndex"),
        ico_group = $(".ico-group");

    function groupIndexLoad() {
        ico_group.find('div div').eq(groupIndex.val()).addClass('active').siblings('div.active').removeClass('active');
    }
    groupIndexLoad();
    ico_group.find('div div a').click(function(event) {
        groupIndex.val($(this).parent("div").index());
        groupIndexLoad();
    });
}
//新建预警功能，下拉关联页面
function alertSelect(arg1) {
    var obj = $(arg1).find("#typeSelect"),
        formObj = $(arg1),
        obj_selected,
        selectedIndex;
    function getVal_n_index() {
        selectedIndex = obj.find('option:selected').index() - 1;
        obj_selected=obj.find('option:selected');
    };

    function divCtrl(arg_1) {
        var subObj = arg_1.parentsUntil("form", "div.col-md-6").siblings('div.col-md-6'),
            subForm = arg_1.parentsUntil("form", "div.form-group").siblings('div.form-group'),
            active = arg_1.parentsUntil("form", "div.col-md-6").siblings('div.col-xs-12');

        subObj.addClass('hidden');
        subObj.eq(selectedIndex).removeClass('hidden');
        active.removeClass('hidden');

        if (selectedIndex == (-1)) {
            subForm.addClass('hidden');
            subObj.addClass('hidden');
            active.addClass('hidden');
        } else {
            subForm.removeClass('hidden');
            active.removeClass('hidden');
        };
    };

    getVal_n_index();
    divCtrl(obj);

    obj.change(function(event) {
        getVal_n_index();
        divCtrl($(this));
        obj_selected.attr('selected', 'selected').siblings().removeAttr("selected");
    });
    formObj.submit(function() {
        formObj.find('input:hidden').val("");
        formObj.find('textarea:hidden').val("");
        formObj.find('option:hidden').removeAttr("selected");
        if (selectedIndex == (-1)) {
            obj_selected.removeAttr("selected");
        }
    });
};
// 打开表单验证错误提示信息
function valNotice(title,content,id){
    if (!id){
        return
    }else{
        var node=$("#"+id);
        node.parent().append('<div class="panel panel-danger panel-error"> <div class="panel-heading"> <h3 class="panel-title">'+title+'</h3> </div> <div class="panel-body">'+content+'</div> </div>')
    }
}
// 关闭表单验证错误提示信息
function valNoticeRemove(id){
    if (!id){
        return
    }else{
        var node=$("#"+id);
        node.siblings('.panel-error').remove();
    }
}


// 导航变换active
function navSwitch(obj,objChild){
    var navList = $(obj).find(objChild);
    navList.click(function(event) {
        $(this).addClass('active').siblings(objChild).removeClass('active');
    });

}

// 实时图表
function dymanicChats(box,chartype,json_url,frequency,heightAgr){
    //初始化频率参数和高度参数，频率默认是5000毫秒，高度默认是160px；
    var freq,_height,chart;
    if (frequency) {
        freq=parseInt(frequency);
    } else{
        freq=5000;
    };
    if (heightAgr) {
        _height=parseInt(frequency);
    } else{
        _height=160;
    };
    //获取服务器时间
    function _times(){
        var date = new Date($.ajax({async: false,url:"/",}).getResponseHeader("Date"));
        return date.getTime();
    };
    function chartExcute(jsonObj){
        $(box).highcharts({
            credits: {
                enabled: false
            },
            chart: {
                className: 'esb-charts',
                backgroundColor:'transparent',
                type: chartype,
                height:_height,
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
                        // set up the updating of the chart each second

                        Highcharts.setOptions({
                            global: {
                                useUTC: false
                            }
                        });
                        var series = this.series[0];
                        setInterval(function() {
                            $.ajax({
                                url: json_url,
                                cache:false,
                                type: 'GET',
                                dataType: 'json',
                            })
                            .done(function(json) {
                                var x = (new Date()).getTime();
                                var y = parseFloat(jsonObj.usage);
                                series.addPoint([x, y], true, true);
                            });
                        }, freq);
                    }
                }
            },
            title: {
                text: jsonObj.pointName,
                align: 'left',
                x: 0,
                style: {
                    color: '#476baa',
                    fontWeight: 'normal'
                }
            },
            xAxis: {
                type: 'datetime',
                tickmarkPlacement: 'on',
                tickPixelInterval: 150
            },
            yAxis: {
                max: 100,
                min:0,
                title: {
                    enabled:false
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                    return '<b>' + this.series.name+ "：" + Highcharts.numberFormat(this.y, 0)+'%</b><br/>' +
                        "时间："+Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x)
                        ;
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: jsonObj.pointName,
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = _times(),
                        i;

                    for (i = -10; i <= 0; i++) {
                        data.push({
                            x: time + i * 1000,
                            y: 0
                        });
                    }
                    return data;
                })()
            }]
        });       
    };

    // function getData(){

    // }

    $.ajax({
        url: json_url,
        type: 'GET',
        dataType: 'json',
    })
    .done(function(json) {
        chartExcute(json)
    })
};
//环形图表
function circleChart(box,chartValueSuffix,json_url,theme) {
    //定义主题全局变量，根据theme参数对应主题风格
    var chartColor,
        chartTipColor,
        dataType,
        colors,
        jsonLength;
    //主题配色
    switch (theme){
        case "purple":
            chartColor ="#bbcef1";
            chartTipColor = "#7492c8";
            break;
        case "skyblue":
            chartColor ="#97cbfd";
            chartTipColor = "#4fa3f1";
            break;
        case "green":
            chartColor ="#a9e0a6";
            chartTipColor = "#6faa5f";
            break;
        case "yellow":
            chartColor ="#f4c97f";
            chartTipColor = "#ff954f";
            break;
    };
    //页面上为box容器内部渲染文字列表
    function addList(jsonObj){
        var listGroup=$("#"+box).siblings(".list-group"),
            totalNum=0,
            html="";
        for (var i = 0; i < jsonLength; i++) {
            totalNum += parseFloat(jsonObj[i].pointNum);
            html+='<li class="list-group-item"> <span class="badge gray">'+parseInt(jsonObj[i].pointNum)+'</span> '+jsonObj[i].pointName+'： </li>'
        };
        listGroup.html('<li class="list-group-item"><span class="badge deepGray">'+totalNum+'</span>'+jsonObj[0].dataType+'总数：</li>'+html);
    }
    //图表严重度颜色渲染，根据严重度值匹配对应的色彩
    function renderColor(obj,_index){
        var Servity =obj[_index].pointServity;
        if (Servity==0) {
            colors=chartColor;
        }
        if(Servity==1){
            colors=chartTipColor;
        };
    }

    //渲染图表方法
    function chartExcute(jsonObj){
        $("#"+box).highcharts({
            credits: {
                enabled: false
            },
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false
            },
            title: {
                style: {
                    color: '#b1b1b1',
                    fontWeight: 'normal',
                    fontSize: "14px"
                },
                text: "",
                align: 'center',
                verticalAlign: 'middle',
                y: 18
            },
            tooltip: {
                headerFormat: '<b>{point.key}</b><br>',
                pointFormat: '占比：{point.percentage:.1f}%，{series.name}：{point.y}',
                valueSuffix: chartValueSuffix
            },
            plotOptions: {
                pie: {
                        dataLabels: {
                        enabled: false,
                        distance: -32,
                        style: {
                            fontWeight: 'bold',
                            color: 'white',
                            textShadow: '1px 1px 2px black'
                        }
                    },
                    startAngle: 0,
                    endAngle: 360,
                    center: ['50%', '50%']
                }
            },
            series: [{
                type: 'pie',
                name: '数量',
                innerSize: '100%',
                data: (function() {
                    var data = [],
                        i;
                    for (i = 0; i < jsonLength; i++) {
                        renderColor(jsonObj,i)
                        data.push({
                            name:jsonObj[i].pointName,
                            color:colors,
                            y:parseInt(jsonObj[i].pointNum)
                        });
                    }
                    return data;
                })()
            }]
        });
    }

    // 用ajax调取数据并渲染整个图表
    $.ajax({
        url: json_url,
        cache:false,
        type: 'GET',
        dataType: 'json',
    })
    .done(function(json) {
        dataType=json[0].dataType;
        jsonLength=json.length;
        addList(json);
        chartExcute(json);
    })

    //获取数据最大条数
    // if (dymanicData.length>chartMaxList) {
    //     chartMaxList=dymanicData.length;
    // };

    //自动填充空白<li>
    // if (dymanicData.length<chartMaxList) {
    //     var k=chartMaxList-dymanicData.length;
    //     for (var i = 0; i < k; i++) {
    //         listGroup.append('<li class="list-group-item"></li>')
    //     };
    // };

};

//表格控件
function esbGrid(box,agrJsonRoleUrl,agrJsonUrl,checkBox,argPageLength,arggroupIn_Max){
    // Grid全局变量，获取参数box的容器，定义最大翻页数量
    var esbTable=$("#"+box),
        groupIn_Max,
        correctPage=NaN,
        pageLength=parseInt(argPageLength),
        checkAll='<th class="table-th-check"><label class="checkbox-inline"> <input type="checkbox" id="checkAll"></label></th>',
        servity0='<span class="esbicon esbicons-checkmark-circle ico-green"></span>',
        servity1='<span class="esbicon esbicons-info2 ico-red"></span>',
        td="td",
        path = $("#projectPath").val() + $("#modulePath").val(),
        RoleUrl=path+'/'+agrJsonRoleUrl,
        JsonUrl=path+'/'+agrJsonUrl,
        maxRecordCounter,
        filterParameter = "",
        pageNumber= 1
        ;

    //设置默认分页数量默认值
    if (!argPageLength) {
        pageLength=10;
    };

    if (arggroupIn_Max<5){
        groupIn_Max=5;
    }else{
        groupIn_Max=parseInt(arggroupIn_Max);
    };

    //异步渲染表格
    function renderGrid(json_roleUrl,json_url){
        // 获取json规则，初始化json
        $.ajax({
            url: json_roleUrl,
            type: 'GET',
            cache:false,
            dataType: 'json',
        })
        .done(function(rules) {
            var tableParent = esbTable.find("#tableBox"),
                role_length = rules.length,
                thHeadLength = rules[2].length,
                pageNumber= 1,
                nextPages = NaN,
                groupCount = 1,
                gourpNum = NaN,
                arrowTriger = NaN,
                searchfilter,
                tableHiddenes = $("#hiddenVal").find($("input:hidden")),
                leftArrow = '<li> <a href="#"> <span class="esbicon esbicons-arrow-left5"></span> </a> </li>',
                rightArrow = '<li> <a href="#"> <span class="esbicon esbicons-arrow-right5"></span> </a> </li>',
                leftArrowDisabled = '<li class="disabled"> <a href="#"> <span class="esbicon esbicons-arrow-left5" id="leftArrow"></span> </a> </li>',
                rightArrowDisabled = '<li class="disabled"> <a href="#"> <span class="esbicon esbicons-arrow-right5" id="rightArrow"></span> </a> </li>';

            // 获取数据规则中的最大记录数量
            maxRecordCounter = rules[6];
            // 渲染页码之前先判断最大记录数量能显示出几页
            if (maxRecordCounter % pageLength == 0) {
                pageNumber = parseInt(maxRecordCounter / pageLength);
            } else {
                pageNumber = parseInt((maxRecordCounter / pageLength)) + 1;
            };

            //计算有几组分页需要显示
            if ((pageNumber % groupIn_Max)==0){
                gourpNum = parseInt(pageNumber / groupIn_Max)
            }else{
                gourpNum = parseInt((pageNumber / groupIn_Max))+1
            };

            tableParent.html('<div class="table-responsive"></div><div id="paginationBox"><ul class="pagination"></ul><div class="pull-right"></div></div>');
            var paginationBox = $("#paginationBox"),
                pagination = paginationBox.find('ul.pagination'),
                table_responsive = esbTable.find("#tableBox div.table-responsive");

            //从表格规则中获取过滤表格的参数
            function getParameters(){
                //如果规则参数数组已经定义，则计算参数字符串
                if (rules[0][0]!==undefined){
                    //声明参数字符串，获取参数数量
                    var parameters = "",
                        parametersLength = rules[0].length;

                    //传参拼接过滤表格的参数字符串
                    function type_getParameter(Id,Val){
                        if (Val!==""&&Val!==undefined) {
                            //拼接参数SQL语句字符串
                            parameters += '&'+Id+'='+Val;
                        }else{
                            return;
                        };
                    }
                    //遍历每个参数，获取参数类型，参数ID
                    for (var i = 0; i < parametersLength; i++) {
                        var objType=rules[0][i],
                            objId = rules[1][i];

                        //根据类型改变取值方式
                        switch (objType){
                            case "search":
                                var objVal = $("#"+objId).val();
                                type_getParameter(objId,objVal);
                                searchfilter = $("#"+objId);
                                break;
                            case "text":
                                var objVal = $("#"+objId).val();
                                type_getParameter(objId,objVal);
                                break;
                            case "select":
                                var objVal = $("#"+objId).find('option:selected').val();
                                type_getParameter(objId,objVal);
                                break;
                            case "multi":
                                // ......
                                break;
                        };
                    };
                    function getHiddenVal(){
                        var tempParmeter = "";
                        if (tableHiddenes) {
                            $.each(tableHiddenes, function(index, val) {
                                // parameters += tableHiddenes.eq(index).val()
                                 tempParmeter += "&"+tableHiddenes.eq(index).attr('id')+"="+tableHiddenes.eq(index).val();
                            });
                        };
                        return tempParmeter;
                    };
                    
                    parameters += getHiddenVal();
                    return parameters;
                }else{
                    return false;
                };
            };
            //获取表格过滤SQL字符串，并赋值给过滤参数
            filterParameter = getParameters();

            //定义重新获取参数方法
            function reGetParameters(){
                filterParameter = getParameters();
                renderGrid(RoleUrl+"?"+searchfilter.attr("id")+"="+searchfilter.val(),JsonUrl);
                renderPagination(groupIn_Max,filterParameter);
                renderTable(json_url+"?pageNum=1&pageLength="+pageLength+filterParameter);
            };

            //为搜索框绑定回车事件时，执行重新获取参数方法
            searchfilter.bind("keydown", function(event) {
                if (event.keyCode == 13) {
                    reGetParameters()
                    // alert(json_url+"?pageNum=1&pageLength="+pageLength+filterParameter);
                };
            });

            //为搜索框的图标绑定点击事件时，执行重新获取参数方法
            searchfilter.siblings('.esbicon').css('cursor', 'pointer').click(function(event) {
                reGetParameters()
            });
            // alert(json_url+'?pageNum='+((groupCount-1)*groupIn_Max+1)+'&pageSize='+pageLength+filterParameter)

            // 渲染翻页
            function renderPagination(loopCount,argParameters){
                var pagesHtml = "";
                if (pageNumber <= groupIn_Max) {
                    loopCount=pageNumber;
                };
                for (var i = 0; i < loopCount; i++) {
                    pagesHtml += '<li><a href="'+path+'/'+agrJsonUrl+'?pageNum='+(i+1)+'&pageSize='+pageLength+argParameters+'">'+(i+1)+'</a></li>';
                    // pagesHtml += '<li><a href="'+path+'/tableData'+(i+1)+'.json">'+(i+1)+'</a></li>';
                    // alert('<li><a href="'+path+'/'+agrJsonUrl+'?pageNum='+(i+1)+'&pageSize='+pageLength+getParameters()+'">'+(i+1)+'</a></li>')
                };
                pagesHtml = leftArrow+pagesHtml+rightArrow;
                pagination.html(pagesHtml);

                var firstLi = pagination.find('li').eq(0),
                     lastLi = pagination.find('li').eq(pagination.find('li').length-1);

                if (pageNumber <= groupIn_Max) {
                    firstLi.addClass('disabled');
                    lastLi.addClass('disabled');
                }else{
                    firstLi.addClass('disabled');
                };
                pagination.find('li').eq(1).addClass('active').siblings('li').removeClass('active');
            };
            renderPagination(groupIn_Max,filterParameter);

            //点击分页后的行为
            function pageClick(){
                pagination.find('a').click(function(event) {
                    var li = $(this).parent("li"),
                        liClass = li.attr('class');
                        li_Index = $(this).parent("li").index(),
                        li_MaxLength = $(this).parents("ul").find('li').length-1,
                        url = $(this).attr('href')
                        ;

                    if (li_Index!==0&&li_Index!=li_MaxLength){
                        li.addClass("active").siblings("li").removeClass("active");
                        renderTable(url);
                        return false;
                    };

                    //为"上一页"/"下一页"按钮设置行为
                    if(li_Index===0&&liClass!="disabled"){
                        changeGroup(0,filterParameter);
                    }else if (li_Index==li_MaxLength&&liClass!="disabled") {
                        changeGroup(1,filterParameter)
                    };
                    return false;
                });
                // alert(pageNumber)
                // alert(nextGroupStart);
            };
            pageClick();

            //点击上一页下一页时重新渲染翻页
            function changeGroup(arg,argParameters){
                var html = "",
                    pagesHtml = "",
                    firstLi = pagination.find('li').eq(0),
                    lastLi = pagination.find('li').eq(pagination.find('li').length-1);
                
                //1,2,3,4  1,2,3,4,5
                if (pageNumber<=groupIn_Max) {
                    for (var i = 1; i <=pageNumber; i++) {

                        html += '<li><a href="'+path+'/'+agrJsonUrl+'?pageNum='+(i)+'&pageSize='+pageLength+argParameters+'">'+(i)+'</a></li>';
                        // html+='<li><a href="'+path+'/tableData'+(i)+'.json">'+(i)+'</a></li>';
                        alert(html)
                    };
                    //PRE,NEXT disabled
                    html = leftArrowDisabled+html+rightArrowDisabled;

                };

                if (pageNumber>groupIn_Max) {
                    //左翻获取HTML的方法
                    function getHtmlA_Left () {
                        var parameter = (groupCount*groupIn_Max)-groupIn_Max+1;
                        // alert(parameter);
                        for (var i = groupIn_Max; i >0; i--) {
                            html+='<li><a href="'+path+'/'+agrJsonUrl+'?pageNum='+(parameter-i)+'&pageSize='+pageLength+argParameters+'">'+(parameter-i)+'</a></li>';
                            // html+='<li><a href="'+path+'/tableData'+(parameter-i)+'.json">'+(parameter-i)+'</a></li>';
                        };
                        return html;
                    };

                    //1-10 1-20类型右翻获取HTML的方法
                    function getHtmlA_Right () {
                        //求href中连接起始位置（拼接字符串）
                        var parameter = groupCount*groupIn_Max;
                        for (var i = 0; i < groupIn_Max; i++) {
                            html+='<li><a href="'+path+'/'+agrJsonUrl+'?pageNum='+(parameter+i+1)+'&pageSize='+pageLength+argParameters+'">'+(parameter+i+1)+'</a></li>';
                            // html+='<li><a href="'+path+'/tableData'+(parameter+i+1)+'.json">'+(parameter+i+1)+'</a></li>';
                        };
                        return html;
                    };
                    //1-6 1-13类型右翻获取HTML的方法
                    function getHtmlB_Right () {
                        //求href参数的起始位置
                        var parameter = (gourpNum-1)*groupIn_Max,
                            pages = pageNumber - ((gourpNum-1)*groupIn_Max);/*求当前不足一组时剩余多少页数据*/

                        for (var i = 0; i < pages; i++) {
                            html+='<li><a href="'+path+'/'+agrJsonUrl+'?pageNum='+(parameter+i+1)+'&pageSize='+pageLength+argParameters+'">'+(parameter+i+1)+'</a></li>'
                            // html+='<li><a href="'+path+'/tableData'+(parameter+i+1)+'.json">'+(parameter+i+1)+'</a></li>';
                        };
                        return html;
                    };

                    switch (arg){
                        //点击左翻页
                        case 0:
                            //判断当前组是否是最后一组，根据情况更改右箭头的样式以达到屏蔽点击的目的
                            if (groupCount>2){
                                html=leftArrow+getHtmlA_Left()+rightArrow;
                                groupCount -= 1;
                            } else if (groupCount==2) {
                                html=leftArrowDisabled+getHtmlA_Left()+rightArrow;
                                groupCount -= 1
                            };
                            break;
                        //点击右翻页
                        case 1:
                            //判断下一组翻页是否为倒数第二组
                            if (groupCount<(gourpNum-1)){
                                html=leftArrow+getHtmlA_Right()+rightArrow;
                                groupCount += 1;
                                   //判断下一组翻页是否为最后一组，并且能显示一组翻页时...
                            } else if((pageNumber % groupIn_Max)==0&&groupCount<gourpNum){
                                html=leftArrow+getHtmlA_Right()+rightArrowDisabled;
                                groupCount += 1;
                                   //判断下一组翻页是否为最后一组，并且不能显示一组翻页时...
                            } else if ((pageNumber % groupIn_Max)>0&&groupCount<gourpNum){
                                html=leftArrow+getHtmlB_Right()+rightArrowDisabled;
                                groupCount += 1;
                            };
                            break;
                    };
                };

                pagination.html(html);
                pagination.find('li').eq(1).addClass('active').siblings('li').removeClass('active');
                // 重新渲染表格，以及绑定翻页点击事件
                // alert(json_url+'?pageNum='+((groupCount-1)*groupIn_Max+1)+'&pageSize='+pageLength+argParameters)
                renderTable(json_url+'?pageNum='+((groupCount-1)*groupIn_Max+1)+'&pageSize='+pageLength+argParameters);
                // renderTable(path+'/tableData'+((groupCount-1)*groupIn_Max+1)+'.json');
                pageClick();
            };

            //表格渲染
            function renderTable(renderUrl){
                $.ajax({
                    url: renderUrl,
                    cache:false,
                    type: 'GET',
                    dataType: 'json',
                })
                .done(function(json) {
                    //初始化局部变量
                    var jsonKeyName = [],
                        keys = [],
                        row,
                        rowLength = json.length,
                        colLength = NaN,
                        colLength = rules[2].length,
                        tbodyHtml = "",
                        theadHtml = "",
                        tableHtml = ""
                        ;

                    //获取表格数据
                    function getTable(jsonObj){
                        //声明临时td内容变量,获取SQL语句中的字段ID，寻找参数对应的JSON属性值
                        var checkVal,
                            tdHtml,
                            sql_Id = rules[5],
                            hrefValue;

                        //遍历并获取每一行表格数据，拼接HTML
                        for (var row = 0; row < rowLength; row++) {
                            //清空每次循环的html内容                            
                            tdHtml = "";
                            hrefValue = jsonObj[row][sql_Id];

                            //遍历并获取表格每一列数据，拼接HTML
                            for (var col = 0;col < colLength; col++) {

                                //声明临时td类型，td值变量
                                var rule = rules[4][col],
                                    colValue = jsonObj[row][rules[2][col]];
                                
                                //根据类型输出不同的单元格
                                if (rule==="link") {
                                    tdHtml += '<'+td+'><a href="'+path+'/'+hrefValue+'">' + colValue + '</a></'+td+'>';
                                } else if (rule==="icon"&&colValue==="0"){
                                    tdHtml += '<'+td+'>'+servity0+'</'+td+'>';
                                } else if (rule==="icon"&&colValue==="1"){
                                    tdHtml += '<'+td+'>'+servity1+'</'+td+'>';
                                } else if (rule==="id") {
                                    tdHtml=tdHtml;
                                    checkVal = hrefValue;
                                } else {
                                    tdHtml += '<'+td+'>' + colValue + '</'+td+'>';
                                };
                            };
                            //拼接每行数据
                            if (checkBox===true) {
                                // alert("allCheckBox");
                                tbodyHtml += '<tr><td><label class="checkbox-inline"> <input type="checkbox" id="checkbox'+row+'" value="'+checkVal+'"> </label></td>'+tdHtml+'</tr>';
                            } else {
                                tbodyHtml += '<tr>'+tdHtml+'</tr>';
                            };
                        };

                        // 便利表格规则json中的表头信息，拼接到HTML
                        for (var i = 0; i < thHeadLength; i++) {
                            if(rules[3][i]==="状态"){
                                theadHtml += '<th class="table-th-status">' + rules[3][i] + '</th>';
                            } else if (rules[3][i]=="") {
                                theadHtml = theadHtml;
                            } else {
                                theadHtml += '<th>' + rules[3][i] + '</th>';
                            };
                        };

                        // 拼接整个HTML,并给类型
                        if (checkBox===true) {
                            tableHtml = '<table class="table table-hover"><thead>' + checkAll + theadHtml + '</thead><tbody>' + tbodyHtml + '</tbody></table>';
                        }else{
                            tableHtml = '<table class="table table-hover"><thead>' + theadHtml + '</thead><tbody>' + tbodyHtml + '</tbody></table>';
                        };

                        return tableHtml;
                    };
                    
                    var htmlString=getTable(json);
                    // 渲染表格到页面中的div.table-responsive容器
                    table_responsive.html(htmlString);

                })
                .fail(function() {
                    alert("表格数据获取异常！");
                });
            };
            // alert(json_url+"?pageNum=1&pageLength="+pageLength+filterParameter)
            renderTable(json_url+"?pageNum=1&pageLength="+pageLength+filterParameter);
            // renderTable(json_url);
        })
        .fail(function() {
            alert("表格初始化数据获取异常！");
        });
    };
    renderGrid(RoleUrl,JsonUrl);
};

//加载页面完毕后执行
$(document).ready(function() {
    //执行弹出
    // alert_message("danger","滑出式警报标题！","这里是警告的具体信息内容，他提醒你此时什么事情需要你特别注意");
    // alertSelect("#alertDefinitions");
    // icoGroupSwitch();
    // navSwitch(".navbar-nav","li");

    //id为#btn_cancel的button点击后返回历史记录
    $("#esbWindow").find("#btn_cancel").click(function(event) {
        esbwindow_close();
    });
    //过滤搜索框按回车后提交表单
    // $("#searchfilter").bind("keyup", function(event) {
    //     if (event.keyCode == 13) {
    //         submit()
    //     }
    // });
    //执行弹窗
    // esbWindow('单列窗口','dialog/html_esbwindow.html');
    // esbWindow('双列窗口','dialog/html_esbwindow2.html');

});
