//导出pdf文件的样式
export default `
	.table{
    width:100%;
	}
	.table tr{
		height: 40px;
	}
  .table td{
    padding: 0 15px;
	}
	.table label{
		width:max-content
	}
	.borderRight{
		border-right: 1px dashed #000;
	}
	.printHide{display:none}
	*{margin: 0;padding: 0;outline: none;}
	.tc{text-align: center}
	.tl{text-align: left;}
	.tr{text-align: right}
	.vm{vertical-align: middle;}
	.fl{float: left;}
	.fr{float: right;}
	.fz24{font-size: 24px;}
	.fz20{font-size: 20px;}
	.fz18{font-size: 18px;}
	.fz16{font-size: 16px;}
	.fz14{font-size: 14px;}
	.fz12{font-size: 12px;}
	.fw{font-weight: 600;}
	.mr5{margin-right: 5px}
	.mr10{margin-right: 10px}
	.mr15{margin-right: 15px}
	.mr20{margin-right: 20px}
	.ml5{margin-left:5px;}
	.ml10{margin-left:10px;}
	.ml15{margin-left:15px;}
	.ml20{margin-left:20px;}
	.mt40{margin-top:40px;}
	.mt20{margin-top: 20px;}
	.mt15{margin-top: 15px;}
	.mt10{margin-top: 10px;}
	.mt5{margin-top: 5px;}
	.mb5{margin-bottom: 5px;}
	.mb10{margin-bottom: 10px;}
	.mb15{margin-bottom: 15px;}
	.mb20{margin-bottom: 20px;}
	.pt5{padding-top: 5px;}
	.pt10{padding-top: 10px;}
	.pt15{padding-top: 15px;}
	.pt20{padding-top: 20px;}
	.pb5{padding-bottom: 5px;}
	.pb10{padding-bottom: 10px;}
	.pb15{padding-bottom: 15px;}
	.pb20{padding-bottom: 20px;}
	.pl5{padding-left: 5px;}
	.pl10{padding-left: 10px;}
	.pl15{padding-left: 15px;}
	.pl20{padding-left: 20px;}
	.pr5{padding-right: 5px;}
	.pr10{padding-right: 10px;}
	.pr15{padding-right: 15px;}
	.pr20{padding-right: 20px;}
	.colorRed{color: red;}
	.lh20{line-height: 20px;}
	.lh22{line-height: 22px;}
	.lh24{line-height: 24px;}
	.lh30{line-height: 30px;}
	.lh40{line-height: 40px;}
	.lh50{line-height: 50px;}
	.lh60{line-height: 60px;}
	.hide{display: none}
	.show{display: block}
	.inline{display: inline-block;}
	.indent2{text-indent: 2em;}
	.txt2{
		overflow : hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
	}
	.txt3{
		overflow : hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 3;
		-webkit-box-orient: vertical;
	}
	.txtno{white-space: nowrap;}/*不换行*/
	.txtsa{text-align: justify;text-align-last: justify;}/*文字分散对齐*/
	.wn{white-space:nowrap;}
	.flex{display: flex;}
	.flex1{flex:1;}
	.colu{flex-direction: column;}
	.justc{justify-content: center;}
	.justs{justify-content: space-between}/*两端对齐*/
	.justsa{justify-content: space-around}/*分散对齐*/
	.juste{justify-content: flex-end;}
	.alic{align-items: center}
	.wrap{flex-wrap:wrap}
	.childEnd{align-self:flex-end;}
	.posAbs{position: absolute;}
	.posRel{position: relative;}
	.posFix{position: fixed;}
	.top0{top:0;}
	.bottom0{bottom:0;}
	.left0{left:0;}
	.right0{right: 0;}
	.w100{width: 100%}
	.h100{height: 100%}
	.border0{border:0}
	.borBox{box-sizing: border-box;}
	.borderte0{border-top:1px solid #e0e0e0; }
	.borderbe0{border-bottom:1px solid #e0e0e0; }
	.borRad{border-radius:5px;}
	.borRad50{border-radius:50%;}
	.over{overflow:hidden;white-space:nowrap;text-overflow:ellipsis;}
	.overH{overflow: hidden}
	.clear{zoom:1;}
	.clear:after{content: "";display: block;height: 0;clear: both;}
	.paging{page-break-after: always;} /*分页符 针对打印html时使用*/
`