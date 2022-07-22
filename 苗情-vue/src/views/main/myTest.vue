<template>
    <div id="notice" class="notice">
        <div id="notice-title" class="notice-title">
            <ul>
            <li class="select"><a href="#">公告</a></li>
            <li><a href="#">规则</a></li>
            <li><a href="#">论坛</a></li>
            <li><a href="#">安全</a></li>
            <li><a href="#">公益</a></li>
            </ul>
        </div>
        <div id="notice-content" class="notice-content">
            <div class="mod" style="display: block">
            <ul>
                <li>
                <a href="#">张勇：要玩快乐足球</a>
                </li>
                <li>
                <a href="#">阿里2000万驰援灾区</a>
                </li>
                <li>
                <a href="#">旅游宝让你边玩边赚钱</a>
                </li>
                <li>
                <a href="#">多行跟进阿里信用贷款</a>
                </li>
            </ul>
            </div>
            <div class="mod" style="display: none">
            <ul>
                <li>
                <span>
                    [
                    <a href="">通知</a>
                    ]
                </span>
                <a href="#">“滥发”即将换新</a>
                </li>
                <li>
                <span>
                    [
                    <a href="">通知</a>
                    ]
                </span>
                <a href="#">比特币严管啦</a>
                </li>
                <li>
                <span>
                    [
                    <a href="">通知</a>
                    ]
                </span>
                <a href="#">禁发商品名录</a>
                </li>
                <li>
                <span>
                    [
                    <a href="">通知</a>
                    ]
                </span>
                <a href="#">商品熟悉限制</a>
                </li>
            </ul>
            </div>
            <div class="mod" style="display: none">
            <ul>
                <li>
                <span>
                    [
                    <a href="">聚焦</a>
                    ]
                </span>
                <a href="#">金牌卖家再启航</a>
                </li>
                <li>
                <span>
                    [
                    <a href="">功能</a>
                    ]
                </span>
                <a href="#">橱窗规则升级啦</a>
                </li>
                <li>
                <span>
                    [
                    <a href="">话题</a>
                    ]
                </span>
                <a href="#">又爱又恨优惠券</a>
                </li>
                <li>
                <span>
                    [
                    <a href="">工具</a>
                    ]
                </span>
                <a href="#">购后送店铺红包</a>
                </li>
            </ul>
            </div>
            <div class="mod" style="display: none">
            <ul>
                <li>
                <span>
                    [
                    <a href="">要闻</a>
                    ]
                </span>
                <a href="#">年轻干部要摒弃盲目求快的人生哲学</a>
                </li>
                <li>
                <span>
                    [
                    <a href="">近来好</a>
                    ]
                </span>
                <a href="#">都说实体店不行了，可便利店为啥越开越多？</a>
                </li>
                <li>
                <span>
                    [
                    <a href="">冬奥会</a>
                    ]
                </span>
                <a href="#">永远有杯咖啡留给您</a>
                </li>
                <li>
                <span>
                    [
                    <a href="">总书记</a>
                    ]
                </span>
                <a href="#">从高空视角看习总书记的春节足迹    奋进新时代</a>
                </li>
            </ul>
            </div>
            <div class="mod" style="display: none">
            <ul>
                <li>
                <a href="#">走近无声课堂</a>
                </li>
                <li>
                <a href="#">淘宝之行大众评审赢公益</a>
                </li>
                <li>
                <a href="#">爱心品牌联合征集</a>
                </li>
                <li>
                <a href="#">名公益机构淘宝开店攻略</a>
                </li>
            </ul>
            </div>
        </div>
        </div>    
</template>


<script>
    export default{
        data(){
            return{
            }
        },
        methods:{
            getElements(id) {
            return typeof id==='string'? document.getElementById(id):id;
        },
        sumWay(){
           //获取鼠标滑过或点击的标签和要切换内容的元素
          
            var titles = this.getElements('notice-title').getElementsByTagName('li'),
            divs = this.getElements('notice-content').getElementsByTagName('div');
            if(titles.length != divs.length){
            return;
            }
            // 遍历titles下的所有li
            for(var i = 0; i < titles.length; i++) {
            // 添加索引
            titles[i].id = i;
            titles[i].onmouseover = function () {
                //清除所有title上的class，将所有的div设置为隐藏
                for(var j=0; j<titles.length; j++) {
                titles[j].className = '';
                divs[j].style.display = 'none';
                }
                //设置当前li为高亮显示
                this.className = 'select';
                divs[this.id].style.display = 'block';
            }
            }
        },
        },
        created(){
            this.$nextTick(function(){
                this.sumWay();
            });
            
        },
    }
</script>  
<style>
    *{
        margin: 0;
        padding: 0;
        list-style: none;
        font-size: 12px;
        box-sizing: border-box;
    }
    .notice{
        width: 302px;
        height: 100px;
        margin: 10px;
        border: 1px solid #eee;
        overflow: hidden;
    }
    .notice-title{
        height: 26px;
        background: #f7f7f7;
    }
    
    .notice-title li{
        float: left;
        width: 60px;
        line-height: 26px;
        text-align: center;
        overflow: hidden;
        background: #fff;
        border-bottom: 1px solid #eee;
        background: #f7f7f7;
    }
    
    .notice li a:link,
    .notice li a:visited{
        text-decoration: none;
        color: #000;
    }
    
    .notice li a:hover{
        color: #f90;
    }
    .notice-title li.select{
        background: #fff;
        border-bottom-color: #fff;
        border-left: 1px solid #eee;
        border-right: 1px solid #eee;
        font-weight: bold;
    }
    .notice-title li:first-child.select{
        border-left: none;
    }
    .notice-title li:last-child.select{
        border-right: none;
    }
    
    .notice-content .mod{
        padding: 12px 5px;
    }
    .notice-content .mod ul{
        width: 300px;
        font-size: 0;
    }
    .notice-content .mod ul li{
        display: inline-block;
        width: 145px;
        height: 25px;
        line-height: 25px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    </style>
