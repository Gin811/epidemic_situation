"use strict";(self["webpackChunkepidemic_management_vue"]=self["webpackChunkepidemic_management_vue"]||[]).push([[537],{6537:function(e,t,i){i.r(t),i.d(t,{default:function(){return d}});var a=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("el-breadcrumb",{attrs:{"separator-class":"el-icon-arrow-right"}},[i("el-breadcrumb-item",{attrs:{to:{path:"/home"}}},[e._v("首页")]),i("el-breadcrumb-item",[e._v("隔离人员管理")]),i("el-breadcrumb-item",{attrs:{to:{path:"/divide"}}},[e._v("隔离人员详情")])],1),i("el-card",[i("el-row",{attrs:{gutter:25}},[i("el-col",{attrs:{span:8}},[i("el-input",{staticClass:"input-with-select",attrs:{clearable:"",placeholder:"请输入内容"},on:{clear:e.getDivideList},model:{value:e.userName,callback:function(t){e.userName=t},expression:"userName"}},[i("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:e.getDivideListByCondition},slot:"append"})],1)],1),i("el-col",{attrs:{span:8}},[i("el-select",{staticStyle:{width:"230px"},attrs:{multiple:"",placeholder:"请选择"},model:{value:e.searchValues,callback:function(t){e.searchValues=t},expression:"searchValues"}},e._l(e.options,(function(e){return i("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1),i("el-button",{attrs:{round:"",type:"primary"},on:{click:e.getDivideListByCondition}},[e._v("查询")])],1),i("el-col",{attrs:{span:2}},[i("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addVisible=!0}}},[e._v("添加数据")])],1)],1),i("el-table",{staticStyle:{width:"100%"},attrs:{"cell-style":e.rowClass,data:e.DataList,"header-cell-style":e.headClass,"row-style":e.rowStyle,border:""}},[i("el-table-column",{attrs:{label:"序号",type:"index"}}),i("el-table-column",{attrs:{label:"姓名",prop:"username"}}),i("el-table-column",{attrs:{label:"电话",prop:"mobile",width:"100px"}}),i("el-table-column",{attrs:{label:"体温",prop:"tw"}}),i("el-table-column",{attrs:{label:"症状1",prop:"status1"}}),i("el-table-column",{attrs:{label:"症状2",prop:"status2"}}),i("el-table-column",{attrs:{label:"症状3",prop:"status3"}}),i("el-table-column",{attrs:{label:"隔离地点",prop:"area",width:"160px"}}),i("el-table-column",{attrs:{label:"备注",prop:"bz"}}),i("el-table-column",{attrs:{label:"创建时间",prop:"createTime",width:"180px"}}),i("el-table-column",{attrs:{label:"操作",prop:"salt",width:"135px"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-tooltip",{staticClass:"item",attrs:{content:"编辑",effect:"dark",placement:"top-start"}},[i("el-button",{attrs:{circle:"",icon:"el-icon-edit",size:"mini",type:"primary"},on:{click:function(i){return e.editEvent(t.row)}}})],1),i("el-tooltip",{staticClass:"item",attrs:{content:"删除",effect:"dark",placement:"top-start"}},[i("el-button",{attrs:{circle:"",icon:"el-icon-delete",size:"mini",type:"danger"},on:{click:function(i){return e.removeEvent(t.row.userId)}}})],1),i("el-tooltip",{staticClass:"item",attrs:{content:"转为治愈",effect:"dark",placement:"top-start"}},[i("el-button",{attrs:{circle:"",icon:"el-icon-plus",size:"mini",type:"primary"},on:{click:function(i){return e.changebz(t.row)}}})],1)]}}])})],1),i("el-pagination",{attrs:{"current-page":e.pageNum,"page-size":e.pageSize,"page-sizes":[5,8,11,12],total:e.total,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1),i("el-dialog",{attrs:{visible:e.addVisible,title:"添加数据",width:"35%"},on:{"update:visible":function(t){e.addVisible=t},close:e.closeAddDialog}},[i("el-form",{ref:"addFormRef",staticClass:"demo-user",attrs:{model:e.Divide,rules:e.addFormRules,"label-width":"100px"}},[i("el-form-item",{attrs:{label:"姓名",prop:"username"}},[i("el-input",{model:{value:e.Divide.username,callback:function(t){e.$set(e.Divide,"username",t)},expression:"Divide.username"}})],1),i("el-form-item",{attrs:{label:"电话：",prop:"mobile"}},[i("el-input",{model:{value:e.Divide.mobile,callback:function(t){e.$set(e.Divide,"mobile",t)},expression:"Divide.mobile"}})],1),i("el-form-item",{attrs:{label:"隔离地点：",prop:"area"}},[i("el-input",{model:{value:e.Divide.area,callback:function(t){e.$set(e.Divide,"area",t)},expression:"Divide.area"}})],1),i("el-form-item",{attrs:{label:"体温：",prop:"tw"}},[i("el-input",{staticStyle:{width:"100px",float:"left"},model:{value:e.Divide.tw,callback:function(t){e.$set(e.Divide,"tw",t)},expression:"Divide.tw"}}),i("div",{staticStyle:{float:"left"}},[e._v("°C")])],1),i("el-form-item",{staticStyle:{float:"left"},attrs:{label:"症状1："}},[i("el-checkbox",{attrs:{prop:"status1","true-label":"咳嗽："},model:{value:e.Divide.status1,callback:function(t){e.$set(e.Divide,"status1",t)},expression:"Divide.status1"}},[e._v("咳嗽")])],1),i("el-form-item",{staticStyle:{float:"left"},attrs:{label:"症状2："}},[i("el-checkbox",{attrs:{prop:"status2","true-label":"感冒"},model:{value:e.Divide.status2,callback:function(t){e.$set(e.Divide,"status2",t)},expression:"Divide.status2"}},[e._v("感冒")])],1),i("el-form-item",{staticStyle:{float:"left"},attrs:{label:"症状3："}},[i("el-checkbox",{staticStyle:{float:"left"},attrs:{prop:"status3","true-label":"发烧"},model:{value:e.Divide.status3,callback:function(t){e.$set(e.Divide,"status3",t)},expression:"Divide.status3"}},[e._v("发烧")])],1),i("el-form-item",{attrs:{label:"备注：",prop:"bz"}},[i("el-input",{model:{value:e.Divide.bz,callback:function(t){e.$set(e.Divide,"bz",t)},expression:"Divide.bz"}})],1),i("el-form-item",{attrs:{label:"隔离日期：",prop:"createTime"}},[i("el-date-picker",{staticStyle:{float:"left"},attrs:{placeholder:"选择日期时间",size:"large",type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss"},model:{value:e.Divide.createTime,callback:function(t){e.$set(e.Divide,"createTime",t)},expression:"Divide.createTime"}})],1)],1),i("el-button",{on:{click:function(t){e.addVisible=!1}}},[e._v("取 消")]),i("el-button",{attrs:{type:"primary"},on:{click:e.ConfirmAdd}},[e._v("确 定")])],1),i("el-dialog",{attrs:{visible:e.editVisible,title:"修改数据",width:"35%"},on:{"update:visible":function(t){e.editVisible=t},close:e.closeEditDialog}},[i("el-form",{ref:"addFormRef",staticClass:"demo-user",attrs:{model:e.Divide,rules:e.addFormRules,"label-width":"100px"}},[i("el-form-item",{attrs:{label:"姓名",prop:"username"}},[i("el-input",{model:{value:e.Divide.username,callback:function(t){e.$set(e.Divide,"username",t)},expression:"Divide.username"}})],1),i("el-form-item",{attrs:{label:"电话",prop:"mobile"}},[i("el-input",{model:{value:e.Divide.mobile,callback:function(t){e.$set(e.Divide,"mobile",t)},expression:"Divide.mobile"}})],1),i("el-form-item",{attrs:{label:"隔离地区",prop:"area"}},[i("el-input",{model:{value:e.Divide.area,callback:function(t){e.$set(e.Divide,"area",t)},expression:"Divide.area"}})],1),i("el-form-item",{attrs:{label:"体温",prop:"tw"}},[i("el-input",{model:{value:e.Divide.tw,callback:function(t){e.$set(e.Divide,"tw",t)},expression:"Divide.tw"}})],1),i("el-form-item",{staticStyle:{float:"left"},attrs:{label:"症状1："}},[i("el-checkbox",{attrs:{prop:"status1","true-label":"咳嗽："},model:{value:e.Divide.status1,callback:function(t){e.$set(e.Divide,"status1",t)},expression:"Divide.status1"}},[e._v("咳嗽")])],1),i("el-form-item",{staticStyle:{float:"left"},attrs:{label:"症状2："}},[i("el-checkbox",{attrs:{prop:"status2","true-label":"感冒"},model:{value:e.Divide.status2,callback:function(t){e.$set(e.Divide,"status2",t)},expression:"Divide.status2"}},[e._v("感冒")])],1),i("el-form-item",{staticStyle:{float:"left"},attrs:{label:"症状3："}},[i("el-checkbox",{staticStyle:{float:"left"},attrs:{prop:"status3","true-label":"发烧"},model:{value:e.Divide.status3,callback:function(t){e.$set(e.Divide,"status3",t)},expression:"Divide.status3"}},[e._v("发烧")])],1),i("el-form-item",{attrs:{label:"备注",prop:"bz"}},[i("el-input",{model:{value:e.Divide.bz,callback:function(t){e.$set(e.Divide,"bz",t)},expression:"Divide.bz"}})],1),i("el-form-item",{attrs:{label:"隔离日期",prop:"createTime"}},[i("el-date-picker",{staticStyle:{float:"left"},attrs:{placeholder:"选择日期时间",size:"large",type:"datetime","value-format":"yyyy-MM-dd HH:mm:ss"},model:{value:e.Divide.createTime,callback:function(t){e.$set(e.Divide,"createTime",t)},expression:"Divide.createTime"}})],1)],1),i("el-button",{on:{click:function(t){e.editVisible=!1}}},[e._v("取 消")]),i("el-button",{attrs:{type:"primary"},on:{click:e.ConfirmUpdate}},[e._v("确 定")])],1)],1)},s=[],l=(i(1703),{name:"Divide",data(){const e=(e,t,i)=>{const a=/^1[345789]\d{9}$/;if(a.test(t))return i();i(new Error("请输入合法的手机号码"))};return{userName:null,addVisible:!1,editVisible:!1,DataList:[],Divide:{},addFormRules:{mobile:[{validator:e,trigger:"blur"}]},pageNum:1,pageSize:5,total:null,searchValues:[],options:[{value:"咳嗽",label:"咳嗽"},{value:"感冒",label:"感冒"},{value:"发烧",label:"发烧"}]}},methods:{rowStyle({row:e}){if("发烧"===e.status3)return{backgroundColor:"#e9f1f6"}},headClass(){return"text-align:center"},rowClass(){return"text-align:center"},getDivideList(){this.$axios.get("/admin/divide/getDivideList",{params:{pageNum:this.pageNum,pageSize:this.pageSize}}).then((e=>{1e4===e.data.status&&(this.DataList=e.data.data.list,this.total=e.data.data.total)}))},handleSizeChange(e){this.pageSize=e,0===this.searchValues.length?this.getDivideList():this.getDivideListByCondition()},handleCurrentChange(e){this.pageNum=e,0===this.searchValues.length?this.getDivideList():this.getDivideListByCondition()},getDivideListByCondition(){const e={};e.pageNum=this.pageNum,e.pageSize=this.pageSize,e.searchValues=this.searchValues,null!=this.userName&&("华帝王朝"==this.userName.substr(0,4)?e.address=this.userName:1==this.userName.substr(0,1)?e.mobile=this.userName:e.username=this.userName),this.$axios.post("/admin/divide/getDivideListByCondition",e).then((e=>{if(void 0!==e.data.data.list)1e4===e.data.status?(this.DataList=e.data.data.list,this.total=e.data.data.total,this.$message.success("查询成功！"),this.userName=null):this.$message.error("查询失败！");else{this.DataList=[];const t=e.data.data;this.DataList.push(t),this.total=1,this.userName=null}}))},ConfirmAdd(){this.Divide.tw=this.Divide.tw+"°C",this.$axios.post("/divide/add",this.Divide).then((e=>{1e4===e.data.status?(this.$message.success("新增成功！"),this.getDivideList()):this.$message.error("新增失败！"),this.addVisible=!1}))},closeAddDialog(){this.$refs.addFormRef.resetFields()},editEvent(e){this.editVisible=!0,this.Divide=e,console.log(this.Divide,111111)},changebz(e){this.$confirm("将此人转为已治愈，是否继续？",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((()=>{this.Divide=e,this.Divide.bz="已治愈",this.$axios.post("/divide/updateDivide",this.Divide).then((e=>{1e4===e.data.status?(this.$message.success("更新成功！"),this.getDivideList()):this.$message.error("更新失败！")}))})).catch((()=>{this.$message.info("已取消删除")}))},ConfirmUpdate(){this.$axios.post("/divide/updateDivide",this.Divide).then((e=>{1e4===e.data.status?(this.$message.success("更新成功！"),this.getDivideList()):this.$message.error("更新失败！")})),this.editVisible=!1},closeEditDialog(){this.$refs.addFormRef.resetFields()},removeEvent(e){console.log("删除了这个",e),this.$confirm("此操作将永久删除，是否继续？",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((()=>{this.$axios.get("/admin/divide/deleteByUserId",{params:{userId:e}}).then((e=>{this.$message.success("删除成功"),this.getDivideList()}))})).catch((()=>{this.$message.info("已取消删除")}))}},created(){this.getDivideList()}}),r=l,o=i(1001),n=(0,o.Z)(r,a,s,!1,null,"a5f34406",null),d=n.exports}}]);
//# sourceMappingURL=537.a4fbbeb5.js.map