// const { get } = require("jquery");

// 实时显示时间
function showTime() {
    var time = new Date();
    var year = time.getFullYear();
    var month = (time.getMonth() + 1 + '').padStart(2, '0');
    var day = (time.getDate() + '').padStart(2, '0');
    var hour = (time.getHours() + '').padStart(2, '0');
    var minute = (time.getMinutes() + '').padStart(2, '0');
    var second = (time.getSeconds() + '').padStart(2, '0');
    //.padStart(2,'0')
    var content = year + "年" + month + "月" + day + "日" + hour + ":" + minute + ":" + second;
    $('#title .time').text(content);
}

showTime();
setInterval(showTime, 1000); //每秒执行一次


// 向腾讯发送请求，获取数据
function getData() {
    $.ajax({
        url: 'https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5',
        data: {
            name: 'disease_h5'
        },
        dataType: 'jsonp',
        success: function (res) {
            var data;
            data = JSON.parse(res.data);
            console.log(data);
            center1(data);
            right1(data);
            right2(data);
            //    left1();
        }
    });

    $.ajax({
        type: 'post',
        //   url:'https://api/inews.qq.com/newsqa/va/query/inner/publish/modules/list',
        url: 'https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list',
        data: {
            modules: 'chinaDayList,chinaDayAddList,nowConfirmStatis,nowConfirmStatis,provinceCompare'
        },
        dataType: 'json',
        success: function (res) {
            //   console.log(res);
            var data = res.data;
            left1(data);
            left2(data);
        }
    })


}

getData();

function center1(data) {
    $('#confirm').text(data.chinaTotal.confirm);
    $('#heal').text(data.chinaTotal.heal);
    $('#dead').text(data.chinaTotal.dead);
    $('#nowConfirm').text(data.chinaTotal.nowConfirm);
    $('#noInfect').text(data.chinaTotal.noInfect);
    $('#import').text(data.chinaTotal.importedCase);
}

