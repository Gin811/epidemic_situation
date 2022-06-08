"use strict";(self["webpackChunkepidemic_management_vue"]=self["webpackChunkepidemic_management_vue"]||[]).push([[326],{2326:function(e,t,o){o.r(t),o.d(t,{default:function(){return r}});var l=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("el-breadcrumb",{attrs:{"separator-class":"el-icon-arrow-right"}},[o("el-breadcrumb-item",{attrs:{to:{path:"/home"}}},[e._v("首页")]),o("el-breadcrumb-item",[e._v("物资储备")]),o("el-breadcrumb-item",{attrs:{to:{path:"/goods"}}},[e._v("物资列表")])],1),o("el-card",[o("el-row",{attrs:{gutter:15}},[o("el-col",{attrs:{span:8}},[o("el-input",{staticClass:"input-with-select",attrs:{clearable:"",placeholder:"请输入内容"},on:{clear:this.getGoodsList},model:{value:e.goodsName,callback:function(t){e.goodsName=t},expression:"goodsName"}},[o("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:e.searchGoods},slot:"append"})],1)],1),o("el-col",{attrs:{span:5}},[o("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addVisible=!0}}},[e._v("添加数据")]),o("el-button",{attrs:{type:"danger"}},[e._v("批量删除")])],1)],1),o("el-table",{staticStyle:{width:"100%"},attrs:{"cell-style":e.rowClass,data:e.goodsList,"header-cell-style":e.headClass,border:""},on:{"selection-change":e.handleSelectionChange}},[o("el-table-column",{attrs:{type:"selection",width:"55"}}),o("el-table-column",{attrs:{label:"序号",type:"index",width:"45px"}}),o("el-table-column",{attrs:{label:"商品名称",prop:"title",width:"150px"}}),o("el-table-column",{attrs:{"show-overflow-tooltip":!0,label:"描述",prop:"describes"}}),o("el-table-column",{attrs:{label:"图片"},scopedSlots:e._u([{key:"default",fn:function(e){return[o("el-popover",{attrs:{placement:"top-start",title:"",trigger:"hover"}},[o("img",{staticStyle:{width:"200px",height:"200px"},attrs:{src:e.row.image,alt:""}}),o("img",{staticStyle:{width:"30px",height:"30px"},attrs:{slot:"reference",src:e.row.image},slot:"reference"})])]}}])}),o("el-table-column",{attrs:{label:"交易数量",prop:"count",width:"100px"}}),o("el-table-column",{attrs:{label:"剩余数量",prop:"delCount",width:"100px"}}),o("el-table-column",{attrs:{label:"操作",width:"150px"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("el-tooltip",{staticClass:"item",attrs:{content:"编辑",effect:"dark",placement:"top-start"}},[o("el-button",{attrs:{circle:"",icon:"el-icon-edit",size:"mini",type:"primary"},on:{click:function(o){return e.editGood(t.row)}}})],1),o("el-tooltip",{staticClass:"item",attrs:{content:"删除",effect:"dark",placement:"top-start"},on:{click:function(o){return e.deleteGood(t.row.goodsId)}}},[o("el-button",{attrs:{circle:"",icon:"el-icon-delete",size:"mini",type:"danger"},on:{click:function(o){return e.deleteGood(t.row.goodsId)}}})],1)]}}])})],1),o("el-pagination",{attrs:{"current-page":this.pageNum,"page-size":this.pageSize,"page-sizes":[5,8,10,12,15],total:this.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),o("el-dialog",{attrs:{visible:e.addVisible,title:"增加数据",width:"35%"},on:{"update:visible":function(t){e.addVisible=t},close:e.closeAddDialog}},[o("el-form",{ref:"addFormRef",staticClass:"demo-Good",attrs:{"label-width":"100px"},model:{value:e.Good,callback:function(t){e.Good=t},expression:"Good"}},[o("el-form-item",{attrs:{label:"商品名称",prop:"title"}},[o("el-input",{model:{value:e.Good.title,callback:function(t){e.$set(e.Good,"title",t)},expression:"Good.title"}})],1),o("el-form-item",{attrs:{label:"描述",prop:"describes"}},[o("el-input",{model:{value:e.Good.describes,callback:function(t){e.$set(e.Good,"describes",t)},expression:"Good.describes"}})],1),o("el-form-item",{attrs:{label:"交易数量",prop:"count"}},[o("el-input",{model:{value:e.Good.count,callback:function(t){e.$set(e.Good,"count",t)},expression:"Good.count"}})],1),o("el-form-item",{attrs:{label:"剩余数量",prop:"delCount"}},[o("el-input",{model:{value:e.Good.delCount,callback:function(t){e.$set(e.Good,"delCount",t)},expression:"Good.delCount"}})],1),o("el-form-item",{attrs:{label:"选择图片"}},[o("el-upload",{staticStyle:{float:"left"},attrs:{"auto-upload":!1,limit:1,"on-change":e.handListChange,action:"","list-type":"picture-card"}},[o("i",{staticClass:"el-icon-plus"})])],1)],1),o("el-button",{on:{click:function(t){e.addVisible=!1}}},[e._v("取 消")]),o("el-button",{attrs:{type:"primary"},on:{click:e.confirmAdd}},[e._v("确 定")])],1),o("el-dialog",{attrs:{visible:e.editVisible,title:"修改数据",width:"35%"},on:{"update:visible":function(t){e.editVisible=t},close:e.closeAddDialog}},[o("el-form",{ref:"addFormRef",staticClass:"demo-Good",attrs:{model:e.Good,"label-width":"100px"}},[o("el-form-item",{attrs:{label:"商品名称",prop:"title"}},[o("el-input",{model:{value:e.Good.title,callback:function(t){e.$set(e.Good,"title",t)},expression:"Good.title"}})],1),o("el-form-item",{attrs:{label:"描述",prop:"describes"}},[o("el-input",{model:{value:e.Good.describes,callback:function(t){e.$set(e.Good,"describes",t)},expression:"Good.describes"}})],1),o("el-form-item",{attrs:{label:"交易数量",prop:"count"}},[o("el-input",{model:{value:e.Good.count,callback:function(t){e.$set(e.Good,"count",e._n(t))},expression:"Good.count"}})],1),o("el-form-item",{attrs:{label:"剩余数量",prop:"delCount"}},[o("el-input",{model:{value:e.Good.delCount,callback:function(t){e.$set(e.Good,"delCount",e._n(t))},expression:"Good.delCount"}})],1),o("el-form-item",{attrs:{label:"图片"}},[o("img",{staticStyle:{float:"left",width:"200px",height:"200px"},attrs:{src:e.Good.image,alt:""}})])],1),o("el-button",{on:{click:function(t){e.editVisible=!1}}},[e._v("取 消")]),o("el-button",{attrs:{type:"primary"},on:{click:e.confirmUpdate}},[e._v("确 定")])],1)],1)},s=[],a={name:"Goods",data(){return{addVisible:!1,editVisible:!1,goodsName:"",pageNum:1,pageSize:5,total:null,goodsList:[],Good:{count:"",delCount:null,describes:"",title:""},file:""}},methods:{removeUserItem(e){},handleSelectionChange(e){},headClass(){return"text-align:center"},rowClass(){return"text-align:center"},getGoodsList(){this.$axios.get("/goods/getAllPageGoods",{params:{pageNum:this.pageNum,pageSize:this.pageSize}}).then((e=>{const t=e.data.data;this.total=t.total,this.goodsList=t.list}))},handleSizeChange(e){this.pageSize=e,this.getGoodsList()},handleCurrentChange(e){this.pageNum=e,this.getGoodsList()},closeAddDialog(){this.$refs["addFormRef"].resetFields()},handListChange(e){let t=new FormData;t.append("file",e.raw),this.file=t},searchGoods(){},confirmAdd(){this.$axios.post("/goods/oosFile/upload",this.file).then((e=>{this.file="",200===e.data.status&&(this.Good.image=e.data.data,console.log(this.Good),this.$axios.post("/goods/add",this.Good).then((e=>{this.$message.success("添加成功！")})))})),this.addVisible=!1},editGood(e){console.log(e,"修改商品"),this.Good=e,this.editVisible=!0},confirmUpdate(){this.$axios.post("/goods/updateByGoodsId",this.Good).then((e=>{1e4===e.data.status&&this.$message.success("修改成功！")})),this.editVisible=!1},closeEditDialog(){this.$refs["addFormRef"].resetFields()},deleteGood(e){this.$confirm("此操作将永久删除该商品, 是否继续?","删除商品",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((()=>{this.$axios.get("/goods/deleteByGoodsId",{params:{goodsId:e}}).then((e=>{1e4===e.data.status?(this.$message.success("删除成功！"),this.getGoodsList()):this.$message.error("删除失败")}))})).catch((()=>{this.$message({type:"info",message:"已取消删除"})}))}},created(){this.getGoodsList()}},i=a,n=o(1001),d=(0,n.Z)(i,l,s,!1,null,"2c73b20a",null),r=d.exports}}]);
//# sourceMappingURL=326.46002ec8.js.map