// 初始化echarts实例
var myEcharts = echarts.init(document.getElementById("center2"),);
var option = {
    title: {  //标题样式
        text: 'China map 中国地图',
        x: "center",
        textStyle: {
            fontSize: 18,
            color: "red"
        },
    },
    tooltip: {  //这里设置提示框
        trigger: 'item',  //数据项图形触发
        backgroundColor: "red",  //提示框浮层的背景颜色。
        //字符串模板(地图): {a}（系列名称），{b}（区域名称），{c}（合并数值）,{d}（无）
        formatter: '地区：{b}<br/>累计确诊人数：{c}'
    },
    visualMap: {//视觉映射组件
        top: 'center',
        left: 'left',
        min: 10,
        max: 100000,
        text: ['High', 'Low'],
        realtime: false,  //拖拽时，是否实时更新
        calculable: true,  //是否显示拖拽用的手柄
        inRange: {
            color: ['lightskyblue', 'yellow', 'orangered']
        }
    },
    series: [
        {
            name: '模拟数据',
            type: 'map',
            mapType: 'china',
            roam: false,//是否开启鼠标缩放和平移漫游
            itemStyle: {//地图区域的多边形 图形样式
                normal: {//是图形在默认状态下的样式
                    label: {
                        show: true,//是否显示标签
                        textStyle: {
                            color: "black"
                        }
                    }
                },
                zoom: 1.5,  //地图缩放比例,默认为1
                emphasis: {//是图形在高亮状态下的样式,比如在鼠标悬浮或者图例联动高亮时
                    label: {show: true}
                }
            },
            top: "3%",//组件距离容器的距离
            data: [
                {name: '北京', value: 36785},
                {name: '天津', value: 12324},
                {name: '上海', value: 33432},
                {name: '重庆', value: 9200},
                {name: '河北', value: 5000},
                {name: '河南', value: 24600},
                {name: '云南', value: 780},
                {name: '辽宁', value: 3050},
                {name: '黑龙江', value: 80000},
                {name: '湖南', value: 2000},
                {name: '安徽', value: 24580},
                {name: '山东', value: 40629},
                {name: '新疆', value: 36981},
                {name: '江苏', value: 13569},
                {name: '浙江', value: 24956},
                {name: '江西', value: 15194},
                {name: '湖北', value: 41398},
                {name: '广西', value: 41150},
                {name: '甘肃', value: 17630},
                {name: '山西', value: 27370},
                {name: '内蒙古', value: 27370},
                {name: '陕西', value: 97208},
                {name: '吉林', value: 88290},
                {name: '福建', value: 19978},
                {name: '贵州', value: 94485},
                {name: '广东', value: 89426},
                {name: '青海', value: 35484},
                {name: '西藏', value: 97413},
                {name: '四川', value: 54161},
                {name: '宁夏', value: 56515},
                {name: '海南', value: 54871},
                {name: '台湾', value: 48544},
                {name: '香港', value: 49474},
                {name: '澳门', value: 34594}
            ]
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myEcharts.setOption(option);

function right1(data) {
    var myChart = echarts.init($('#right1')[0], 'dark');

    var option = {
        title: {
            text: "全国确诊省市TOP10",
            textStyle: {
                color: 'white',
            },
            left: 'left',
        },
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            //指示器
            axisPointer: {
                type: 'shadow'  // 默认为直线,可选为：'line' | 'shadow'
            }
        },
        xAxis: {
            type: 'category',
            data: [] //['湖北','广州','北京']
        },
        yAxis: {
            type: 'value',
            //y轴字体设置
            axisLabel: {
                show: true,
                color: 'white',
                fontSize: 12,
                formatter: function (value) {
                    if (value >= 1000) {
                        value = value / 1000 + 'k';
                    }
                    return value;
                }
            },
        },
        series: [{
            data: [], //[582,300,100],
            type: 'bar',
            barMaxWidth: "50%"
        }]
    };

    var provinces = data.areaTree[0].children;
    var topData = [];
    for (var province of provinces) {
        topData.push({
            'name': province.name,
            'value': province.total.confirm
        });
    }
    // 降序排列
    topData.sort(function (a, b) {
        return b.value - a.value;
    });
    //只保留前10个
    topData.length = 10;
    //
    console.log(topData);
    // 分别取出省份名称和数值
    for (var province of topData) {
        option.xAxis.data.push(province.name);
        option.series[0].data.push(province.value);
    }

    myChart.setOption(option);

}

function right2(data) {
    var myChart = echarts.init($('#right2')[0], 'dark');

    var option = {
        title: {
            text: '境外输入城市top5',
            //   subtext: 'True Data',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a}<br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: [], //['Search Engine','Direct','Email','Union Ads','Video Ads']
        },
        series: [
            {
                name: '省市名称',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                // data: [
                //   { value: 1048, name: 'Search Engine' },
                //   { value: 735, name: 'Direct' },
                //   { value: 580, name: 'Email' },
                //   { value: 484, name: 'Union Ads' },
                //   { value: 300, name: 'Video Ads' }
                // ],
                data: [],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    var provinces = data.areaTree[0].children;
    var topData = [];
    for (var province of provinces) {
        for (var item of province.children) {
            if (item.name === '境外输入') {
                topData.push({
                    'name': province.name,
                    'value': item.total.confirm
                });
                break;
            }
        }
    }
    //降序排序
    topData.sort(function (a, b) {
        return b.value - a.value;
    });
    //只保留前5条
    topData.length = 5;
    //分别取出省份名称和数据
    for (var province of topData) {
        option.legend.data.push(province.name);
        option.series[0].data.push(province);
    }
    myChart.setOption(option);
}

function left1(data) {
    var myChart = echarts.init($('#left1')[0], 'dark');
    var option = {
        title: {
            text: "全国累计趋势",
            textStyle: {
                color: 'white',
            },
            left: 'left',
        },
        tooltip: {
            trigger: 'axis',
            //指示器
            axisPointer: {
                type: 'line',
                lineStyle: {
                    color: '#7171c6'
                }
            },
        },
        //图例
        legend: {
            data: ["累计确诊", "累计治愈", "累计死亡"],
            left: "right"
        },
        //图形位置
        grid: {
            left: '4%',
            right: '6%',
            bottom: '4%',
            top: 50,
            containLabel: true,
        },
        xAxis: [{
            type: 'category',
            data: ['03.20', '03.21', '03.22']
        }],
        yAxis: [{
            type: 'value',
            //y轴字体设置
            axisLabel: {
                show: true,
                color: 'white',
                fontSize: 12,
                formatter: function (value) {
                    if (value >= 1000) {
                        value = value / 1000 + 'k';
                    }
                    return value;
                }
            },
            //y轴线设置显示
            axisLine: {
                show: true
            },
            //与X轴平行的线样式
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#172738',
                    width: 1,
                    type: 'solid'
                }
            }
        }],
        series: [{
            name: "累计确诊",
            type: 'line',
            smooth: true,
            data: [260, 406, 529]
        }, {
            name: "累计治愈",
            type: 'line',
            smooth: true,
            data: [25, 121, 225]
        }, {
            name: "累计死亡",
            type: 'line',
            smooth: true,
            data: [16, 59, 117]
        }]
    };

    var chinaDayList = data.chinaDayList;
    for (var day of chinaDayList) {
        option.xAxis[0].data.push(day.date);
        option.series[0].data.push(day.confirm);
        option.series[1].data.push(day.heal);
        option.series[2].data.push(day.dead);
    }
    myChart.setOption(option);
}

function left2(data) {
    var myChart = echarts.init($('#left2')[0], 'dark');
    var option = {
        title: {
            text: '全国新增趋势',
            textStyle: {
                color: 'white',
            },
            left: 'left',
        },
        tooltip: {
            trigger: 'axis',
            //指示器
            axisPointer: {
                type: 'line',
                lineStyle: {
                    color: '#7171C6'
                }
            },
        },
        //图例
        legend: {
            data: ['新增确诊', '新增疑似', '新增境外输入'],
            left: 'right'
        },
        //图形位置
        grid: {
            left: '4%',
            right: '6%',
            bottom: '4%',
            top: 50,
            containLabel: true,
        },
        xAxis: [{
            type: 'category',
            data: []
        }],
        yAxis: [{
            type: 'value',
            //y轴字体设置
            axisLabel: {
                show: true,
                color: 'white',
                fontSize: 12,
                formatter: function (value) {
                    if (value >= 1000) {
                        value = value / 1000 + 'k';
                    }
                    return value;
                }
            },
            //y轴线设置显示
            axisLine: {
                show: true
            },
            //与x轴平行的线样式
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#17273B',
                    width: 1,
                    type: 'solid',
                }
            }
        }],
        series: [{
            name: '新增确诊',
            type: 'line',
            smooth: true,
            data: []
        }, {
            name: '新增疑似',
            type: 'line',
            smooth: true,
            data: []
        }, {
            name: '新增境外输入',
            type: 'line',
            smooth: true,
            data: []
        }]
    };
    var chinaDayAddList = data.chinaDayAddList;
    for (var day of chinaDayAddList) {
        option.xAxis[0].data.push(day.date);
        option.series[0].data.push(day.confirm);
        option.series[1].data.push(day.suspect);
        option.series[2].data.push(day.importedCase);
    }
    myChart.setOption(option);
}
